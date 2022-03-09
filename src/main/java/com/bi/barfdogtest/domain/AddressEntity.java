package com.bi.barfdogtest.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "address")
@Entity
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;

    private Address address;

    public AddressEntity(Address address) {
        this.address = address;
    }

    public AddressEntity() {
    }
}
