package com.bi.barfdogtest.service;

import com.bi.barfdogtest.domain.item.Book;
import com.bi.barfdogtest.domain.item.Item;
import com.bi.barfdogtest.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    
    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;
    @Autowired EntityManager em;
    
    @Test
    public void 상품등록() throws Exception {
        //given
        Item item = new Book();
        item.setName("book");

        //when
        Long saveId = itemService.saveItem(item);

        //then
        Assertions.assertThat(item).isEqualTo(itemRepository.findOne(saveId));
    
    }
     

}