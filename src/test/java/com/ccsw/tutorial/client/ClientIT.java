package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// test de integración: test a un determinado endpoint que conlleva inicializacion de Spring (bbdd incluida) y hacen llamada RESt para comprobar flujo completo de API

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //  inicializar el contexto de Spring cada vez que se inician los test de jUnit
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // test transaccionales, cuando termina cada test,  Spring hace reset parcial dejará la BBDD como estaba inicialmente.
public class ClientIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/client";

    public static final Long DELETE_CLIENT_ID = 6L;
    public static final Long MODIFY_CLIENT_ID = 3L;
    public static final String NEW_CLIENT_NAME = "Sirius Black";

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<ClientDto>> responseType = new ParameterizedTypeReference<List<ClientDto>>() {
    }; // definimos un tipo generico, en este caso especificamos que la respuesta es una lista de objetos ClientDto, si no lo usaramos Spring intentaría deserializar la respuesta en un List genérico, y obtendrías una lista tipo Object

    /*
     @Test
    void shouldReturnACashCardWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/client/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
     */

    //    Returns a client that already exists

    // Returns a client when is saved
    //    @Test
    //    public void shouldReturnAClientWhenDataIsSaved() {
    //
    //    }
    @Test
    public void findAllShouldReturnAllClients() {
        ResponseEntity<List<ClientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(5, response.getBody().size());
    }

    @Test
    public void createWithNameAlreadyExistsShouldException() {
        // sin codigo aun

    }

    @Test
    public void saveWithoutIdShouldCreateNewClient() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(NEW_CLIENT_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(clientDto), Void.class);
        ResponseEntity<List<ClientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(6, response.getBody().size());
    }

    @Test
    public void modifyWithNotExistIdShouldInternalError() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(NEW_CLIENT_NAME); // ¿ no se supone que al crear un cliente se le asigna un ID automatico?

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + 6L, HttpMethod.PUT, new HttpEntity<>(clientDto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteClient() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + 5L, HttpMethod.DELETE, null, Void.class);

        ResponseEntity<List<ClientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(4, response.getBody().size());

    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {
        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + 10L, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}
