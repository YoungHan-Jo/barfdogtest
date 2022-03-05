package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20) // 길이를 여기에 적어두면 DB 까서 볼 필요 없음.
    private String name;
    private String city;
    private String street;
    private String zipcode;

}
