package com.bi.barfdogtest.repository.order.simplequery;

import com.bi.barfdogtest.domain.Address;
import com.bi.barfdogtest.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    private Address address;
    private Long orderId;

    public OrderSimpleQueryDto(String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, Long orderId) {
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.orderId = orderId;
    }
}
