package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Loan", description = "API for Loan")
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")

public class LoanController {

    // Autowired del service
    @Autowired
    LoanService loanService;

    @Autowired
    ModelMapper mapper;

    /*
    Metodo para recuperar listado paginado de {Loan}
    param dto de busqueda (LoanSearchDto)
    return page de LoanDto
     */
    @Operation(summary = "Find", description = "Method that return a list of Loans")
    @GetMapping("")
    public List<LoanDto> findAll() {
        List<Loan> loans = this.loanService.findAll();
        return loans.stream().map(e -> mapper.map(e, LoanDto.class)).collect(Collectors.toList());
    }

    @Operation(summary = "Find Page", description = "Method that return a Page of Loans")
    @PostMapping
    public Page<LoanDto> findPage(@RequestBody LoanSearchDto dto) {
        Page<Loan> page = this.loanService.findPage(dto);
        return new PageImpl<>(page.getContent().stream().map(e -> mapper.map(e, LoanDto.class)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());

    }

    @Operation(summary = "Save", description = "Method that saves or updates a Loan")
    @PutMapping
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody LoanDto dto) {
        this.loanService.save(id, dto);
    }

    @Operation(summary = "Delete", description = "Method that deletes a Loan")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.loanService.delete(id);
    }

}
