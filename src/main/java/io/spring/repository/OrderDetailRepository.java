package io.spring.repository;

import io.spring.entity.Order;
import io.spring.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("select o from OrderDetail o where o.order.id = ?1")
    public List<OrderDetail> getOrderDetailsByOrder(Integer id);
}