package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private GenreRepo genreRepo;
    private Book2GenreRepo book2genre;
    private BookRepository bookRepository;

    @Autowired
    public GenreService(GenreRepo genreRepo, Book2GenreRepo book2genre, BookRepository bookRepository) {
        this.genreRepo = genreRepo;
        this.book2genre = book2genre;
        this.bookRepository = bookRepository;
    }

    public GenreEntity genre(String slug){
        return genreRepo.findBySlug(slug).get();
    }

    public List<Book> books(String slug, Integer offset, Integer limit){
        var genreOpt = genreRepo.findBySlug(slug);

        if(!genreOpt.isPresent())
            return null;

        Pageable nextPage = PageRequest.of(offset, limit);

        var ganre = genreOpt.get();
        var b2genre = book2genre.findAllByGenreId(ganre.getId(),nextPage);

        var booksId = new ArrayList<Integer>();

        for(var b2t :b2genre)
            booksId.add(b2t.getBookId());


        return bookRepository.findAllById(booksId);
    }

    public List<GenreEntity> findAll() {
        return genreRepo.findAll();
    }
}
