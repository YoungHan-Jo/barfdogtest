package com.bi.barfdogtest;

import com.bi.barfdogtest.domain.Order;
import com.bi.barfdogtest.domain.OrderItem;
import com.bi.barfdogtest.domain.item.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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

            Movie movie = new Movie();
            movie.setName("movie1");
            movie.setPrice(20000);
            movie.setStockQuantity(100);
            em.persist(movie);
        }
    }
}
