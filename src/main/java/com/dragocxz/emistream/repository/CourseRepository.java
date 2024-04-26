package com.dragocxz.emistream.repository;

import com.dragocxz.emistream.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
