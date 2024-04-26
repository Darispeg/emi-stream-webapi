package com.dragocxz.emistream.service;

import com.dragocxz.emistream.configuration.NotFoundException;
import com.dragocxz.emistream.dto.UserCreateRequest;
import com.dragocxz.emistream.model.User;
import com.dragocxz.emistream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository _userRepository;

    public User save(UserCreateRequest request) {
        User _new = new User();

        _new.setFullName(request.getFullName());
        _new.setUsername(request.getUsername());
        _new.setEmail(request.getEmail());
        _new.setPhone(request.getPhone());
        _new.setStatus(request.getStatus());
        _new.setPassword(request.getPassword());

        var saved = _userRepository.save(_new);

        return saved;
    }

    public List<User> getAllUsers() {
        return _userRepository.findAll();
    }

    public User getUserById(String id) {
        Optional<User> user = _userRepository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public User updateUser(String id, UserCreateRequest request) {
        User existingUser = getUserById(id);

        existingUser.setFullName(request.getFullName());
        existingUser.setUsername(request.getUsername());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());
        existingUser.setStatus(request.getStatus());

        return _userRepository.save(existingUser);
    }

    public void deleteUser(String id) {
        User existingUser = getUserById(id);
        _userRepository.delete(existingUser);
    }
}
