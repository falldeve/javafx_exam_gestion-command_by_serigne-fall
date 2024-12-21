package com.exam.controllers.impl;

import com.exam.entites.Client;
import com.exam.controllers.IClientController;
import com.exam.services.IClientService;

public class ClientController implements IClientController {
    private IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Client findClientByPhoneNumber(String phoneNumber) {
        return clientService.findClientByPhoneNumber(phoneNumber);
    }
}