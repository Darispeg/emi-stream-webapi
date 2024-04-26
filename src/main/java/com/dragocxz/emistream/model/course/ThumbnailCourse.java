package com.dragocxz.emistream.model.course;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter @Setter
public class ThumbnailCourse {
    @Id
    private String id;
    private String videoId;
    private String videoUrl;
}