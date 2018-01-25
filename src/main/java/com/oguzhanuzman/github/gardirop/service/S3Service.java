package com.oguzhanuzman.github.gardirop.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.oguzhanuzman.github.gardirop.exception.aws.S3Exception;
import com.oguzhanuzman.github.gardirop.persistence.Product;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.UUID;

import static com.oguzhanuzman.github.gardirop.Constants.AWS.S3;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class S3Service {

    private final AmazonS3 amazonS3;

    @Autowired
    public S3Service(@Lazy AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String addProductImage(Product product, InputStream inputStream, String name, String contentType) {
        ObjectMetadata objectMetadata = createObjectMetadata(name, contentType);
        String imageKey = generateKey(product);

        DigestInputStream digestInputStream = new DigestInputStream(inputStream, DigestUtils.getMd5Digest());
        PutObjectResult result = amazonS3.putObject(S3.BUCKET, imageKey, digestInputStream, objectMetadata);
        amazonS3.setObjectAcl(S3.BUCKET, imageKey, CannedAccessControlList.PublicRead);

        try {
            validateImageAndResult(digestInputStream, result);
        } catch (Exception e) {
            amazonS3.deleteObject(S3.BUCKET, imageKey);
            throw e;
        }

        return imageKey;
    }

    public String getProductImageUrl(String imageKey) {
        return amazonS3.getUrl(S3.BUCKET, imageKey).toString();
    }

    private void validateImageAndResult(DigestInputStream digestInputStream, PutObjectResult result) {
        MessageDigest messageDigest = digestInputStream.getMessageDigest();
        byte[] digest = messageDigest.digest();
        String originalMd5 = Hex.encodeHexString(digest);

        if (StringUtils.equalsIgnoreCase(originalMd5, result.getContentMd5())) {
            throw new S3Exception("Uploaded file is different from source file!");
        }
    }

    private String generateKey(Product product) {
        return String.format("%s/%d/%s", S3.PRODUCT, product.getId(), generateUniqueKey());
    }

    private String generateUniqueKey() {
        return UUID.randomUUID().toString();
    }

    private ObjectMetadata createObjectMetadata(String name, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.addUserMetadata("originalName", name);
        return objectMetadata;
    }
}
