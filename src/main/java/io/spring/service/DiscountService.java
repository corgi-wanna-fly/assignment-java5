package io.spring.service;

import io.spring.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> findAll();

    Discount getById(Integer integer);
}
