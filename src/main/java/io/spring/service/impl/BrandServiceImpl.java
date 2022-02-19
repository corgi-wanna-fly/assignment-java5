package io.spring.service.impl;

import io.spring.entity.Brand;
import io.spring.repository.BrandRepository;
import io.spring.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getById(Integer integer) {
        return brandRepository.getById(integer);
    }
}
