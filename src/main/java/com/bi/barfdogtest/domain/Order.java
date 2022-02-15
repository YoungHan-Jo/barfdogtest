package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@Table(name = "orders") // 테이블 이름 수동 설정
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") // 컬럼명 수동 설정
    private Long id;

    @ManyToOne(fetch = LAZY) // 다 대 일 관계
    @JoinColumn(name = "member_id") // FK 설정 // 연관관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL) // 일 대 일 관계 // FK를 어디둘지 정해야함.
    @JoinColumn(name = "delivery_id") // 엑세스가 더 높은곳을 연관관계의 주인으로 설정
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // [ORDER, CANCEL]

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
