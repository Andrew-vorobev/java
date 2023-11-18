package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.book.links.Book2UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Book2UserEntityRepository extends JpaRepository<Book2UserEntity,Integer> {

    List<Book2UserEntity> findAllByTypeIdAndBookId(Integer typeId, Integer bookId);

}
