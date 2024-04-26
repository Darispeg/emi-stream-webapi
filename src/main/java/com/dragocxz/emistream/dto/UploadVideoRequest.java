package com.dragocxz.emistream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVideoRequest {
    private MultipartFile file;
    private String title;
    private String description;
    private String videoStatus;
}
