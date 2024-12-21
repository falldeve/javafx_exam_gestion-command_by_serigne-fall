package com.exam.repository.impl;

import com.exam.entites.Client;
import com.exam.repository.IClientRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClientRepository implements IClientRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ClientRepository() {
        emf = Persistence.createEntityManagerFactory("monPU");
        em = emf.createEntityManager();
    }

    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        return em.createQuery("FROM Client WHERE phoneNumber = :phoneNumber", Client.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
    }

    @Override
    public void save(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }
}