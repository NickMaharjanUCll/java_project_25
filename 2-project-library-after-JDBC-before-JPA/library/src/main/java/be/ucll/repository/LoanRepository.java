package be.ucll.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import be.ucll.model.Book;
import be.ucll.model.Loan;
import be.ucll.model.Magazine;
import be.ucll.model.Publication;
import be.ucll.model.User;

@Repository
public class LoanRepository {
    private List<Loan> loans;
    // @Autowired
    // private UserRepository userRepository;
    // @Autowired
    // private PublicationRepository publicationRepository;
    
    public LoanRepository(){
        resetData();
    }

    public void resetData() {
        // List<User> users = userRepository.allUsers();
        // List<Publication> publications = publicationRepository.allPublications();

        // Q& THESE LISTS HAVE TO BE HARDCODED, OTHERWISE THE REFERENCES ARE TAKEN AND WEIRD STUFF HAPPENS.

        // This list has to match the one in the userRepository.
        List<User> users = new ArrayList<>(
            List.of(
                new User("John Doe", 25, "john.doe@ucll.be", "john1234"),
                new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234"),
                new User("Jack Doe", 5, "jack.doe@ucll.be", "jack1234"),
                new User("Sarah Doe", 4, "sarah.doe@ucll.be", "sarah1234"),
                new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234")
            )
        );

        List<Publication> publications = new ArrayList<>(
            List.of(
                new Book("Arijo" , "Kerry Lekoz" , "978-1-1147-9625-6",2004, 5),
                new Book("Sun is Moon", "Gel Tison", "978-6-5275-9678-3", 2000, 2),
                new Book("Leaking Human", "Tom Huysegoms", "978-4-4612-3082-4", 2008, 7),
                new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 4),
                new Magazine("Berot", "Kéril Dough", "5692-2390", 2005, 3),
                new Magazine("Leaking Human", "Tom Huysegoms", "2222-3435", 2008, 3)
            )
        );

        this.loans = new ArrayList<>();
        LocalDate today = LocalDate.of(2010, 3, 13);
        loans.add(new Loan(users.get(0),List.of(publications.get(0)),today.minusDays(30),today.minusDays(1)));
        loans.add(new Loan(users.get(2),List.of(publications.get(2)),today.minusDays(50),null));
        loans.add(new Loan(users.get(1),List.of(publications.get(1)),today.minusDays(46),today.minusDays(9)));
        loans.add(new Loan(users.get(2),List.of(publications.get(2)),today.minusDays(40),today.minusDays(25)));
        loans.add(new Loan(users.get(3),List.of(publications.get(3)),today.minusDays(2),null));
        loans.add(new Loan(users.get(3),List.of(publications.get(3)),today.minusDays(2),null));
        // Q& Why is this code ran when starting up Spring? It decreases the publications' availableCopies and this makes conflicts elsewhere. That's why I hardcoded it.
        // System.out.println("\n\n!!!!!!!!!!!!!!!! MAKING LOANS AND DECREASING availableCopies OF PUBLICATIONS !!!!!!!!!!!!!!!\n\n");

    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<Loan> findLoansByUserEmail(String email, boolean onlyActive){

        List<Loan> userLoans = loans.stream().filter(loan -> loan.getUser().getEmail().equals(email)).collect(Collectors.toList());

        if(onlyActive){
            userLoans = userLoans.stream().filter(loan -> loan.getEndDate()==null ||loan.getEndDate().isAfter(LocalDate.now())).collect(Collectors.toList());
        }
        return userLoans;
        //loans.stream() converts the List<Loan> called loans into a stream of elements.
        //.collect(Collectors.toList()) After filtering, the collect method gathers the filtered elements back into a list. This results in a list of Loan objects (List<Loan>) where each Loan belongs to the specified user (email).
    }

    public String deleteLoans(String email) {
        for (Loan loan : findLoansByUserEmail(email, false)) {
            loans.remove(loan);            
        }
        return "Loans of user successfully deleted.";
    }
}