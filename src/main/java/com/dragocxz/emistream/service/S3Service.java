package com.dragocxz.emistream.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {

    public static final String BUCKET_NAME = "emi-stream-bucket";
    private final AmazonS3 awsS3Client;

    @Override
    public Map<String, String> uploadFile(MultipartFile file) {
        Map<String, String> S3DataResponse = new HashMap<>();
        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = String.format("%s.%s", UUID.randomUUID(), filenameExtension);

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            awsS3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
        } catch (IOException ioException) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An Exception occurred while uploading the file");
        }

        awsS3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);
        var fileUrl = awsS3Client.getObject(BUCKET_NAME, key).getObjectContent().getHttpRequest().getURI().toString();

        S3DataResponse.put("fileKey", key);
        S3DataResponse.put("fileUrl", fileUrl);

        return S3DataResponse;
    }

    @Override
    public String removeFile(String idFile) {
        try {
            awsS3Client.deleteObject(BUCKET_NAME, idFile);
            return "file successfully deleted";
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An Exception occurred while deleting the file");
        }
    }
}
