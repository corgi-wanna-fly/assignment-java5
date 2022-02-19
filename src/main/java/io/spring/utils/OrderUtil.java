package io.spring.utils;

import io.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderUtil {
    private static List<String> status = List.of("Chờ duyệt", "Đã duyệt", "Giao thành công", "Đã huỷ");

    @Autowired
    OrderService orderService;

    public Map<String, Integer> getInfo(){

        Map<String, Integer> map = new HashMap<>();

        for (String str: status){
            int count = orderService.getOrderByStatus(str).size();
            map.put(str, count);
        }

        return map;
    }
}
