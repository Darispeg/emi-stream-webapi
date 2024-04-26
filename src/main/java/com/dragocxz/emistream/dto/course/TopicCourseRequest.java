package com.dragocxz.emistream.dto.course;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopicCourseRequest {
    private String id;
    private String name;
    private String description;
    private String status;
    private String url;
}
