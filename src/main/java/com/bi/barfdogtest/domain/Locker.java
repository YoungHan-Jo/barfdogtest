package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.management.remote.JMXAddressable;
import javax.persistence.*;

@Entity
@Getter @Setter
public class Locker extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
