package com.ccsw.tutorial.client;

// test unitarios: pruebas relativas a la calidad estatica del codigo de una determinada operacion de la capa Service, no trabajan con datos directamente,
// por lo que no inicializan contexto de SpringBoot (@ExtendWith indica a Spring que no debe inicializar el contexto)
/*
 * +INFO: https://www.baeldung.com/mockito-verify
 * Prueba logica de la operacion de consulta de Cliente
 * Prueba logica de la operacion de creacion de Cliente
 * Prueba logica de la operacion de modificacion de Cliente
 * Prueba logica de la operacion de borrado de Cliente
 * */

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

    @Mock// invoca al repository en un test
    private ClientRepository clientRepository;

    @InjectMocks // simula respuesta ficticia
    private ClientServiceImpl clientService;

    //    Returns a list of all clients
    @Test
    public void findAllShouldReturnAllClients() {
        List<Client> list = new ArrayList<>();
        list.add(mock(Client.class)); // añadimos cliente ficticio con mock
        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);
        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(2, clients.size());
    } // puedo hacer otro igual pero que falle

    @Test
    public void getExistsClientIdShouldReturnClient() {
        Client client = mock(Client.class); // cliente falso

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

    // test para save, edit y delete
    @Test
    public void saveNotExistsClientIdShouldInsert() {
        // recuerda que al guardar con save pasamos un Dto que se transformará a Entity
        ClientDto newClientDto = new ClientDto();
        newClientDto.setName("Edward Elric");

        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);
        clientService.save(null, newClientDto);

        verify(clientRepository).save(client.capture());
        assertEquals(newClientDto.getName(), client.getValue().getName());
    }

    @Test
    public void saveExistsClientNameShouldReturnError() {
        // de momento vacio
        // ClientDto newClientDto = new ClientDto();
        // newClientDto.setName("Edward Elric");
        //when(ClientRepository.getName()).thenReturn(Optional.of("Edward Elric"));
    }

    @Test
    public void deleteClientIfItsNullShouldFailWithAnException() throws Exception {
        // de momento vacio
        //        @Test
        //        void deleteActorById_throwsExceptionIfIDNotFound() {
        //            assertThatExceptionOfType(NotFoundException.class)
        //                    .isThrownBy(() -> service.deleteActorById(1L))
        //                    .withMessage("Actor id not found - 1");
        //        }
    }

    @Test
    public void deleteExistsClientIdShouldDelete() throws Exception {
        Client client = mock(Client.class);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        clientService.delete(1L);

        verify(clientRepository).deleteById(1L);
    }
}
