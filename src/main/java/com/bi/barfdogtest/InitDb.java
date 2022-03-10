package com.bi.barfdogtest;

import com.bi.barfdogtest.domain.*;
import com.bi.barfdogtest.domain.item.Book;
import com.bi.barfdogtest.domain.item.Movie;
import com.bi.barfdogtest.domain.test.Child;
import com.bi.barfdogtest.domain.test.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            // 내용

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setAge(20);

            team.addMember(member);

            em.persist(member);

            List<Member> result = em.createQuery("select m, (select avg(m1.age) from Member m1) from Member m", Member.class)
                    .getResultList();

            for (Member m : result) {
                System.out.println("m = " + m.toString());
            }

        } // end of dbInit1()
    }
}
