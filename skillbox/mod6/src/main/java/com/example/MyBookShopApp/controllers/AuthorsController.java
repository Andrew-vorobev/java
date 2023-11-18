package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final AuthorService authorService;
    private final BookService bookService;


    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "/authors/index";
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors/{slug}")
    public String authorsPage(@PathVariable String slug, Model model) {
        var author = authorService.getAuthor(slug);
        var books = bookService.getBookOfAuthor(author,0,6);


        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle());
        }

        model.addAttribute("author", author);
        model.addAttribute("books", books);
        return "/authors/slug";
    }
}
