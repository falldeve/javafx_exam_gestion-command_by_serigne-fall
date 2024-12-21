package com.exam.repository.impl;

import com.exam.entites.CustomerOrder;
import com.exam.repository.ICustomerOrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerOrderRepository implements ICustomerOrderRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public CustomerOrderRepository() {
        emf = Persistence.createEntityManagerFactory("monPU");
        em = emf.createEntityManager();
    }

    @Override
    public CustomerOrder findById(Long id) {
        return em.find(CustomerOrder.class, id);
    }

    @Override
    public void save(CustomerOrder order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }
}