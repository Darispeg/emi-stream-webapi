package com.dragocxz.emistream.controller;

import com.dragocxz.emistream.dto.UploadVideoResponse;
import com.dragocxz.emistream.dto.VideoDto;
import com.dragocxz.emistream.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file")MultipartFile file,
                                           @RequestParam("title")String title,
                                           @RequestParam("description") String description,
                                           @RequestParam("videoStatus") String videoStatus) {
        return videoService.uploadVideo(file, title, description, videoStatus);
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadThumbnail(@RequestParam("file")MultipartFile file) {
         return videoService.uploadThumbnail(file);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {
        return videoService.editVideo(videoDto);
    }

    @DeleteMapping("/remove/{id}")
    public String removeVideo(@PathVariable String id) {
        return videoService.removeVideoById(id);
    }

    @DeleteMapping("/thumbnail/remove/{id}")
    public String removeThumbnail(@PathVariable String id) {
        return videoService.removeThumbnailById(id);
    }
}
