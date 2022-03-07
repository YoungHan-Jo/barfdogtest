package com.bi.barfdogtest.domain;

import lombok.Getter;

import javax.management.remote.JMXAddressable;
import javax.persistence.*;

@Entity
@Getter
public class Locker extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
