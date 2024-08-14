package be.ucll.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import be.ucll.repository.DbInitializer;
import be.ucll.repository.LoanRepository;
import be.ucll.service.ServiceException;
import be.ucll.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Sql("classpath:schema.sql")

public class UserIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserService userService;
    @Autowired
    private DbInitializer dbInitializer;
    // @Autowired
    // private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;


    @BeforeEach
    public void resetData() {
        // userRepository.resetData();
        // publicationRepository.resetData();
        // Q& Because my loanRepository has no dependencies anymore, it is not reset by dbInitializer. That's why I have to manually call resetData here. Is that okay?
        loanRepository.resetData();
    }

    @BeforeEach
    public void setup() {
        dbInitializer.initialize();
        // System.out.println("\n\n\nResetting Data\n\n\n");
    }

    @Test 
    public void GivenRequestToFetchAllAdults_WhenCallingGetAllAdultUsers_ThenAllAdultUsersAreReturned(){
        webTestClient.get()
            .uri("/users/adults")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody().json("[{\"name\":\"John Doe\",\"age\":25,\"email\":\"john.doe@ucll.be\",\"password\":\"john1234\"},{\"name\":\"Jane Toe\",\"age\":30,\"email\":\"jane.toe@ucll.be\",\"password\":\"jane1234\"},{\"name\":\"Birgit Doe\",\"age\":18,\"email\":\"birgit.doe@ucll.be\",\"password\":\"birgit1234\"}]");
    }

    @Test 
    public void GivenRangeOfAge_WhenCallingUsersInRange_ThenUsersInThatRangeAreReturned(){
        webTestClient.get()
        .uri("/users/age/18/25")
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody().json("[{\"name\":\"Birgit Doe\",\"age\":18,\"email\":\"birgit.doe@ucll.be\",\"password\":\"birgit1234\"},{\"name\":\"John Doe\",\"age\":25,\"email\":\"john.doe@ucll.be\",\"password\":\"john1234\"}]");  
    }

    // TODO: This test is not isolate and conflicts with the last one (DELETE request).
    // TODO: The code for this test relies on the time and fails the next day.
    @Test
    public void GivenMatchingUserInURL_WhenCallingGetLoansByUser_ThenLoansOfThatUserAreReturned() {
        webTestClient.get()
            .uri("/users/john.doe@ucll.be/loans")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody().json("""
                [
                    {
                      "user": {
                        "name": "John Doe",
                        "age": 25,
                        "email": "john.doe@ucll.be",
                        "password": "john1234"
                      },
                      "publications": [
                        {
                          "title": "Arijo",
                          "publicationYear": 2004,
                          "availableCopies": 4,
                          "author": "Kerry Lekoz",
                          "isbn": "978-1-1147-9625-6"
                        }
                      ],
                      "startDate": "2010-02-11",
                      "endDate": "2010-03-12"
                    }
                  ]
            """);

    }

    // Q& Running it via Thunderclient works.
    // 
    @Test 
    public void GivenValidUserBody_WhenCallingAddUser_ThenUserIsAdded(){
        // String newUser = "{\"name\":\"Jenifer Doe\",\"age\":20,\"email\":\"jenifer.doe@ucll.be\",\"password\":\"jenifer1234\"}";
        String newUser = """
            {
                "name": "John Deer",
                "age": 25,
                "email": "john.deer@ucll.be",
                "password": "john1234"
            }
        """;
        webTestClient.post()
            .uri("/users")
            .header("Content-Type","application/json")
            .bodyValue(newUser)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody().json(newUser);

        Assertions.assertEquals("John Deer", userService.getAllUsersByName("John Deer").get(0).getName());
    }

    @Test
    public void GivenValidEmailAndBody_WhenCallingUpdateUser_ThenUserIsUpdatedAndReturned() {
        String newUser = "{\n" + //
                            "    \"name\": \"John Deer\",\n" + //
                            "    \"age\": 25,\n" + //
                            "    \"email\": \"john.doe@ucll.be\",\n" + //
                            "    \"password\": \"john1234\"\n" + //
                            "}";
        webTestClient.put()
            .uri("/users/john.doe@ucll.be")
            .header("Content-Type", "application/json")
            .bodyValue(newUser)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody().json(newUser);

        Assertions.assertThrows(ServiceException.class, () -> userService.getAllUsersByName("John Doe"));
        assertEquals("John Deer", userService.getAllUsersByName("John Deer").get(0).getName());
    }

    @Test
    public void GivenValidUserEmail_WhenCallingDeleteUser_ThenUserIsDeleted(){
        webTestClient.delete()
            .uri("/users/john.doe@ucll.be")
            .exchange()
            .expectStatus().is2xxSuccessful();

        Assertions.assertThrows(
            ServiceException.class,
            () -> userService.getAllUsersByName("John Doe")
        );
    }
}
