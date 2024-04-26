package com.dragocxz.emistream.model;

import com.dragocxz.emistream.model.course.ThumbnailCourse;
import com.dragocxz.emistream.model.course.TopicCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
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
    private TopicCourse[] topics;
    private ThumbnailCourse thumbnail;
}
