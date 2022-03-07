package com.bi.barfdogtest.domain;

import com.bi.barfdogtest.domain.item.Item;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;
    private int count;

    public void changOrder(Order order) {
        this.order = order;
    }
}
