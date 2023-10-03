package com.example.mod2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {

    @Value("Война и мир")
    private String name;

    @Value("1865")
    private int yearOfPublication;

    @Value("Л.Н. Толстой")
    private String author;

    public String getName() {
        return name;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }


    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }
}
