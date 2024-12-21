package com.exam.services;

import com.exam.entites.Article;

import java.util.List;

public interface IArticleService {
    Article findArticleById(Long id);
    List<Article> findAllArticles();
    void saveArticle(Article article);
}