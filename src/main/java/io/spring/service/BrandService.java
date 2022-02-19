package io.spring.service;

import io.spring.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand getById(Integer integer);
}
