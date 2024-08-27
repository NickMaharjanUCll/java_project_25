// package be.ucll.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;

// public class UserTest {

//     // Happy paths.
//     @Test
//     public void givenValidValues_whenCallingUserConstructor_thenUserIsCreatedWithThoseValues() {
//         User user = new User("John Doe", 56, "john.doe@ucll.be", "john1234");
//         assertEquals("John Doe", user.getName());
//         assertEquals(56, user.getAge());
//         assertEquals("john.doe@ucll.be", user.getEmail());
//         assertEquals("john1234", user.getPassword());
//     }

//     // Unhappy paths.
//     @Test
//     public void givenNameIsNull_whenCallingSetName_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User(null, 56, "john.doe@ucll.be", "john1234")
//         );
//         assertEquals("Name is required.", ex.getMessage());
//     }

//     @Test
//     public void givenNameIsEmpty_whenCallingSetName_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User("", 56, "john.doe@ucll.be", "john1234")
//         );
//         assertEquals("Name is required.", ex.getMessage());
//     }

//     @Test
//     public void givenNameContainsOnlySpaces_whenCallingSetName_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User("     ", 56, "john.doe@ucll.be", "john1234")
//         );
//         assertEquals("Name is required.", ex.getMessage());
//     }

//     @Test
//     public void givenInvalidEmail_whenCallingSetEmail_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User("John Doe", 56, "johndoeucllbe", "john1234")
//         );
//         assertEquals("E-mail must be a valid email format.", ex.getMessage()); // That's not grammatically correct, but this is how the story states.
//     }

//     @Test
//     public void givenPasswordWithLessThan8Characters_whenCallingSetPassword_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User("John Doe", 56, "john.doe@ucll.be", "john123")
//         );
//         assertEquals("Password must be at least 8 characters long.", ex.getMessage());
//     }

//     @Test
//     public void givenAge0_whenCreatingOrEditingUser_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User("John Doe", 0, "john.doe@ucll.be", "john1234")
//         );
//         assertEquals("Age must be a positive integer between 0 and 101.", ex.getMessage());
//     }

//     @Test
//     public void givenAge101_whenCreatingOrEditingUser_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User("John Doe", 101, "john.doe@ucll.be", "john1234")
//         );
//         assertEquals("Age must be a positive integer between 0 and 101.", ex.getMessage());   
//     }

//     @Test
//     public void givenAgeMinus15_whenCreatingOrEditingUser_thenDomainExceptionIsThrown() {
//         Exception ex = Assertions.assertThrows(
//             DomainException.class,
//             () -> new User("John Doe", -15, "john.doe@ucll.be", "john1234")
//         );
//         assertEquals("Age must be a positive integer between 0 and 101.", ex.getMessage());   
//     }

    
// }

// // DONE One assert equals per test.
// // Q&A Should we test the error message? Yes.
// // Q&A Do we have to write @Test each time? Yes
// // Q&A Should I create a method for each exception? Create a method for each field that you're testing.
// // Q&A A name can be an empty string according to technical details. Is that correct? I added it anyways. That's correct.