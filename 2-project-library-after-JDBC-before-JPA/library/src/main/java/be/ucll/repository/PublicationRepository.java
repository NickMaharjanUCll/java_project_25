package be.ucll.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import be.ucll.model.Book;
import be.ucll.model.Magazine;
import be.ucll.model.Publication;

@Repository
public class PublicationRepository {
    // Q&A Cannot convert from Book to Publication. A: Forgotten.
    // public List<Book> books;
    // public List<Magazine> magazines;
    public List<Publication> books;
    public List<Publication> magazines;

    public PublicationRepository() {
        resetData();
        // System.out.println("\n\n CONSTRUCTOR \n" + books);
    }

    public void resetData() {
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
        // System.out.println("\n\n RESET DATA \n" + books);

    }

    public List<Publication> allPublications() {
        List<Publication> allPublications = new ArrayList<>();
        // System.out.println("\n\n");
        // System.out.println(books);
        allPublications.addAll(books);
        allPublications.addAll(magazines);
        // System.out.println("\n\n" + allPublications);
        return allPublications; 
    }

    public List<Publication> publicationsByTitle(String title, List<Publication> publications) {
        
        List<Publication> publicationsByTitle = new ArrayList<>();
        for (Publication publication : publications) {
            if (publication.getTitle().toLowerCase().contains(title.toLowerCase())) {
                publicationsByTitle.add(publication);
            }
        }
        return publicationsByTitle;
    }

    // Q& Last time I remember you say "Repository is dumb," so I have doubts about the code below. Is it okay have these if's here?
    public List<Publication> publicationsByType(String type) {
        if (type.equals("Book")) {
            return books;
        } else if (type.equals("Magazine")) {
            return magazines;
        }
        return new ArrayList<>();
    }
    
    public List<Publication> publicationsByTitleAndType(String title, String type) {
        return publicationsByTitle(title, publicationsByType(type)); // First filter by type and then by title.
    }

    public List<Publication> publicationByAvailableCopies(int availableCopies){
        List<Publication> publicationByAvailableCopies = new ArrayList<>();
        for(Publication publication : allPublications()){
            if (publication.getAvailableCopies() >= availableCopies){
                publicationByAvailableCopies.add(publication);
            }
        }
        return publicationByAvailableCopies;
    }

}

// Q&A First story always contains logic that needs to be understood by both team members. Should we both do it independently, or how should we divide the work, so that we both benifit from the first story. Pair programming.