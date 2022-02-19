package io.spring.service;

import io.spring.entity.CartItem;
import io.spring.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<CartItem> findAll();

    <S extends CartItem> S save(S entity);

    void deleteById(Integer integer);

    Optional<CartItem> findById(Integer integer);

    List<CartItem> getCartItemByUser(User user);

    boolean existsById(Integer integer);
}
