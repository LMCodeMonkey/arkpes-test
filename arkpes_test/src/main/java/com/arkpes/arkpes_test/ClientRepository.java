package com.arkpes.arkpes_test;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Override
    List<Client> findAll();

    Client findByName(String clientName);

    List<Client> findByType(Client.clientType clientType);

    List<Client> findByDescription(String clientDescription);

    List<Client> findByPhoneNumber(String clientPhoneNumber);
}