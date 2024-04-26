package com.dragocxz.emistream.repository;

import com.dragocxz.emistream.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
