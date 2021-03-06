package com.bi.barfdogtest;

import com.bi.barfdogtest.domain.*;
import com.bi.barfdogtest.domain.item.Book;
import com.bi.barfdogtest.domain.item.Item;
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
import java.util.Collection;
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

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setName("member1");
            member1.setAge(11);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setAge(22);
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("member3");
            member3.setAge(22);
            em.persist(member3);

            teamA.addMember(member1);
            teamA.addMember(member2);
            teamB.addMember(member3);

            em.flush();
            em.clear();

            // 자동 flush됨
            int resultCount = em.createQuery(
                            "update Member m" +
                                    " set m.age = 20")
                    .executeUpdate();
            System.out.println("resultCount = " + resultCount);

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember = " + findMember.getAge());

            em.clear();

//            Member findMember2 = em.find(Member.class, member1.getId());
//            System.out.println("findMember2 = " + findMember2.getAge());


        } // end of dbInit1()
    }
}
