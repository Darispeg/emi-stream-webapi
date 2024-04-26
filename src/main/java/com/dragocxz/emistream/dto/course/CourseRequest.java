package com.dragocxz.emistream.dto.course;

import lombok.Getter;

@Getter
public class CourseRequest {
    private String id;
    private String title;
    private String description;
    private String[] benefits;
    private String[] requirements;
    private String level;
    private String duration;
    private String category;
    private String courseStatus;
    private boolean certification;
    private TopicCourseRequest[] topics;
    private ThumbnailCourseRequest thumbnail;
}