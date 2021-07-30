package com.arkpes.arkpes_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/getAllClients")
    @CrossOrigin
    public List<Client> getAllClients(
            @RequestParam(required = false, defaultValue = "no_filter") String filterField,
            @RequestParam(required = false) String filterValue){
        return clientService.getAllClients(filterField, filterValue);
    }

    @GetMapping("/getClientsInvestors")
    public List<Investor> getClientsInvestors(@RequestParam String clientName) throws Exception {
        return clientService.getClientsInvestors(clientName);
    }

    @GetMapping("/getInvestorsFunds")
    public List<Fund> getInvestorsFunds(@RequestParam String investorName) throws Exception {
        return clientService.getInvestorsFunds(investorName);
    }

    @PostMapping("/updateClient")
    public Client updateClient(@Valid @RequestBody Client client) throws Exception {
        return clientService.updateClient(client);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
