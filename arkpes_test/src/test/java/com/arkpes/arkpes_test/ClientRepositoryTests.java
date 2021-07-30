package com.arkpes.arkpes_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class ClientRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    final String testClient1Name = "test 1";
    final String testClient2Name = "test 2";
    final String testClient3Name = "test 3";

    @BeforeEach
    private void testInit(){
        persistTestClient(initFullTestClient("1", Client.clientType.DOMESTIC));
        persistTestClient(initFullTestClient("2", Client.clientType.INTERNATIONAL));
        persistTestClient(initFullTestClient("3", Client.clientType.INTERNATIONAL));
    }

    @Test
    public void testFindAll() {
        List<Client> result = clientRepository.findAll();

        assertEquals(3, result.size());
        assertEquals(testClient1Name, result.get(0).getName());
        assertEquals(testClient2Name, result.get(1).getName());
        assertEquals(testClient3Name, result.get(2).getName());
    }

    @Test
    public void testFindByNameClientFound() {
        Client result = clientRepository.findByName(testClient1Name);

        assertEquals(testClient1Name, result.getName());
    }

    @Test
    public void testFindByNameClientNotFound() {
        Client result = clientRepository.findByName("test not found");

        assertNull(result);
    }

    @Test
    public void testFindByType() {
        List<Client> result = clientRepository.findByType(Client.clientType.INTERNATIONAL);

        assertEquals(2, result.size());
        assertEquals(Client.clientType.INTERNATIONAL, result.get(0).getType());
        assertEquals(Client.clientType.INTERNATIONAL, result.get(1).getType());
    }

    @Test
    public void testFindByDescription() {
        List<Client> result = clientRepository.findByDescription("test 2 description");

        assertEquals(1, result.size());
        assertEquals("test 2 description", result.get(0).getDescription());
    }

    @Test
    public void testFindByPhoneNumber() {
        List<Client> result = clientRepository.findByPhoneNumber("# 1");

        assertEquals(1, result.size());
        assertEquals("# 1", result.get(0).getPhoneNumber());
    }

    @Test
    public void testSaveNewClient() {
        Client testClient = initFullTestClient("4", Client.clientType.INTERNATIONAL);
        Client saveResult = clientRepository.save(testClient);

        assertNotNull(saveResult.getId());

        Optional<Client> foundResult = clientRepository.findById(saveResult.getId());

        assertTrue(foundResult.isPresent());
        assertEquals(testClient, foundResult.get());
    }

    @Test
    public void testSaveUpdateClient() {
        Client testClient = clientRepository.findById(1).get();
        testClient.setName("Updated");
        Client saveResult = clientRepository.save(testClient);

        assertNotNull(saveResult.getId());

        Optional<Client> foundResult = clientRepository.findById(saveResult.getId());

        assertTrue(foundResult.isPresent());
        assertEquals(testClient.getName(), foundResult.get().getName());
    }

    private Client initFullTestClient(String order, Client.clientType clientType){
        Fund testFund = new Fund();
        testFund.setAmount(Math.random());

        Investor testInvestor = new Investor();
        testInvestor.setFunds(Collections.singletonList(testFund));
        testInvestor.setName("test " + order + " investor");

        Client testClient = new Client(
                "test " + order,
                "test " + order + " description",
                "# " + order,
                clientType,
                Collections.singletonList(testInvestor));

        return testClient;
    }

    private void persistTestClient(Client client){
        entityManager.persistAndFlush(client.getInvestors().get(0).getFunds().get(0));
        entityManager.persistAndFlush(client.getInvestors().get(0));
        entityManager.persistAndFlush(client);
    }
}