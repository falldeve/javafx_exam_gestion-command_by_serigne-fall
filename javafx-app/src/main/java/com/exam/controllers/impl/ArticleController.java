package com.exam.controllers.impl;

import com.exam.entites.Article;
import com.exam.controllers.IArticleController;
import com.exam.services.IArticleService;

import java.util.List;

public class ArticleController implements IArticleController {
    private IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public Article findArticleById(Long id) {
        return articleService.findArticleById(id);
    }

    @Override
    public List<Article> findAllArticles() {
        return articleService.findAllArticles();
    }

    @Override
    public void saveArticle(Article article) {
        articleService.saveArticle(article);
    }
}