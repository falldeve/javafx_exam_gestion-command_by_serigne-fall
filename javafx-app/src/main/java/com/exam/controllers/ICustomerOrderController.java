package com.exam.controllers;

import com.exam.entites.CustomerOrder;

public interface ICustomerOrderController {
    CustomerOrder findOrderById(Long id);
    void saveOrder(CustomerOrder order);
}