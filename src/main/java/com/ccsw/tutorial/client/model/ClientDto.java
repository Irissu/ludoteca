package com.ccsw.tutorial.client.model;

/**
 * DTO para la entidad Client.
 */
public class ClientDto {

    /**
     * Identificador único del cliente.
     */
    private Long id;

    /**
     * Nombre del cliente.
     */
    private String name;

    /**
     * Obtiene el identificador del cliente.
     *
     * @return el identificador del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del cliente.
     *
     * @param id el identificador del cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return el nombre del cliente.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param name el nombre del cliente.
     */
    public void setName(String name) {
        this.name = name;
    }
}