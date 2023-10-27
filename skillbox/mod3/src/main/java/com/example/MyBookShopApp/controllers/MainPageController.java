package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop")
public class MainPageController {

    private final BookService bookService;
    @Autowired
    private AuthorService authorService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("books", bookService.getBooksData());
        return "index";
    }


    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }

    @GetMapping("/recent")
    public String recent() {
        return "books/recent";
    }

    @GetMapping("/popular")
    public String popular() {
        return "books/popular";
    }

    @GetMapping("/authors")
    public String authors() {
        return "authors/index";
    }

    @ModelAttribute
    public void authorsModel(Model model) {
        model.addAttribute("authors", authorService.getAuthors());
    }
}
