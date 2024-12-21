package com.exam.repository;

import com.exam.entites.Client;

public interface IClientRepository {
    Client findByPhoneNumber(String phoneNumber);
    void save(Client client);
}