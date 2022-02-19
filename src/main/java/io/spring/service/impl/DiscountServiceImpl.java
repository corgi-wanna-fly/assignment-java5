package io.spring.service.impl;

import io.spring.entity.Discount;
import io.spring.repository.DiscountRepository;
import io.spring.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getById(Integer integer) {
        return discountRepository.getById(integer);
    }
}
