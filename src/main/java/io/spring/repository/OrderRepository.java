package io.spring.repository;

import io.spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.user.id = ?1")
    public List<Order> getOrderByUser(Integer id);


    @Query("select o from Order o where o.status = ?1")
    public List<Order> getOrderByStatus(String status);
}