package com.dragocxz.emistream.repository;

import com.dragocxz.emistream.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
