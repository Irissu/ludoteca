package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    /**
     * Recupera un préstamo a través de su ID.
     *
     * @param id PK de la entidad
     * @return el préstamo correspondiente al ID proporcionado
     */
    @Override
    public Loan get(Long id) {
        return this.loanRepository.findById(id).orElse(null);
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
        return this.loanRepository.findAll(dto.getPageable().getPageable());
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

        BeanUtils.copyProperties(dto, loan, "id"); // copiamos las propiedades del dto que llega en loan(entity), menos id

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
