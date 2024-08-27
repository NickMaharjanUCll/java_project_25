// package be.ucll.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.Set;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;

// import jakarta.validation.ConstraintViolation;
// import jakarta.validation.Validation;
// import jakarta.validation.Validator;
// import jakarta.validation.ValidatorFactory;

// public class BookTest {
//     private String info = "Number of input violations: ";
//     private static ValidatorFactory validatorFactory;
//     private static Validator validator;

//     public BookTest() {
//         validatorFactory = Validation.buildDefaultValidatorFactory();
//         validator = validatorFactory.getValidator();
//     }

//     @BeforeAll
//     public static void createValidator() {
//         validatorFactory = Validation.buildDefaultValidatorFactory();
//         validator = validatorFactory.getValidator();
//     }

//     @Test
//     public void givenValidValues_whenCallingBookConstructor_thenMagazineIsCreatedWithThoseValues(){
//         Book book = new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2004, 5);

//         assertEquals("bookTitle", book.getTitle());
//         assertEquals("bookAuthor" , book.getAuthor());
//         assertEquals("978-0-545-01022-1", book.getISBN());
//         assertEquals(2004 , book.getPublicationYear());
//         assertEquals(5 , book.getAvailableCopies());

//         book.setAvailableCopies(0);
        
//         assertEquals(0, book.getAvailableCopies());
//     }

//     // // The test is done in the PublicationTest.java using input validation.
//     // @Test
//     // public void givenBlankTitle_whenCallingSetTitle_thenConstraintViolationIsThrown() {
//     //     Exception ex = Assertions.assertThrows(
//     //         DomainException.class,
//     //         () -> new Book("" , "bookAuthor" , "978-0-545-01022-1",2004, 5)
//     //     );
//     //     assertEquals("Title is required.", ex.getMessage());
//     // }

//     @Test
//     public void givenNullOrBlankAuthor_whenCallingSetAuthor_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             ()-> new Book("bookTitle" , "" , "978-0-545-01022-1",2004, 5)
//         );
//         assertEquals("Author is required." , ex.getMessage());
//     }

//     @Test
//     public void givenBlankISBN_whenCallingSetISBN_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class, 
//             () -> new Book("bookTitle" , "bookAuthor" , "   ", 2004, 5)
//         );
//         assertEquals("ISBN is required.", ex.getMessage());
//     }

//     @Test
//     public void givenNullISBN_whenCallingSetISBN_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new Book("bookTitle" , "bookAuthor" , null, 2004, 5)
//         );
//         assertEquals("ISBN is required.", ex.getMessage());
//     }

//     @Test // Q&A ISBN musn't be null, empty string, string full of spaces. Do we have to test for each of these? Yes.
//     public void givenToShortISBN_whenCallingSetISBN_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-14552",2004, 5)
//         );
//         assertEquals("ISBN should be 13 digits long.", ex.getMessage());
//     }

//     @Test
//     public void givenNonPositivePublicationYear_whenCallingSetPublicationYear_thenConstraintViolationIsThrown() {
//         Book book = new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",-2004, 5);

//         Set<ConstraintViolation<Book>> violations = validator.validate(book);
//         assertEquals(info + 1, info + violations.size());

//         ConstraintViolation<Book> violation = violations.iterator().next();
//         assertEquals("Publication year must be a non-negative integer.", violation.getMessage());

//         // Exception ex = Assertions.assertThrows(
//         //     DomainException.class,
//         //     () -> new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",-2004, 5)
//         // );
//         // assertEquals("Publication year must be a non-negative integer.", ex.getMessage());
//     }

//     @Test
//     public void givenFuturePublicationYear_whenCallingSetPublicationYear_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2050, 5)
//         );
//         assertEquals("Publication year cannot be in the future.", ex.getMessage());
//     }

//     // Q&A Is this what the last technical requirement from story 5. means? A: Forgotten question.
//     @Test
//     public void givenNegativeAvailableCopies_whenCallingSetAvailableCopies_thenConstraintViolationIsThrown() {
//         Book book = new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2004, -23);

//         Set<ConstraintViolation<Book>> violations = validator.validate(book);
//         assertEquals(info + 1, info + violations.size());

//         ConstraintViolation<Book> violation = violations.iterator().next();
//         assertEquals("Available copies must be a non-negative integer.", violation.getMessage());

//         // Exception ex = Assertions.assertThrows(
//         //     DomainException.class,
//         //     () -> new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2004, -23)
//         // );
//         // assertEquals("'Available copies' must be a non-negative integer.", ex.getMessage());
//     }

//     @AfterAll
//     public static void close() {
//         validatorFactory.close();
//     }
// }

// // Q&A Is UML okay? A: It's subjective.

// // Code review:
// //      - "An ISBN is a String that contains 13 digits. Example: 978-0-545-01022-1" Please make tests the same as the examples from technical requirements; if there are examples.
// //      - Which convention for naming exception variables to use: invalidISBNException or exInvalidISBN?