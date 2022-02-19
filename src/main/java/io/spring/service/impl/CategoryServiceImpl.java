package io.spring.service.impl;

import io.spring.entity.Category;
import io.spring.repository.CategoryRepository;
import io.spring.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Integer integer) {
        return categoryRepository.getById(integer);
    }
}
