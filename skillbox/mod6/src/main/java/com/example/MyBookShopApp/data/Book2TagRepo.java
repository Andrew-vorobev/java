package com.example.MyBookShopApp.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Book2TagRepo extends JpaRepository<Book2Tag,Integer> {

    List<Book2Tag> findAllByTagId(Integer integer);
    Page<Book2Tag> findAllByTagId(Integer integer, Pageable nextPage);

}
