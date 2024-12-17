package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    public static final Long EXISTS_CLIENT_ID = 1L;
    public static final Long NOT_EXISTS_CLIENT_ID = 0L;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void findAllShouldReturnAllClients() {
        List<Client> list = new ArrayList<>();
        list.add(mock(Client.class));
        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);
        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(2, clients.size());
    }

    @Test
    public void getExistsClientIdShouldReturnClient() {
        Client client = mock(Client.class);

        when(client.getId()).thenReturn(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client clientResponse = clientService.get(1L);

        assertNotNull(clientResponse);
        assertEquals(1L, client.getId());
    }

    @Test
    public void getNotExistsClientIdShouldReturnNull() {
        Client client = mock(Client.class);

        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        Client clientResponse = clientService.get(99L);
        assertNull(clientResponse);
    }

    @Test
    public void saveNotExistsClientIdShouldInsert() {
        ClientDto newClientDto = new ClientDto();
        newClientDto.setName("Edward Elric");

        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);
        clientService.save(null, newClientDto);

        verify(clientRepository).save(client.capture());
        assertEquals(newClientDto.getName(), client.getValue().getName());
    }

    @Test
    public void deleteExistsClientIdShouldDelete() throws Exception {
        Client client = mock(Client.class);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        clientService.delete(1L);

        verify(clientRepository).deleteById(1L);
    }
}
