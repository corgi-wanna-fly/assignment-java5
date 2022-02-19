package io.spring.service;

import io.spring.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category getById(Integer integer);
}
