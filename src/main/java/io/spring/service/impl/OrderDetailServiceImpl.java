package io.spring.service.impl;

import io.spring.entity.Order;
import io.spring.entity.OrderDetail;
import io.spring.repository.OrderDetailRepository;
import io.spring.service.OrderDetailService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public <S extends OrderDetail> S save(S entity) {
        return orderDetailRepository.save(entity);
    }

    @Override
    @Query("select o from OrderDetail o where o.order.id = ?1")
    public List<OrderDetail> getOrderDetailsByOrder(Integer id) {
        return orderDetailRepository.getOrderDetailsByOrder(id);
    }

}
