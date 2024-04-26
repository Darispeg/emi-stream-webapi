package com.dragocxz.emistream.service;

import com.dragocxz.emistream.configuration.NotFoundException;
import com.dragocxz.emistream.dto.course.CourseRequest;
import com.dragocxz.emistream.mapper.CourseMapper;
import com.dragocxz.emistream.model.Course;
import com.dragocxz.emistream.repository.CourseRepository;
import com.dragocxz.emistream.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper _mapper;

    public Course saveCourse(CourseRequest request) {
        Course _new = _mapper.mapToCourse(request);
        var savedVideo = courseRepository.save(_new);

        return savedVideo;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public  Course getCourseById(String id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    public Course updateCourse(String id, CourseRequest request) {
        Course existingCourse = getCourseById(id);

        _mapper.updateCourseFromRequest(existingCourse, request);

        return courseRepository.save(existingCourse);
    }

    public String deleteCourse(String id) {
        Course existingCourse = getCourseById(id);

        if (existingCourse == null) {
            throw new NotFoundException("Course not found with id: " + id);
        }

        try {
            courseRepository.delete(existingCourse);
            return "course successfully deleted";
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An Exception occurred while deleting the file");
        }
    }
}
