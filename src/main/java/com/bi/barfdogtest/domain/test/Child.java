package com.bi.barfdogtest.domain.test;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Child {

    @Id @GeneratedValue
    @Column(name = "child_id")
    private Long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
