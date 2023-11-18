package com.example.MyBookShopApp.data;

import java.util.List;

public class RecommendedBooksPageDTO {

    private Integer count;
    private List<Book> books;

    public RecommendedBooksPageDTO(List<Book> content) {
        this.count=content.size();
        this.books=content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
