package com.exam.services.impl;

import com.exam.entites.CustomerOrder;
import com.exam.repository.ICustomerOrderRepository;
import com.exam.services.ICustomerOrderService;

public class CustomerOrderService implements ICustomerOrderService {
    private ICustomerOrderRepository orderRepository;

    public CustomerOrderService(ICustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CustomerOrder findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void saveOrder(CustomerOrder order) {
        orderRepository.save(order);
    }
}