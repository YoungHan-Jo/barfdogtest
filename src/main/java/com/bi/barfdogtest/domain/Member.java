package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//@Table(uniqueConstraints = ) // 여기에 테이블 조건 같은거 다 적어두면 DB 까서 볼 필요없음
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20) // 길이를 여기에 적어두면 DB 까서 볼 필요 없음.
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locker_id")
    private Locker locker;

    private String city;
    private String street;
    private String zipcode;

//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();
//    반대 방향에서 검색하고 싶을때 객체지향 스럽게 검색하려면 이렇게 해야할거 같지만 이건 잘못된 설계.
//    ** 연관관계를 잘 끊어내는게 중요함 **
    //    주문에 이미 FK로 memberId가 있기 때문에 Order테이블을 바로 검색하면 됨

}
