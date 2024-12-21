package com.exam.repository.impl;

import com.exam.entites.Article;
import com.exam.repository.IArticleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ArticleRepository implements IArticleRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ArticleRepository() {
        emf = Persistence.createEntityManagerFactory("monPU");
        em = emf.createEntityManager();
    }

    @Override
    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("FROM Article", Article.class).getResultList();
    }

    @Override
    public void save(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
    }
}