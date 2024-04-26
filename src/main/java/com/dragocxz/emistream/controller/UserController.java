package com.dragocxz.emistream.controller;

import com.dragocxz.emistream.configuration.NotFoundException;
import com.dragocxz.emistream.dto.CategoryRequest;
import com.dragocxz.emistream.dto.UserCreateRequest;
import com.dragocxz.emistream.dto.course.CourseRequest;
import com.dragocxz.emistream.model.Category;
import com.dragocxz.emistream.model.Course;
import com.dragocxz.emistream.model.User;
import com.dragocxz.emistream.service.CourseService;
import com.dragocxz.emistream.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody UserCreateRequest request) {
        return userService.save(request);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateCategory(@PathVariable String id, @RequestBody UserCreateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }
}