package be.ucll.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import be.ucll.model.Book;
import be.ucll.model.Magazine;
import be.ucll.model.Publication;
import be.ucll.repository.PublicationRepository;
import be.ucll.service.PublicationService;

public class PublicationServiceTest {
    PublicationRepository publicationRepository = new PublicationRepository();
    PublicationService publicationService = new PublicationService(publicationRepository);
    public List<Publication> books;
    public List<Publication> magazines;

    public PublicationServiceTest() {
        this.books = new ArrayList<>(
            List.of(
                new Book("Arijo" , "Kerry Lekoz" , "978-1-1147-9625-6",2004, 5),
                new Book("Sun is Moon", "Gel Tison", "978-6-5275-9678-3", 2000, 2),
                new Book("Leaking Human", "Tom Huysegoms", "978-4-4612-3082-4", 2008, 7)
            )
        );
        this.magazines = new ArrayList<>(
            List.of(
                new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 4),
                new Magazine("Berot", "Kéril Dough", "5692-2390", 2005, 3),
                new Magazine("Leaking Human", "Tom Huysegoms", "2222-3435", 2008, 3)
            )
        );
    }

    // Q&A Why does it only work with Strings? Override .equals in Book class.
    @Test
    public void givenRequestParametersThatGiveAMatch_whenCallingFindPublicationsByTitleAndType_thenAllMatchingPublicationsAreReturned() {
        assertEquals(     
            new ArrayList<Publication>(List.of(books.get(0))),
            publicationService.findPublicationsByTitleAndType("Arijo", "Book")
        );

    // @Test
    // public void givenRequestParametersThatGiveAMatch_whenCallingFindPublicationsByTitleAndType_thenAllMatchingPublicationsAreReturned() {
    //     assertEquals(     
    //         new ArrayList<Publication>(List.of(books.get(0))),
    //         publicationService.findPublicationsByTitleAndType("Arijo", "Book")
    //     );

        // assertTrue(
    }
    // Q&A Is this way of testing correct? Hardcode it.
    // Q&A Should give same names to related functions. In the example above? It does not matter.

    // Q&A If publications will be added, this test will fail. That's probably not useful? What to change? Hard code it.
    @Test
    public void givenTitleThatPartiallyMatches_whenCallingFindPublicationsByTitleAndType_thenPublicationsWithPartiallyMatchingTitlesAreReturned() {
        assertEquals(     
            // new ArrayList<Publication>(List.of(books.get(0))).toString(),
            // publicationService.findPublicationsByTitleAndType("Arijo", "Book").toString()
            new ArrayList<Publication>(List.of(books.get(0))),
            publicationService.findPublicationsByTitleAndType("Arij", "Book")
        );
    }

    @Test
    public void givenRequestParametersThatDoNotGiveAMatch_whenCallingFindPublicationsByTitleAndType_thenEmptyListIsReturned() {
        assertEquals(
            new ArrayList<>(),
            publicationService.findPublicationsByTitleAndType("x", null)
        );
    }

    @Test
    public void givenAllRequestParametersAreNull_whenCallingFindPublicationsByTitleAndType_thenAllPublicationsAreReturned() {
        List<Publication> expectedAll = new ArrayList<>();
        expectedAll.addAll(books);
        expectedAll.addAll(magazines);
        
        assertEquals(
            expectedAll,
            publicationService.findPublicationsByTitleAndType(null, null)
        );
    }

    @Test 
    public void givenOnlyTitleIsNull_whenCallingFindPublicationsByTitleAndType_thenPublicationsFilteredOnTypeOnlyAreReturned() {
        assertEquals(
            // new ArrayList<Publication>(List.of(books.get(0), books.get(2))),
            magazines,
            publicationService.findPublicationsByTitleAndType(null, "Magazine")
        );
    }

    @Test
    public void givenOnlyTypeIsNull_whenCallingFindPublicationsByTitleAndType_thenPublicationsFilteredOnTitleOnlyAreReturned() {
        assertEquals(
            new ArrayList<Publication>(List.of(books.get(0), books.get(2), magazines.get(2))),
            publicationService.findPublicationsByTitleAndType("a", null)
        );
    }

  
}

// Q&A In the tests, do we have to translate each acceptance criteria into a test? Yes it's a good reflex.
