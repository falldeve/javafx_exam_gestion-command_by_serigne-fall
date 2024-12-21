package com.exam.services;

import com.exam.entites.Client;

public interface IClientService {
    Client findClientByPhoneNumber(String phoneNumber);
    void saveClient(Client client);
}