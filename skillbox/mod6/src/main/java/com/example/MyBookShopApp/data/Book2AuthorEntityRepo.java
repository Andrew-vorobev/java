package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.book.links.Book2AuthorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Book2AuthorEntityRepo extends JpaRepository<Book2AuthorEntity,Integer> {
    List<Book2AuthorEntity> findAllByAuthorId(Integer id, Pageable nextPage);
}
