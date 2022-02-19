package io.spring.service.impl;

import io.spring.entity.Order;
import io.spring.repository.OrderRepository;
import io.spring.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepository.save(entity);
    }

    @Override
    @Query("select o from Order o where o.user.id = ?1")
    public List<Order> getOrderByUser(Integer id) {
        return orderRepository.getOrderByUser(id);
    }

    @Override
    public boolean existsById(Integer integer) {
        return orderRepository.existsById(integer);
    }

    @Override
    public Order getById(Integer integer) {
        return orderRepository.getById(integer);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer integer) {
        orderRepository.deleteById(integer);
    }

    @Override
    @Query("select o from Order o where o.status = ?1")
    public List<Order> getOrderByStatus(String status) {
        return orderRepository.getOrderByStatus(status);
    }
}
