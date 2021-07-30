package com.arkpes.arkpes_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {

    @Mock
    ClientRepository clientRepository;
    @Mock
    InvestorRepository investorRepository;

    @InjectMocks
    @Spy
    ClientService clientService;

    @Test
    public void testGetAllClientsNoFilter(){
        clientService.getAllClients("noFilter", null);
        Mockito.verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllClientsTypeFilter(){
        clientService.getAllClients("type", "domestic");
        Mockito.verify(clientRepository, times(1)).findByType(Client.clientType.DOMESTIC);
    }

    @Test
    public void testGetAllClientsDescriptionFilter(){
        clientService.getAllClients("description", "test description");
        Mockito.verify(clientRepository, times(1)).findByDescription("test description");
    }

    @Test
    public void testGetAllClientsPhoneNumberFilter(){
        clientService.getAllClients("phoneNumber", "test number");
        Mockito.verify(clientRepository, times(1)).findByPhoneNumber("test number");
    }

    @Test
    public void testGetClientsInvestorsClientNotFound(){
        String testClient = "testClient";
        doReturn(null).when(clientRepository).findByName(testClient);

        String result = assertThrows(Exception.class, () -> {
            clientService.getClientsInvestors(testClient);
        }).getMessage();

        assertEquals("Error: No client found for client name " + testClient, result);
    }

    @Test
    public void testGetClientsInvestorsClientFound() throws Exception {
        Client testClient = new Client();
        testClient.setName("testClient");
        testClient.setInvestors(new ArrayList<>());

        doReturn(testClient).when(clientRepository).findByName(testClient.getName());

        List<Investor> result = clientService.getClientsInvestors(testClient.getName());

        assertEquals(testClient.getInvestors(), result);
    }

    @Test
    public void testGetInvestorsFundsInvestorNotFound() {
        String testInvestor = "testInvestor";
        doReturn(null).when(investorRepository).findByName(testInvestor);

        String result = assertThrows(Exception.class, () -> {
            clientService.getInvestorsFunds(testInvestor);
        }).getMessage();

        assertEquals("Error: No investor found for investor name " + testInvestor, result);
    }

    @Test
    public void testGetInvestorsFundsInvestorFound() throws Exception {
        Investor testInvestor = new Investor();
        testInvestor.setName("testInvestor");
        testInvestor.setFunds(new ArrayList<>());

        doReturn(testInvestor).when(investorRepository).findByName(testInvestor.getName());

        List<Fund> result = clientService.getInvestorsFunds(testInvestor.getName());

        assertEquals(testInvestor.getFunds(), result);
    }

    @Test
    public void testValidateClientTypeInternationalWithPhoneNumber() throws Exception {
        Client testClient = new Client();
        testClient.setType(Client.clientType.INTERNATIONAL);
        testClient.setPhoneNumber("test phone number");

        assertDoesNotThrow(() -> clientService.validateClientType(testClient));
    }

    @Test
    public void testValidateClientTypeInternationalWithOutPhoneNumber(){
        Client testClient = new Client();
        testClient.setType(Client.clientType.INTERNATIONAL);

        String result = assertThrows(Exception.class, () -> {
            clientService.validateClientType(testClient);
        }).getMessage();


        assertEquals("ERROR: International clients must have a phone number.", result);
    }

    @Test
    public void testValidateClientTypeNotInternationalWithNoPhoneNumber(){
        Client testClient = new Client();
        testClient.setType(Client.clientType.DOMESTIC);

        assertDoesNotThrow(() -> clientService.validateClientType(testClient));
    }
}
