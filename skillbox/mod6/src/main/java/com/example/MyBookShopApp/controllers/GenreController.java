package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.GenreRepo;
import com.example.MyBookShopApp.data.GenreService;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GenreController {


    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }

    @GetMapping("/genres/{slug}")
    public String slug(@PathVariable String slug, Model model){

        model.addAttribute("genreEntity",genreService.genre(slug));
        model.addAttribute("books",genreService.books(slug,0,6));
        return "genres/slug";
    }


    @ModelAttribute("genresList")
    public List<GenreEntity> genres(){
        return genreService.findAll();
    }

}
