package com.bi.barfdogtest.domain;

import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @Builder
    public Team(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }

    public Team() {
    }

    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this);
    }
}
