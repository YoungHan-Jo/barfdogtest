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

            Member member = new Member();
            member.setName("memberA");
            member.setAge(10);
            em.persist(member);

            List<MemberDto> result = em.createQuery("select new com.bi.barfdogtest.domain.MemberDto(m.name, m.age) from Member m", MemberDto.class)
                    .getResultList();

            for (MemberDto memberDto : result) {
                System.out.println("memberDto = " + memberDto.getName());
                System.out.println("memberDto = " + memberDto.getAge());
            }


        } // end of dbInit1()
    }
}
