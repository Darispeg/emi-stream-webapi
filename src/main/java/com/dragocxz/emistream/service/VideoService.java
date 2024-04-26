package com.dragocxz.emistream.service;

import com.dragocxz.emistream.dto.UploadVideoResponse;
import com.dragocxz.emistream.dto.VideoDto;
import com.dragocxz.emistream.model.Video;
import com.dragocxz.emistream.model.VideoStatus;
import com.dragocxz.emistream.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public UploadVideoResponse uploadVideo(MultipartFile file, String title, String description, String status) {
        Map<String, String> videoData = s3Service.uploadFile(file);

        var video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoStatus(VideoStatus.valueOf(status.toUpperCase()));
        video.setS3FileKey(videoData.get("fileKey"));
        video.setVideoUrl(videoData.get("fileUrl"));

        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    public VideoDto editVideo(VideoDto videoDto) {
        // Find the video
        Video saved = this.getVideoById(videoDto.getId());

        // Map the video fields to video
        saved.setTitle(videoDto.getTitle());
        saved.setDescription(videoDto.getDescription());
        saved.setTags(videoDto.getTags());
        saved.setThumbnailUrl(videoDto.getThumbnailUrl());
        saved.setVideoStatus(videoDto.getVideoStatus());
        // save the video to the DB

        videoRepository.save(saved);
        return videoDto;
    }

    public UploadVideoResponse uploadThumbnail(MultipartFile file) {
        Map<String, String> imgData = s3Service.uploadFile(file);

        String fileKey = imgData.get("fileKey");
        String thumbnailUrl = imgData.get("fileUrl");

        return new UploadVideoResponse(fileKey, thumbnailUrl);
    }

    public Video getVideoById(String id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id" + id));
    }

    public String removeVideoById(String id) {
        Video video = this.getVideoById(id);
        videoRepository.delete(video);
        return s3Service.removeFile(video.getS3FileKey());
    }

    public String removeThumbnailById(String id) {
        return s3Service.removeFile(id);
    }
}