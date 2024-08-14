package be.ucll.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import be.ucll.model.Loan;
import be.ucll.repository.LoanRepository;
import be.ucll.repository.PublicationRepository;
import be.ucll.repository.UserRepository;
import be.ucll.service.LoanService;
import be.ucll.service.ServiceException;
import be.ucll.unit.repository.UserRepositoryTestImpl;

public class LoanServiceTest {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    UserRepository userRepository = new UserRepositoryTestImpl();
    PublicationRepository publicationRepository = new PublicationRepository();
    LoanRepository loanRepository = new LoanRepository();
    LoanService loanService = new LoanService(userRepository, loanRepository);

    @Test
    public void givenExistingEmailInRequestAndMatchingLoansInRepository_whenCallingGetLoansByUser_thenLoansOfThatUserAreReturned() {
        List<Loan> exceptedLoans = loanRepository.findLoansByUserEmail("john.doe@ucll.be", false) ;

        assertEquals(true,exceptedLoans!=null && !exceptedLoans.isEmpty());
    }

    @Test
    public void givenExistingEmailInRequestAndNoMatchingLoansInRepository_whenCallingGetLoansByUser_thenEmptyListIsReturned() {
        LoanRepository loanRepository = new LoanRepository();
        List<Loan> exceptedLoans = loanRepository.findLoansByUserEmail("john.doe@ucll.be", true);

        assertEquals(0, exceptedLoans.size());
    }

    @Test
    public void
    givenExistingEmailInRequestAndMatchingActiveLoansInRepository_whenCallingGetLoansByUserWithOnlyActiveParameterSetToTrue_thenOnlyLoansWithNullEndDateOfThatUserAreReturned() {
        List<Loan> exceptedLoans = loanRepository.findLoansByUserEmail("john.doe@ucll.be", true);
        for(Loan loan : exceptedLoans){
            assertEquals(
                true,
                loan.getEndDate() == null || loan.getEndDate().isAfter(LocalDate.now())
            );
        }
    }

    @Test
    public void givenNonExistingEmailInRequest_whenCallingGetLoansByUser_thenServiceExceptionIsThrown() {
        Exception ex = Assertions.assertThrows(
            ServiceException.class,
            () -> loanService.getLoansByUser("john.deer@ucll.be", false)
        );
        assertEquals("User does not exist.", ex.getMessage());
    }

    @Test
    public void givenUserWithProvidedEmailDoesNotExist_whenCallingDeleteLoans_thenServiceExceptionIsThrown() {
        Exception ex = Assertions.assertThrows(
            ServiceException.class,
            () -> loanService.deleteLoans("john.deer@ucll.be")
        );
        assertEquals("User does not exist.", ex.getMessage());
    }

    @Test
    public void givenUserHasActiveLoans_whenCallingDeleteLoans_thenServiceExceptionIsThrown() {
        Exception ex = Assertions.assertThrows(
            ServiceException.class,
            () -> loanService.deleteLoans("jack.doe@ucll.be")
        );
        assertEquals("User has active loans.", ex.getMessage());
    }

    @Test
    public void givenUserHasNoLoans_whenCallingDeleteLoans_thenServiceExceptionIsThrown() {
        Exception ex = Assertions.assertThrows(
            ServiceException.class,
            () -> loanService.deleteLoans("birgit.doe@ucll.be")
        );
        assertEquals("User has no loans.", ex.getMessage());
    }

    @Test
    public void givenValidParameters_whenCallingDeleteLoans_thenAppropriateLoansAreDeleted() {
        loanService.deleteLoans("john.doe@ucll.be");
        assertEquals(new ArrayList<>(), loanService.getLoansByUser("john.doe@ucll.be", false));
    }

    @Test
    public void givenValidParameters_whenCallingDeleteLoans_thenMessageIndicatingSuccessIsReturned() {
        assertEquals("Loans of user successfully deleted.", loanService.deleteLoans("john.doe@ucll.be"));
    }
}


