package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 비지니스 로직적으로 자주 조회 해야한다면 (주문서 내용 확인)
    // 양방향으로 조회할 수 있도록
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


    /*
    *  == 연관관계 편의 메소드 ==
    * */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.changOrder(this);
    }


}
