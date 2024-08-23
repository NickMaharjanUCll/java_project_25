package be.ucll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {
    public static void main(String[] args) {
        // Tests for story 6.
        SpringApplication.run(LibraryApplication.class, args);

        
        // // Tests after story 1-5.
        // Publication publication1 = new Book("bookTitle" , "bookAuthor" , "978-0-545-01022-1",2004, 5);
        // Publication publication2 = new Book("bookTitle2" , "bookAuthor2" , "978-0-545-01022-1",2004, 6);
        // Publication publication3 = new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 4);
        // List<Publication> publications = new ArrayList<>();

        // publications.add(publication1);
        // publications.add(publication2);
        // publications.add(publication3);

        // System.out.println("\nPublications in the library:\n");
        // for (Publication publication : publications ) {
        //     System.out.println(publication);
        // }
        // System.out.println();
    }
}


// Q&A What should I clean? "After cleaning up the test data in LibraryApplication.java, you can consider story 6 as finished!" IDK
// Q&A I ran the LibraryApplication.java from VSCode, but the 'target' folder with .class files did not appear automatically. I had to manually do it in the Lifecycle of Maven. Anyone experiences similar? Open the 'Library' folder from VSCode, not any other folder. 
