package org.example.web.controllers;


import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    //Задание 1. Устранить баг при попытке удаления записи по несуществующему id
    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove) {
        if (bookService.removeBookById(bookIdToRemove)) {
            return "redirect:/books/shelf";
        } else {
            return "redirect:/books/shelf";
        }

    }

    //Задание 3. Интерфейс и логика удаления записей по полям author, title и size
    @PostMapping("/removeByRegexAuthor")
    public String removeByRegexAuthor(@RequestParam(value = "author") String author) {
        bookService.removeBookByAuthor(author);
        return "redirect:/books/shelf";
    }

    //Задание 3. Интерфейс и логика удаления записей по полям author, title и size
    @PostMapping("/removeByRegexSize")
    public String removeByRegexSize(@RequestParam(value = "size") String size) {
        bookService.removeBookBySize(size);
        return "redirect:/books/shelf";
    }

    //Задание 3. Интерфейс и логика удаления записей по полям author, title и size
    @PostMapping("/removeByRegexTitle")
    public String removeByRegexTitle(@RequestParam(value = "title") String title) {
        bookService.removeBookByTitle(title);
        return "redirect:/books/shelf";
    }
}
