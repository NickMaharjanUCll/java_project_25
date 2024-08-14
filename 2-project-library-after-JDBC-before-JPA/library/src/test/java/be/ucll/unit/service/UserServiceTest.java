package be.ucll.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import be.ucll.model.DomainException;
import be.ucll.model.User;
import be.ucll.repository.LoanRepository;
import be.ucll.repository.UserRepository;
import be.ucll.service.ServiceException;
import be.ucll.service.UserService;
import be.ucll.unit.repository.UserRepositoryTestImpl;
//Q& What unhappy paths should be test here?
public class UserServiceTest {
    // Q&A The code below appears in all the tests, so I moved it out not to repeat itself. Ok? A: OK.
    // Q&A Is it okay that we use package private access modifier? A: Preferably private access modifiers!
    private UserRepository userRepository = new UserRepositoryTestImpl();
    private LoanRepository loanRepository;
    private UserService userService = new UserService(userRepository, loanRepository);

    // Q&A Which one to choose, best clarity?
    // givenGetAllUsers_whenUsersExists_thenTheyAreReturned()
    // givenGetAllUsersAndUsersExist_whenCallingGetAllUsers_thenAllUsersAreReturned() 
    // givenUsersExist_whenCallingGetAllUsers_thenAllUsersAreReturned() <<---------------- Choosen by Bram Van Impe --------------
    // givenRequestForAllUsers_whenCallingGetAllUsers_thenAllUsersAreReturned()
    // givenDemandForAllUsers_whenCallingGetAllUsers_thenAllUsersAreReturned()
    // givenNeedForAllUsers_whenCallingGetAllUsers_thenAllUsersAreReturned()
    
    // givenValidUsers_whenCallingGetAllUsers_thenAllUsersAreReturned()

