package com.dragocxz.emistream.controller;

import com.dragocxz.emistream.configuration.NotFoundException;
import com.dragocxz.emistream.dto.course.CourseRequest;
import com.dragocxz.emistream.model.Course;
import com.dragocxz.emistream.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course save(@RequestBody CourseRequest request) {
        return courseService.saveCourse(request);
    }

    @GetMapping
    public List<Course> getAll() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String id, @RequestBody CourseRequest request) {
        return courseService.updateCourse(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCourse(@PathVariable String id) {
        return courseService.deleteCourse(id);
    }

    // Manejo de excepciones para NotFoundException
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }
}
