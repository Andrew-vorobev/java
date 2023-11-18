package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepo extends JpaRepository<GenreEntity, Integer> {

    Optional<GenreEntity> findBySlug(String slug);

}
