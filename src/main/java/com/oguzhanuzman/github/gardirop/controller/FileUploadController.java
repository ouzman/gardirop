package com.oguzhanuzman.github.gardirop.controller;

import com.oguzhanuzman.github.gardirop.controller.dto.FileUploadDto;
import com.oguzhanuzman.github.gardirop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    private final ProductService productService;

    @Autowired
    public FileUploadController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("product/{id}")
    @ResponseBody // no response
    public void uploadProductImage(FileUploadDto fileUploadDto) {
        productService.uploadImage(fileUploadDto);
    }
}
