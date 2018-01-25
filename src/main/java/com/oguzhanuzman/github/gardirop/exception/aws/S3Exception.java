package com.oguzhanuzman.github.gardirop.exception.aws;

public class S3Exception extends AWSException {

    public S3Exception(String message) {
        super(message);
    }

    public S3Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
