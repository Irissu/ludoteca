package com.ccsw.tutorial.loan.model;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.game.model.Game;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entidad que representa un préstamo en el sistema.
 */
@Entity
@Table(name = "loan")
public class Loan {

    /**
     * Identificador único del préstamo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Juego asociado al préstamo.
     */
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    /**
     * Cliente asociado al préstamo.
     */
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    /**
     * Fecha de inicio del préstamo.
     */
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    /**
     * Fecha de devolución del préstamo.
     */
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    /**
     * Constructor por defecto.
     */
    public Loan() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id         Identificador del préstamo.
     * @param game       Juego asociado al préstamo.
     * @param client     Cliente asociado al préstamo.
     * @param loanDate   Fecha de inicio del préstamo.
     * @param returnDate Fecha de devolución del préstamo.
     */
    public Loan(Long id, Game game, Client client, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.game = game;
        this.client = client;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    /**
     * Obtiene el identificador del préstamo.
     *
     * @return el identificador del préstamo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del préstamo.
     *
     * @param id el identificador del préstamo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el juego asociado al préstamo.
     *
     * @return el juego asociado al préstamo.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Establece el juego asociado al préstamo.
     *
     * @param game el juego asociado al préstamo.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Obtiene el cliente asociado al préstamo.
     *
     * @return el cliente asociado al préstamo.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Establece el cliente asociado al préstamo.
     *
     * @param client el cliente asociado al préstamo.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Obtiene la fecha de inicio del préstamo.
     *
     * @return la fecha de inicio del préstamo.
     */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /**
     * Establece la fecha de inicio del préstamo.
     *
     * @param loanDate la fecha de inicio del préstamo.
     */
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Obtiene la fecha de devolución del préstamo.
     *
     * @return la fecha de devolución del préstamo.
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Establece la fecha de devolución del préstamo.
     *
     * @param returnDate la fecha de devolución del préstamo.
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}