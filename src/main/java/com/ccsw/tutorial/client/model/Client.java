package com.ccsw.tutorial.client.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un cliente en el sistema.
 */
@Entity
@Table(name = "client")
public class Client {

    /**
     * Identificador único del cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre del cliente.
     */
    @Column(name = "name", nullable = false)
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