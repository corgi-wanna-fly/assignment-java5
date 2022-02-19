package io.spring.service.impl;

import io.spring.entity.CartItem;
import io.spring.entity.User;
import io.spring.repository.CartItemRepository;
import io.spring.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    CartItemRepository cartItemRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public <S extends CartItem> S save(S entity) {
        return cartItemRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        cartItemRepository.deleteById(integer);
    }

    @Override
    public Optional<CartItem> findById(Integer integer) {
        return cartItemRepository.findById(integer);
    }

    @Override
    public List<CartItem> getCartItemByUser(User user) {
        return cartItemRepository.getCartItemByUser(user);
    }

    @Override
    public boolean existsById(Integer integer) {
        return cartItemRepository.existsById(integer);
    }

}
