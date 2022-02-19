package io.spring.service;

import io.spring.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    <S extends Order> S save(S entity);

    @Query("select o from Order o where o.user.id = ?1")
    List<Order> getOrderByUser(Integer id);

    boolean existsById(Integer integer);

    Order getById(Integer integer);

    Page<Order> findAll(Pageable pageable);


    void deleteById(Integer integer);

    @Query("select o from Order o where o.status = ?1")
    List<Order> getOrderByStatus(String status);
}
