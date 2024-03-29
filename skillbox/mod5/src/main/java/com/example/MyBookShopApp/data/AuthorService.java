package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Map<String, List<Author>> getAuthorsMap(){
        var authors = authorRepository.findAll();
        return authors.stream().collect(Collectors.groupingBy((Author a)->{return a.getName().substring(0,1);}));
    }
}
