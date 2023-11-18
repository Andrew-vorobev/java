package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.book.links.Book2GenreEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Book2GenreRepo extends JpaRepository<Book2GenreEntity,Integer> {
    List<Book2GenreEntity> findAllByGenreId(int id, Pageable nextPage);
}
