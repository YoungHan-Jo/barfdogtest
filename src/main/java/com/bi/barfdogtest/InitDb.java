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

            Member member1 = new Member();
            member1.setName("member1");
            member1.setAge(11);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setAge(22);
            em.persist(member2);

            List<String> resultList = em.createQuery(
                            "select " +
                                    "   case when m.age <= 19 then '학생요금'" +
                                    "        when m.age >= 60 then '경로요금'" +
                                    "        else '일반요금'" +
                                    "   end" +
                                    " from Member m", String.class)
                    .getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }


        } // end of dbInit1()
    }
}
