package io.spring.service;

import io.spring.entity.Order;
import io.spring.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();

    <S extends OrderDetail> S save(S entity);

    @Query("select o from OrderDetail o where o.order.id = ?1")
    List<OrderDetail> getOrderDetailsByOrder(Integer id);
}
