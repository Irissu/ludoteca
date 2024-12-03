package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "client", description = "API for Client") //  ayuda a generar documentación automática
@RequestMapping(value = "/client") // nos permiten definir las rutas de acceso
@RestController // anotacion que indica que  las operaciones son de tipo Rest
@CrossOrigin(origins = "*")

public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    ModelMapper mapper;

    // Metodo que recupera un listado de todos los Clientes
    @Operation(summary = "Find", description = "Method that return all Clients")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClientDto> findAll() {
        //        return new ArrayList<ClientDto>(this.clients.values());
        List<Client> clients = this.clientService.findAll();
        return clients.stream().map(e -> mapper.map(e, ClientDto.class)).collect(Collectors.toList());
    }

    @Operation(summary = "Save or Update", description = "Method that saves or updates a client")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientDto dto) {
        this.clientService.save(id, dto);
    }

    @Operation(summary = "Delete", description = "Method that deletes a client")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) throws Exception { // el servicio lleva exception
        this.clientService.delete(id);
    }

}
