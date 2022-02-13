package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders") // 테이블 이름 수동 설정
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") // 컬럼명 수동 설정
    private Long id;

    @ManyToOne // 다 대 일 관계
    @JoinColumn(name = "member_id") // FK 설정 // 연관관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne // 일 대 일 관계 // FK를 어디둘지 정해야함.
    @JoinColumn(name = "delivery_id") // 엑세스가 더 높은곳을 연관관계의 주인으로 설정
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // [ORDER, CANCEL]

}
