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

            Book book = Book.builder()
                    .name("JPA")
                    .build();

            em.persist(book);

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(new Address("homeCity","street","100"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity(new Address("old1", "street", "200")));
            member.getAddressHistory().add(new AddressEntity(new Address("old2", "street", "300")));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===================== START =========================");
            Member findMember = em.find(Member.class, member.getId());

            // 일반적인 값 타입. setter 쓰면 안됨!
            // homeCity -> newCity
            findMember.setHomeAddress(new Address("newCity","street","100"));

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // remove할 인덱스를 찾을때 equals를 사용함 -> equals와 hashcode가 있어야함!
//            findMember.getAddressHistory().remove(new Address("old1", "street", "200"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "20000"));
            // List에서 인덱스를 지우고 새 인덱스를 끼운것이고
            // DB에서는 List에 있는 리스트 전부 다 지우고 바뀐 List 내용으로 새로 만듦



        } // end of dbInit1()




    }
}
