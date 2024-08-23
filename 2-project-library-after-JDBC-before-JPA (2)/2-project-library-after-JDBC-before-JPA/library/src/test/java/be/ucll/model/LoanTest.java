// package be.ucll.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Set;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import jakarta.validation.ConstraintViolation;
// import jakarta.validation.Validation;
// import jakarta.validation.Validator;
// import jakarta.validation.ValidatorFactory;

// // REFERENCES, REFERENCES, REFERENCES!!!

// public class LoanTest {
//     private User user;
//     private LocalDate startDate;
//     private LocalDate endDate;
//     private List<Publication> publications;
//     private String info = "Number of input violations: ";

//     private static ValidatorFactory validatorFactory;
//     private static Validator validator;

//     @BeforeAll
//     public static void createValidator() {
//         validatorFactory = Validation.buildDefaultValidatorFactory();
//         validator = validatorFactory.getValidator();
//     }

//     @BeforeEach
//     public void resetData() {
//         user = new User("John Doe", 56, "john.doe@ucll.be", "john1234");
//         endDate = LocalDate.now();
//         startDate = endDate.minusDays(5);
//         publications = new ArrayList<>(List.of(
//             new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2004, 5),
//             new Book("bookTitle2" , "bookAuthor2" , "978-0-545-01022-1",2004, 6),
//             new Book("bookTitle3" , "bookAuthor3" , "978-0-545-01022-3",2004, 1)
//         ));
//     }

//     @Test
//     public void givenValidValues_whenCallingLoanConstructor_thenLoanIsCreatedWithThoseValues() {  
//         User userInLoan = new User("John Doe", 56, "john.doe@ucll.be", "john1234");
//         LocalDate endDateInLoan = LocalDate.now();
//         LocalDate startDateInLoan = endDate.minusDays(5);
//         // Q&A How to make a deep copy of an ArrayList? What do you prefer? This is not needed here.
//         List<Publication> publicationsInLoan = new ArrayList<>(List.of(
//             new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2004, 5),
//             new Book("bookTitle2" , "bookAuthor2" , "978-0-545-01022-1",2004, 6),
//             new Book("bookTitle3" , "bookAuthor3" , "978-0-545-01022-3",2004, 1)
//         ));
//         Loan loan = new Loan(user, publicationsInLoan, startDate, endDate);
        
//         assertEquals(userInLoan, loan.getUser());
//         // assertEquals(publicationsInLoan, loan.getPublications()); // AvailableCopies is tested in the loop below.
//         assertEquals(startDateInLoan, loan.getStartDate());
//         assertEquals(endDateInLoan, loan.getEndDate());

//         for (int i = 0; i < publications.size(); i++) {
//             assertEquals(
//                 publications.get(i).getAvailableCopies() - 1,
//                 publicationsInLoan.get(i).getAvailableCopies()
//             );
//         }
        
//         // // Keep in mind that Loan contains references of objects! These assertEquals compare same references!
//         // assertEquals(user, loan.getUser());
//         // assertEquals(publications, loan.getPublications()); 
//         // assertEquals(startDate, loan.getStartDate());
//         // assertEquals(endDate, loan.getEndDate());
//     }

//     @Test
//     public void givenNullUser_whenCallingSetUser_thenConstraintViolationIsThrown() {
//         Loan loan = new Loan(null, publications, startDate, endDate);

//         Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
//         assertEquals(info + 1, info + violations.size());

//         ConstraintViolation<Loan> violation = violations.iterator().next();
//         assertEquals("A loan must be associated with an user.", violation.getMessage());
//     }

//     @Test
//     public void givenEmptyOrNullPublications_whenCallingSetPublications_thenDomainExceptionIsThrown() {
//         Loan loan = new Loan(user, null, startDate, endDate);

//         Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
//         assertEquals(info + 1, info + violations.size());

//         ConstraintViolation<Loan> violation = violations.iterator().next();
//         assertEquals("A loan must be associated with one or more publications.", violation.getMessage());
//     }

//     @Test
//     public void givenAvailableCopiesOfAtLeastOnePublicationIs0_whenCallingSetPublications_thenOtherPublicationsRemainUnchangedAndDomainExceptionIsThrown(){
//         publications = new ArrayList<>(List.of(
//             new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2004, 5),
//             new Book("bookTitle2" , "bookAuthor2" , "978-0-545-01022-1",2004, 0),
//             new Book("bookTitle3" , "bookAuthor3" , "978-0-545-01022-3",2004, 1)
//         ));

//         // DomainException is thrown.
//         Exception ex = Assertions.assertThrows(
//             DomainException.class, 
//             () -> new Loan(user, publications, startDate, endDate)
//         );
//         for (Publication publication : publications) {
//             if (publication.getAvailableCopies() <= 0) {
//                 assertEquals(
//                     "Unable to lend publication. No copies available for " + publication.getTitle() + ".",
//                     ex.getMessage()
//                 );
//             }
//             break;
//         }

//         // Other Publications remain unchanged.
//         assertEquals(5, publications.get(0).getAvailableCopies());
//         assertEquals(0, publications.get(1).getAvailableCopies());
//         assertEquals(1, publications.get(2).getAvailableCopies());
//     }

//     @Test
//     public void givenNullStartDate_whenCallingSetStartDate_thenViolationConstraintIsThrown() {
//         // user, publications, endDate are defined with a @BeforeEach method.
//         Loan loan = new Loan(user, publications, null, endDate);

//         Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
//         assertEquals(info + 1, info + violations.size());

//         ConstraintViolation<Loan> violation = violations.iterator().next();
//         assertEquals("Start date is required.", violation.getMessage());
//     }

//     @Test
//     public void givenFutureStartDate_whenCallingSetStartDate_thenConstraintViolationIsThrown() {
//         startDate = LocalDate.now().plusDays(5);
//         // user, publications are predefined somewhere else.
//         Loan loan = new Loan(user, publications, startDate, null);

//         Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
//         assertEquals(info + 1, info + violations.size());

//         ConstraintViolation<Loan> violation = violations.iterator().next();
//         assertEquals("Start date cannot be in the future.", violation.getMessage());
//     }

//     @Test
//     public void givenStartDateAfterEndDate_whenCallingSetEndDate_thenDomainExceptionIsThrown() {        
//         endDate = LocalDate.now().minusDays(12);
//         startDate = endDate.plusDays(5);

//         Exception ex = Assertions.assertThrows(DomainException.class,()->
//             new Loan(user, publications, startDate, endDate)
//         );
//         assertEquals("Start date cannot be after end date.", ex.getMessage());
//     }

//     @Test // Q&A Shouldn't end date be in the future? No.
//     public void givenFutureEndDate_whenCallingSetEndDate_thenConstraintViolationIsThrown() {
//         endDate = LocalDate.now().plusDays(5);
//         Loan loan = new Loan(user,publications,startDate, endDate);

//         Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
//         assertEquals(info + 1, info + violations.size());

//         ConstraintViolation<Loan> violation = violations.iterator().next();
//         assertEquals("End date cannot be in the future.", violation.getMessage());

//         // endDate = LocalDate.now().plusDays(1);

//         // Exception ex = Assertions.assertThrows(DomainException.class, ()->
//         //     new Loan(user,publications,startDate, endDate)
//         // );
//         // assertEquals("End date cannot be in future.", ex.getMessage());
//     }

//     @AfterAll
//     public static void close() {
//         validatorFactory.close();
//     }
// }

// // Code review:
// //    - Remember to add a dot after the error message.
// //    - Add a test for "No available copies left for publication."