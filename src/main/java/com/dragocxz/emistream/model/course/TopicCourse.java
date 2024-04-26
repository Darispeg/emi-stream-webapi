package com.dragocxz.emistream.model.course;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter @Setter
public class TopicCourse {
    @Id
    private String id;
    private String name;
    private String description;
    private String status;
    private String url;
}
