package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.exception.ClientNameExists;
import com.ccsw.tutorial.exception.ClientNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client get(Long id) {
        return this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    @Override
    public void save(Long id, ClientDto dto) {
        Client clientNameDetails = this.clientRepository.findByName(dto.getName());
        if (clientNameDetails != null) {
            throw new ClientNameExists("The client already exists");
        }

        Client client;
        client = (id == null) ? new Client() : this.get(id);
        client.setName(dto.getName());

        this.clientRepository.save(client);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null || clientRepository.findById(id).isEmpty()) {
            throw new Exception("The client doesn't exist");
        } else {
            this.clientRepository.deleteById(id);
        }
    }
}
