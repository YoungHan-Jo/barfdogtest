package com.bi.barfdogtest.api;

import com.bi.barfdogtest.domain.Address;
import com.bi.barfdogtest.domain.Order;
import com.bi.barfdogtest.domain.OrderStatus;
import com.bi.barfdogtest.repository.OrderRepository;
import com.bi.barfdogtest.repository.order.simplequery.OrderSimpleQueryDto;
import com.bi.barfdogtest.repository.order.simplequery.OrderSimpleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleRepository orderSimpleRepository;

    @GetMapping("/api/v3/simple-orders")
    public Result ordersV3() {

        List<Order> orders = orderRepository.findAll();
        List<SimpleOrderDto> collect = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @GetMapping("/api/v4/simple-orders")
    public Result ordersV4() {
        List<OrderSimpleQueryDto> orderDtos = orderSimpleRepository.findOrderDtos();
        return new Result(orderDtos);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }

}
