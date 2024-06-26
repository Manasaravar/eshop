package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.repositories.CategoryRepository;
import com.korniushin.eshop.model.entities.Category;
import com.korniushin.eshop.model.dao.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> all() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category newCategory = categoryRepository.findById(category.getId()).get();
        newCategory.setTitle(category.getTitle());
        return categoryRepository.save(newCategory);
    }

    @Override
    public boolean deleteById(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
