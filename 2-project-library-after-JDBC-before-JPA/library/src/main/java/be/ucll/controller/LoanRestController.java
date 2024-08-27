package be.ucll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.model.DomainException;
import be.ucll.model.Loan;
import be.ucll.model.Publication;
import be.ucll.model.User;
import be.ucll.service.LoanService;

@RestController
@RequestMapping("/users")
public class LoanRestController {
    private LoanService loanService;

    public LoanRestController(LoanService loanService){
        this.loanService = loanService;
    }
    @GetMapping("/{email}/loans")
    public List<Loan> getLoansByUser(@PathVariable String email, @RequestParam(required = false,defaultValue = "false")boolean onlyActive){
        return loanService.getLoansByUser(email, onlyActive);
    }

    @DeleteMapping("/{email}/loans")
    public String deleteLoans(@PathVariable String email) {
        return loanService.deleteLoans(email);
    }

     @PostMapping("/{email}/loans/{startDate}")
    public Loan updatedLoanByEmailandStartDate(@PathVariable(value = "email") String email, @PathVariable(value = "startDate") LocalDate startDate,@RequestBody List<Long> publicationIDs){
        return loanService.updatedLoanByEmailandStartDate(email,startDate,publicationIDs);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DomainException.class})
    public Map<String, String> handleDomainExceptions(DomainException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("DomainException", ex.getMessage());
        return errors;
    }
}
