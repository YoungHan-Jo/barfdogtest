package com.bi.barfdogtest.domain.item;

import com.bi.barfdogtest.domain.Category;
import lombok.*;

import javax.persistence.Entity;
import java.util.List;


@Getter
@NoArgsConstructor

@Entity
public class Book extends Item{

    private String author;
    private String isbn;

    @Builder
    public Book(Long id, String name, int price, int stockQuantity, List<Category> categories, String author, String isbn) {
        super(id, name, price, stockQuantity, categories);
        this.author = author;
        this.isbn = isbn;
    }

    public Book(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }
}
