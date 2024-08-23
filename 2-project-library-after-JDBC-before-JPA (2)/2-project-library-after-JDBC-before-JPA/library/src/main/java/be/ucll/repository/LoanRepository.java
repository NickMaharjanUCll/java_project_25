package be.ucll.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.Loan;
import be.ucll.model.Publication;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {

    List<Loan> findByUserEmail(String email);

    List<Loan> findByUserEmailAndEndDateIsNull(String email);


    String deleteByUserEmail(String email);


    List<Loan> findByUserEmailAndEndDateAfter(String email, LocalDate date);


}