package com.bi.barfdogtest.service;

import com.bi.barfdogtest.domain.Address;
import com.bi.barfdogtest.domain.Member;
import com.bi.barfdogtest.domain.Order;
import com.bi.barfdogtest.domain.OrderStatus;
import com.bi.barfdogtest.domain.item.Book;
import com.bi.barfdogtest.domain.item.Item;
import com.bi.barfdogtest.exception.NotEnoughStockException;
import com.bi.barfdogtest.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired EntityManager em;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Item item = createBook("SPRING BOOT", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //given
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).as("주문 상태는 ORDER 이어야한다.").isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems().size()).as("주문 상품 종류는 한 종류 이어야 한다.").isEqualTo(1);
        assertThat(getOrder.getTotalPrice()).as("주문 금액은 상품가격 * 수량 이어야 한다.").isEqualTo(10000 * orderCount);
        assertThat(item.getStockQuantity()).as("재고는 8개 이어야 한다.").isEqualTo(8);

    }



    @Test(expected = NotEnoughStockException.class)
    public void 상품초과주문() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("SPRING BOOT", 10000, 10);

        int orderCount = 11;

        //when
        orderService.order(member.getId(), item.getId(), orderCount);

        //given
        fail("재고 수량 부족 시 예외가 발생해야 한다.");

    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("SPRING BOOT", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //given
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(item.getStockQuantity()).as("재고가 복구되어야한다.").isEqualTo(10);
        assertThat(getOrder.getStatus()).as("주문 상태는 CANCEL 이어야한다.").isEqualTo(OrderStatus.CANCEL);

    }



    private Item createBook(String name, int price, int stockQuantity) {
        Item item = new Book();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        em.persist(item);
        return item;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("부산시","황령대로","13"));
        em.persist(member);
        return member;
    }

}