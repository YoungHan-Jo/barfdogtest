package com.bi.barfdogtest;

import com.bi.barfdogtest.domain.Locker;
import com.bi.barfdogtest.domain.Member;
import com.bi.barfdogtest.domain.Order;
import com.bi.barfdogtest.domain.OrderItem;
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

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);


        } // end of dbInit1()

        


    }
}
