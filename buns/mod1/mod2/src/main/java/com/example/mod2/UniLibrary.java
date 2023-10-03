package com.example.mod2;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary {
    public void getBook(Book book){
        System.out.println("Мы берем книгу " + book.getName());
    }

    public String returnBook(Book book){
        System.out.println("Мы возвращаем книгу " + book.getName());
        return book.getName();
    }

    public void getMagazine(){
        System.out.println("Мы берем журнал");
    }

    public void returnMagazine(){
        System.out.println("Мы возвращаем журнал");
    }

    public void addBook(Book book){
        System.out.println("Мы добавили книгу " + book.getName());
    }

    public void addMagazine(){
        System.out.println("Мы добавили журнал");
    }
}
