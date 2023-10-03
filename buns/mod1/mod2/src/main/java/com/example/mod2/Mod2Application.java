package com.example.mod2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Mod2Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        UniLibrary uniLibrary = context.getBean(UniLibrary.class);
        Book book = context.getBean(Book.class);

        uniLibrary.getBook(book);
        String bookName = uniLibrary.returnBook(book);
        System.out.println("В библиотеку вернули книгу: " + bookName);

        uniLibrary.getMagazine();
        uniLibrary.returnMagazine();

        uniLibrary.addBook(book);
        uniLibrary.addMagazine();

        context.close();
    }

}
