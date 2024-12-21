package com.exam.controllers.impl;

import com.exam.entites.CustomerOrder;
import com.exam.controllers.ICustomerOrderController;
import com.exam.services.ICustomerOrderService;

public class CustomerOrderController implements ICustomerOrderController {
    private ICustomerOrderService orderService;

    public CustomerOrderController(ICustomerOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CustomerOrder findOrderById(Long id) {
        return orderService.findOrderById(id);
    }

    @Override
    public void saveOrder(CustomerOrder order) {
        orderService.saveOrder(order);
    }
}