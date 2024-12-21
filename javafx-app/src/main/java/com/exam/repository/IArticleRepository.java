package com.exam.repository;

import com.exam.entites.Article;

import java.util.List;

public interface IArticleRepository {
    Article findById(Long id);
    List<Article> findAll();
    void save(Article article);
}