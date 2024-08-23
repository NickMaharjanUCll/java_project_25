// package be.ucll.integration;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.reactive.server.WebTestClient;

// import be.ucll.repository.PublicationRepository;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureWebTestClient

// public class PublicationIntegrationTest {

//     @Autowired
//     private WebTestClient webTestClient;
//     @Autowired
//     private PublicationRepository publicationRepository;

//     @AfterEach
//     public void resetData() {
//         publicationRepository.resetData();
//     }

//     @Test
//     public void GivenTitleAndType_WhenCallingGetPublicationsByTitleAndType_ThenMatchingPublicationsAreReturned() {
//         webTestClient.get()
//             .uri("/publications?title=Arij&type=Book").exchange()
//             .expectStatus().is2xxSuccessful()
//             .expectBody().json("[\n" + //
//                                 "  {\n" + //
//                                 "    \"title\": \"Arijo\",\n" + //
//                                 "    \"publicationYear\": 2004,\n" + //
//                                 "    \"availableCopies\": 5,\n" + //
//                                 "    \"author\": \"Kerry Lekoz\",\n" + //
//                                 "    \"isbn\": \"978-1-1147-9625-6\"\n" + //
//                                 "  }\n" + //
//                                 "]");
//     }

//     @Test
//     public void GivenAvailableCopies_WhenCallingGetPublicationsByAvailableCopies_ThenMatchingPublicationsAreReturned() {
//         webTestClient.get()
//             .uri("/publications/stock/7").exchange()
//             .expectStatus().is2xxSuccessful()
//             .expectBody().json("[\r\n" + //
//                                 "  {\r\n" + //
//                                 "    \"title\": \"Leaking Human\",\r\n" + //
//                                 "    \"publicationYear\": 2008,\r\n" + //
//                                 "    \"availableCopies\": 7,\r\n" + //
//                                 "    \"author\": \"Tom Huysegoms\",\r\n" + //
//                                 "    \"isbn\": \"978-4-4612-3082-4\"\r\n" + //
//                                 "  }\r\n" + //
//                                 "]");
//     }

// }
