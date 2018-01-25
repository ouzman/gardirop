package com.oguzhanuzman.github.gardirop.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadDto {
    private Long id;
    private MultipartFile file;
}
