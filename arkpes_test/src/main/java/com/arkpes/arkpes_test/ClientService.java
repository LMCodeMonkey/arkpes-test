package com.arkpes.arkpes_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvestorRepository investorRepository;

    public List<Client> getAllClients(String filterField, String filterValue){
        switch (filterField) {
            case "type":
                return clientRepository.findByType(Client.clientType.valueOf(filterValue.toUpperCase()));
            case "description":
                return clientRepository.findByDescription(filterValue);
            case "phoneNumber":
                return clientRepository.findByPhoneNumber(filterValue);
            default:
                return clientRepository.findAll();
        }
    }

    public List<Investor> getClientsInvestors(String clientName) throws Exception {
        Client client = clientRepository.findByName(clientName);
        if(client == null){
            throw new Exception("Error: No client found for client name " + clientName);
        }
        return client.getInvestors();
    }

    public List<Fund> getInvestorsFunds(String investorName) throws Exception {
        Investor investor = investorRepository.findByName(investorName);
        if(investor == null){
            throw new Exception("Error: No investor found for investor name " + investorName);
        }
        return investor.getFunds();
    }

    public Client updateClient(Client client) throws Exception {
        validateClientType(client);
        return clientRepository.save(client);
    }

    protected void validateClientType(Client client) throws Exception {
        if(client.getType().equals(Client.clientType.INTERNATIONAL)
                && client.getPhoneNumber() == null){
            throw new Exception("ERROR: International clients must have a phone number.");
        }
    }
}
