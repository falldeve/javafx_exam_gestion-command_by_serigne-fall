package com.exam.services.impl;

import com.exam.entites.Article;
import com.exam.repository.IArticleRepository;
import com.exam.services.IArticleService;

import java.util.List;

public class ArticleService implements IArticleService {
    private IArticleRepository articleRepository;

    public ArticleService(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }
}