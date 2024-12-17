package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.exception.LoanNotFoundException;
import com.ccsw.tutorial.exception.LoanNotValidDateException;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    /**
     * Recupera un préstamo a través de su ID.
     *
     * @param id PK de la entidad
     * @return el préstamo correspondiente al ID proporcionado
     */
    @Override
    public Loan get(Long id) {

        return this.loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {

        return (List<Loan>) this.loanRepository.findAll();
    }

    /**
     * Encuentra una página de préstamos basada en los criterios de búsqueda.
     *
     * @param dto objeto que contiene los criterios de búsqueda y paginación
     * @return una página de préstamos que coinciden con los criterios de búsqueda
     */
    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {
        LocalDate[] dates = null;
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (dto.getDate() != null) {
            date = LocalDate.parse(dto.getDate(), formatter);
        }

        if (dto.getDates() != null && dto.getDates().length == 2) {
            dates = new LocalDate[] { LocalDate.parse(dto.getDates()[0], formatter), LocalDate.parse(dto.getDates()[1], formatter) };
        }

        LoanSpecification gameSpec = new LoanSpecification(new SearchCriteria("game.id", ":", dto.getIdGame()));
        LoanSpecification clientSpec = new LoanSpecification(new SearchCriteria("client.id", ":", dto.getIdClient()));
        LoanSpecification dateGreaterThanSpec = new LoanSpecification(new SearchCriteria("returnDate", ">=", date));
        LoanSpecification dateLessThanSpec = new LoanSpecification(new SearchCriteria("loanDate", "<=", date));

        Specification<Loan> spec = Specification.where(gameSpec).and(clientSpec).and(dateGreaterThanSpec).and(dateLessThanSpec);

        return this.loanRepository.findAll(spec, dto.getPageable().getPageable());
      
    }

    /**
     * Guarda o actualiza un préstamo.
     *
     * @param id  PK de la entidad, puede ser null para crear un nuevo préstamo
     * @param dto objeto que contiene los datos del préstamo a guardar
     */
    @Override
    public void save(Long id, LoanDto dto) {
        Loan loan;

        loan = (id == null) ? new Loan() : this.get(id);

        BeanUtils.copyProperties(dto, loan, "id", "game", "client");

        if (dto.getGame() != null && dto.getGame().getId() != null) {
            loan.setGame(gameService.get(dto.getGame().getId()));
        } else {
            throw new IllegalArgumentException("Game id can't be null");
        }

        if (dto.getClient() != null && dto.getClient().getId() != null) {
            loan.setClient(clientService.get(dto.getClient().getId()));
        } else {
            throw new IllegalArgumentException("Client id can't be null");
        }
        if (dto.getLoanDate() != null && dto.getReturnDate() != null) {
            if (dto.getReturnDate().isBefore(dto.getLoanDate())) {
                throw new LoanNotValidDateException("Return date can't be before loan date");
            }
        }

        if (dto.getLoanDate() != null && dto.getReturnDate() != null) {
            if (dto.getLoanDate().plusDays(14).isBefore(dto.getReturnDate())) {
                throw new LoanNotValidDateException("Loan can't be more than 14 days");
            }
        }

        if (!loanRepository.findLoanByGameAndDates(loan.getGame().getId(), loan.getLoanDate(), loan.getReturnDate()).isEmpty()) {
            throw new LoanNotValidDateException("Game already loaned in this date range");
        }

        Long loansByClient = loanRepository.countLoanByClientAndDates(loan.getClient().getId(), loan.getLoanDate(), loan.getReturnDate());
        if (loansByClient > 2) {
            throw new LoanNotValidDateException("Client can't loan more than 2 games in this date range");
        }

        this.loanRepository.save(loan);
    }

    @Override
    /**
     * Elimina un préstamo a través de su ID.
     *
     * @param id PK de la entidad
     */ public void delete(Long id) {
        this.loanRepository.deleteById(id);
    }
}
