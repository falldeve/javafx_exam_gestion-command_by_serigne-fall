package com.exam.controllers;

import com.exam.entites.Client;

public interface IClientController {
    Client findClientByPhoneNumber(String phoneNumber);
}