    @Test
    // public void givenUsersExist_whenCallingGetAllUsers_thenUsersAreReturned() {
    // Q&A Why are we not handling givenInvalidUsers_when...? A: Unanswered question.
    public void givenUsersInRepository_whenCallingGetAllUsers_thenUsersAreReturned() {
        // This list has to match the dummy data from UserRepository.
        List<User> expectedUsers = new ArrayList<>(
            List.of(
                new User("John Doe", 25, "john.doe@ucll.be", "john1234"),
                new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234"),
                new User("Jack Doe", 5, "jack.doe@ucll.be", "jack1234"),
                new User("Sarah Doe", 4, "sarah.doe@ucll.be", "sarah1234"),
                new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234")
            )
        );
        List<User> receivedUsers = userService.getAllUsers();

        assertEquals(
                "Users list size: " + expectedUsers.size(),
                "Users list size: " + receivedUsers.size()
        );
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i), receivedUsers.get(i)); 
        }
    }

    // This list has to match the dummy data from UserRepository.
    List<User> expectedAdultUsers = new ArrayList<>(
        List.of(
            new User("John Doe", 25, "john.doe@ucll.be", "john1234"),
            new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234"),
            new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234")
        )
    );
    @Test
    // Q&A Is this name okay? Yes.
    public void givenUsersInRepository_whenCallingGetAdultUsers_thenUsersOlderThanOrEqualTo18AreReturned() {
        List<User> receivedAdultUsers = userService.getAllAdultUsers();

        // Q&A Should these two be separated? A: It's okay.
        assertEquals(
                "Adult users list size: " + expectedAdultUsers.size(),
                "Adult users list size: " + receivedAdultUsers.size()
        );
        for (int i = 0; i < expectedAdultUsers.size(); i++) {
            assertEquals(expectedAdultUsers.get(i), receivedAdultUsers.get(i));
        }
    }

    // Also test for empty list.
    @Test
    public void givenValidAgeRange_whenCallingGetAllUsersInAgeRange_thenAllUsersInThatAgeRangeAreReturned() {
        assertEquals(expectedAdultUsers, userService.getAllUsersInAgeRange(18, 100));
        assertEquals(0, userService.getAllUsersInAgeRange(100, 150).size()); // Empty list test.
        // Q& Should we always test for empty lists?
    }

    @Test
    public void givenMinIsGreaterThanMax_whenCallingGetAllUsersInAgeRange_thenServiceExceptionIsThrown() {
        Exception ex = Assertions.assertThrows(
            ServiceException.class,
            () -> userService.getAllUsersInAgeRange(11, 10)
        );
        assertEquals("Minimum age cannot be greater than maximum age.", ex.getMessage());
    }

    @Test
    public void givenMinOrMaxOutOfRange_whenCallingGetAllUsersInAgeRange_thenServiceExceptionIsThrown() {
        Exception exAgeOutOfRange = Assertions.assertThrows(
            ServiceException.class,
            () -> userService.getAllUsersInAgeRange(-100, 500) // Q& Why does VSCode not show 'min:'?
        );
        assertEquals("Invalid age range. Age must be between 0 and 150.", exAgeOutOfRange.getMessage());
    }
    
   @Test
   public void givenValidUsers_whenCallingUsersByName_thenUsersAreReturned(){
    List<User> expectedUsers = new ArrayList<>(
        List.of(
            new User("John Doe", 25, "john.doe@ucll.be", "john1234"),
            new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234"),
            new User("Jack Doe", 5, "jack.doe@ucll.be", "jack1234"),
            new User("Sarah Doe", 4, "sarah.doe@ucll.be", "sarah1234"),
            new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234")
        )
    );
    List<User> receivedUsers = userService.getAllUsers();

    for(User expectedUser : expectedUsers){
        assertTrue(receivedUsers.contains(expectedUser));
    }
    
   }

   @Test 
   public void givenInvalidUsers_whenCallingUsersByName_thenServiceExceptioNIsThrown(){
    Exception exception = Assertions.assertThrows(ServiceException.class,()->userService.getAllUsersByName("nonExistentUserName"));
    assertEquals("No users found with the specified name", exception.getMessage());
   }

   @Test 
   public void givenUserDoesNotExist_whenCallingAddUser_thenUserIsAddedAndReturned(){
    //Q&A Why doesn't this test fail when the email which already exists in the list is passed? You;re calling userRepository.
    User user = new User("John Doe", 25, "notexists@ucll.be", "john1234");

    assertEquals(user, userService.addUser(user));
    assertEquals(true, userService.getAllUsers().contains(user));
   }

   @Test
   public void givenUserExists_whenCallingAddUser_thenServiceExceptionIsThrown(){
    User user = new User("John Doe", 25, "john.doe@ucll.be", "john1234");

    Exception userAlreadyExistsException = Assertions.assertThrows(
        ServiceException.class,
        () -> userService.addUser(user)
    );
    assertEquals("User already exists.", userAlreadyExistsException.getMessage());
   }

   @Test
   public void givenEmailDoesNotExist_whenCallingUpdateUser_thenServiceExceptionIsThrown() {
    User userFromRequest = new User("John Doe", 25, "john.deer@ucll.be", "john1234");
    String emailFromRequest = "x@ucll.be";

    Exception ex = Assertions.assertThrows(
        ServiceException.class,
        () -> userService.updateUser(emailFromRequest, userFromRequest)
    );
    assertEquals("User does not exist.", ex.getMessage());  

   }

   @Test
   public void givenUserEmailThatDoesNotMatchEmailInTheRequestBody_whenCallingUpdateUser_thenDomainExceptionIsThrown() {
    User userFromRequest = new User("John Doe", 25, "john.deer@ucll.be", "john1234");
    String emailFromRequest = "john.doe@ucll.be";

    Exception ex = Assertions.assertThrows(
        DomainException.class,
        () -> userService.updateUser(emailFromRequest, userFromRequest)
    );
    assertEquals("Email cannot be changed.", ex.getMessage());

   }

   @Test
   public void givenValidEmailAndBody_whenCallingUpdateUser_thenUserIsUpdatedAndReturned() {
    User userFromRequest = new User("John Doe", 25, "john.doe@ucll.be", "john1234");
    String emailFromRequest = "john.doe@ucll.be";
    
    assertEquals(userFromRequest, userService.updateUser(emailFromRequest, userFromRequest));
   }
}

// Q&A Should we always write validation for null values? A: Unanswered.
// Q& Did I implement all the happy and unhappy paths? Correctly?
// Q&A All String fields must not be: null, empty, or just spaces. Is that correct? There must be appropriate error and test for each case? Yes, but it also depends on the field.
// Q&A Story 6. "In case of a server error, an appropriate error message is returned." You may ignore it.