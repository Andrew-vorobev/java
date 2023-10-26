package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(BookRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

//    Задание 2. Исключить возможность сохранения пустых записей
    public void saveBook(Book book) {
        if(!book.getAuthor().isEmpty() || !book.getSize().isEmpty() || !book.getTitle().isEmpty())
            bookRepo.store(book);
    }

    //Задание 1. Устранить баг при попытке удаления записи по несуществующему id
    public boolean removeBookById(Integer bookIdToRemove) {
        try{
            return bookRepo.removeItemById(bookIdToRemove);
        }catch (Exception e){
            return false;
        }
    }

    public boolean removeBookByTitle(String title) {
        try{
            return bookRepo.removeItemByTitle(title);
        }catch (Exception e){
            return false;
        }
    }

    public boolean removeBookBySize(String size) {
        try{
            return bookRepo.removeItemBySize(size);
        }catch (Exception e){
            return false;
        }
    }

    public boolean removeBookByAuthor(String author) {
        try{
            return bookRepo.removeItemByAuthor(author);
        }catch (Exception e){
            return false;
        }
    }
}
