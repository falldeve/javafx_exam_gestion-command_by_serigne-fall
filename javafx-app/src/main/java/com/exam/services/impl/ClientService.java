package com.exam.services.impl;

import com.exam.entites.Client;
import com.exam.repository.IClientRepository;
import com.exam.services.IClientService;

public class ClientService implements IClientService {
    private IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findClientByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }
}