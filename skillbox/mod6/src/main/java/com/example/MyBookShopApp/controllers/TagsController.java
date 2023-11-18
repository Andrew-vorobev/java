package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.Book2TagRepo;
import com.example.MyBookShopApp.data.BookRepository;
import com.example.MyBookShopApp.data.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class TagsController {

    private final TagRepository tagRepository;
    private final Book2TagRepo book2TagRepo;
    private final BookRepository bookRepository;

    @Autowired

    public TagsController(TagRepository tagRepository, Book2TagRepo book2TagRepo, BookRepository bookRepository) {
        this.tagRepository = tagRepository;
        this.book2TagRepo = book2TagRepo;
        this.bookRepository = bookRepository;
    }


    @GetMapping("/tags/{slug}")
    public String getTag(@PathVariable String slug, Model model) {
        var opttag = tagRepository.findBySlug(slug);

        if(!opttag.isPresent())
            return "redirect:/";

        var tag = opttag.get();
        var book2tags = book2TagRepo.findAllByTagId(tag.getId());

        var books = new ArrayList<Book>();

        for(var b2t :book2tags)
            books.add(bookRepository.findById(b2t.getBookId()).get());


        model.addAttribute("tag", tag);
        model.addAttribute("books", books);

        return "tags/index";
    }

}


