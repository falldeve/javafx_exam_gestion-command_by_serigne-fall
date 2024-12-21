package com.exam.repository;

import com.exam.entites.CustomerOrder;

public interface ICustomerOrderRepository {
    CustomerOrder findById(Long id);
    void save(CustomerOrder order);
}