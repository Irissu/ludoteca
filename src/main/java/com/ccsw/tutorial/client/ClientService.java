package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import java.util.List;

public interface ClientService {

    // Recupera un cliente a partir de su ID
    Client get(Long id);
    
    // Recupera todos los clientes
    List<Client> findAll();

    // save()
    void save(Long id, ClientDto dto);

    //delete()
    void delete(Long id) throws Exception;
}
