package com.exam.controllers;

import com.exam.entites.Article;

import java.util.List;

public interface IArticleController {
    Article findArticleById(Long id);
    List<Article> findAllArticles();
    void saveArticle(Article article);
}