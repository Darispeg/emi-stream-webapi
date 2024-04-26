package com.dragocxz.emistream.service;

import com.dragocxz.emistream.configuration.NotFoundException;
import com.dragocxz.emistream.dto.CategoryRequest;
import com.dragocxz.emistream.model.Category;
import com.dragocxz.emistream.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository _categoryRepository;

    public Category save(CategoryRequest request) {
        Category _new = new Category();

        _new.setTitle(request.getTitle());
        _new.setDescription(request.getDescription());
        _new.setStatus(request.getStatus());
        _new.setIcon(request.getIcon());

        var saved = _categoryRepository.save(_new);

        return saved;
    }

    public List<Category> getAllCategories() {
        return _categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {
        Optional<Category> category = _categoryRepository.findById(id);
        return category.orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
    }

    public Category updateCategory(String id, CategoryRequest request) {
        Category existingCategory = getCategoryById(id);

        existingCategory.setTitle(request.getTitle());
        existingCategory.setDescription(request.getDescription());
        existingCategory.setStatus(request.getStatus());
        existingCategory.setIcon(request.getIcon());

        return _categoryRepository.save(existingCategory);
    }

    public String deleteCategory(String id) {
        Category existingCategory = getCategoryById(id);
        try {
            _categoryRepository.delete(existingCategory);
            return "category successfully deleted";
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An Exception occurred while deleting the file");
        }
    }
}
