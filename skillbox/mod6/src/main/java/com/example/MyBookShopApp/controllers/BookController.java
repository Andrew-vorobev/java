package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;


    @GetMapping("/books/recent")
    public String recent() {
        return "books/recent";
    }

    @GetMapping("/books/popular")
    public String popular() {
        return "books/popular";
    }


    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecomendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() throws ParseException {
        var from = "01.01.1977";
        var to = "01.01.2023";
        return bookService.getPageOfRecentBooks(0, 6, from, to).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookService.getPageOfPopularBooks(0, 6).getContent();
    }


    @GetMapping("/books/recommended")
    @ResponseBody
    public RecommendedBooksPageDTO getBooksPageRecommended(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDTO(bookService.getPageOfRecomendedBooks(offset, limit).getContent());
    }

    @GetMapping("/api/books/recent")
    @ResponseBody
    public RecommendedBooksPageDTO getBooksPageRecent(@RequestParam("offset") Integer offset, @RequestParam(value = "limit") Integer limit, @RequestParam(value = "from", required = false) String from, @RequestParam(value = "to", required = false) String to) throws ParseException {
        if (from == null || to == null || from.isEmpty() || to.isEmpty() || from.isBlank() || to.isBlank()) {
            from = "01.01.1977";
            to = "01.01.2023";
        }
        return new RecommendedBooksPageDTO(bookService.getPageOfRecentBooks(offset, limit, from, to).getContent());
    }


    @GetMapping("/api/books/popular")
    @ResponseBody
    public RecommendedBooksPageDTO getBooksPagePopular(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDTO(bookService.getPageOfPopularBooks(offset, limit).getContent());
    }


    @GetMapping("/api/books/tag/{slug}")
    @ResponseBody
    public RecommendedBooksPageDTO getBookstag(@PathVariable String slug,@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDTO(bookService.getBookTag(slug,offset, limit));
    }

    @GetMapping("/api/books/genre/{slug}")
    @ResponseBody
    public RecommendedBooksPageDTO genre(@PathVariable String slug,@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDTO(bookService.getGenre(slug,offset, limit));
    }

    @Autowired
    private TagRepository tagRepository;


    @GetMapping("/")
    public String mainPage(Model model) {

        var size = new HashMap<Integer, String>();
        var tags = tagRepository.findAll();

        for (var tag : tags)
            size.put(tag.getId(), tag.getSize());

        model.addAttribute("tags", tags);
        model.addAttribute("size", size);
        return "index";
    }

    @GetMapping("/books/author/{slug}")
    public String authorBook(@PathVariable String slug,Model model) {
        var author = authorService.getAuthor(slug);
        var books = bookService.getBookOfAuthor(author,0,6);

        model.addAttribute("author", author);
        model.addAttribute("books", books);
        return "/books/author";
    }

    @GetMapping("/api/books/author/{slug}")
    @ResponseBody
    public RecommendedBooksPageDTO authorBookApi(@PathVariable String slug,@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        var author = authorService.getAuthor(slug);
        var books = bookService.getBookOfAuthor(author,0,6);
        return new RecommendedBooksPageDTO(books);
    }

}
