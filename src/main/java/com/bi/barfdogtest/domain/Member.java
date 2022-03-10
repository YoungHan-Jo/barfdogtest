package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
//@Table(uniqueConstraints = ) // 여기에 테이블 조건 같은거 다 적어두면 DB 까서 볼 필요없음
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20) // 길이를 여기에 적어두면 DB 까서 볼 필요 없음.
    private String name;

    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "favorite_food", // 테이블 이름
            joinColumns = @JoinColumn(name = "member_id") // FK 값 설정
    )
    @Column(name = "food_name") // 아직 다른곳에 존재하지 않기때문에 예외적으로 허용됨
    private Set<String> favoriteFoods = new HashSet<>();
//
//    @ElementCollection
//    @CollectionTable(name = "address", // 테이블 이름
//            joinColumns = @JoinColumn(name = "member_id") // FK 값 설정
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<AddressEntity> addressHistory = new ArrayList<>();


//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();
//    반대 방향에서 검색하고 싶을때 객체지향 스럽게 검색하려면 이렇게 해야할거 같지만 이건 잘못된 설계.
//    ** 연관관계를 잘 끊어내는게 중요함 **
    //    주문에 이미 FK로 memberId가 있기 때문에 Order테이블을 바로 검색하면 됨


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
