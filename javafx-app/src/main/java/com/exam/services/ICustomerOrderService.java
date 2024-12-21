package com.exam.services;

import com.exam.entites.CustomerOrder;

public interface ICustomerOrderService {
    CustomerOrder findOrderById(Long id);
    void saveOrder(CustomerOrder order);
}