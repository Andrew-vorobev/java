package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.data.book.links.Book2UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    private Book2UserTypeRepository book2UserTypeRepository;
    private Book2UserEntityRepository book2UserEntityRepository;

    @Autowired
    private  TagRepository tagRepository;
    @Autowired
    private  Book2TagRepo book2TagRepo;

    @Autowired
    private Book2AuthorEntityRepo book2AuthorRepo;

    public BookService(BookRepository bookRepository, Book2UserTypeRepository book2UserTypeRepository, Book2UserEntityRepository book2UserEntityRepository) {
        this.bookRepository = bookRepository;
        this.book2UserTypeRepository = book2UserTypeRepository;
        this.book2UserEntityRepository = book2UserEntityRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public Page<Book> getPageOfRecomendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfRecentBooks(Integer offset, Integer limit, String from, String to) throws ParseException {
        var f = new SimpleDateFormat("dd.MM.yyyy");
        Date datefrom = f.parse(from);
        Date dateto = f.parse(to);

        Pageable nextPage = PageRequest.of(offset, limit);

        return bookRepository.findAllByPubDateBetweenOrderByPubDateDesc(datefrom, dateto, nextPage);
    }

    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        var books = bookRepository.findAll();

        var buyBookId = book2UserTypeRepository.findByCode("PAID").getId();
        var cartBookId = book2UserTypeRepository.findByCode("CART").getId();
        var keptBookId = book2UserTypeRepository.findByCode("KEPT").getId();

        for (var book : books) {

            var b = book2UserEntityRepository.findAllByTypeIdAndBookId(buyBookId, book.getId()).size();
            var c = book2UserEntityRepository.findAllByTypeIdAndBookId(cartBookId, book.getId()).size();
            var k = book2UserEntityRepository.findAllByTypeIdAndBookId(keptBookId, book.getId()).size();

            double p = b + 0.7 * c + 0.4 * k;
            book.setPopularity(p);
            bookRepository.save(book);
        }

        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByOrderByPopularityDesc(nextPage);
    }

    public List<Book> getBookTag(String slug,Integer offset, Integer limit) {
        var opttag = tagRepository.findBySlug(slug);

        if(!opttag.isPresent())
            return null;

        Pageable nextPage = PageRequest.of(offset, limit);

        var tag = opttag.get();
        var book2tags = book2TagRepo.findAllByTagId(tag.getId(),nextPage);

        var booksId = new ArrayList<Integer>();

        for(var b2t :book2tags)
            booksId.add(b2t.getBookId());


        return bookRepository.findAllById(booksId);
    }

    @Autowired
    private GenreService genreService;

    public List<Book> getGenre(String slug, Integer offset, Integer limit) {
        return genreService.books(slug,offset,limit);
    }

    public List<Book> getBookOfAuthor(Author author,Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);

        var book2Author = book2AuthorRepo.findAllByAuthorId(author.getId(),nextPage);

        var booksId = new ArrayList<Integer>();

        for(var b2t :book2Author)
            booksId.add(b2t.getBookId());

        var books = bookRepository.findAllById(booksId);


        return books;
    }
}
