package be.ucll.repository;

import java.util.List;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.model.Profile;
import be.ucll.model.User;
import be.ucll.model.Book;
import be.ucll.model.Loan;
import be.ucll.model.Magazine;
import be.ucll.model.Publication;
import be.ucll.model.Membership;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {
    private UserRepository userRepository;
    private LoanRepository loanRepository;
    private PublicationRepository publicationRepository;
    private ProfileRepository profileRepository;
    private MembershipRepository membershipRepository;
    // private List<Loan> loans;

    @Autowired
    public DbInitializer(LoanRepository loanRepository,MembershipRepository membershipRepository,UserRepository userRepository, ProfileRepository profileRepository,  PublicationRepository publicationRepository){
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.publicationRepository = publicationRepository;
        this.loanRepository = loanRepository;
        this.membershipRepository = membershipRepository;
    }

    // List<Loan> loans;


    @PostConstruct
    public void initialize(){
     

        Profile hero = new Profile("sanda", "banh banh", "ola gola");
        Profile john = new Profile("hero", "america", "basketball and footBall");
        Profile bone = new Profile("sero", "barysa", "volleyball and pani");
    
    
        profileRepository.save(hero);
        profileRepository.save(john);
        profileRepository.save(bone);
    
        // Now you can create and save Users, linking to the Profiles
        userRepository.save(new User("John Doe", 25, "john.doe@ucll.be", "john1234",hero));
        userRepository.save(new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234",john));
        userRepository.save(new User("Jack Doe", 5, "jack.doe@ucll.be", "jack1234"));  // Link to hero Profile
        userRepository.save(new User("Sarah Doe", 4, "sarah.doe@ucll.be", "sarah1234"));
        userRepository.save(new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234",bone));


        publicationRepository.save(new Book("Arijo" , "Kerry Lekoz" , "978-1-1147-9625-6",2004, 5));
        publicationRepository.save(new Book("Sun is Moon", "Gel Tison", "978-6-5275-9678-3", 2000, 2));
        publicationRepository.save(new Book("Leaking Human", "Tom Huysegoms", "978-4-4612-3082-4", 2008, 7));
        publicationRepository.save(new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 4));
        publicationRepository.save(new Magazine("Berot", "Kéril Dough", "5692-2390", 2005, 3));
        publicationRepository.save(new Magazine("Leaking Human", "Tom Huysegoms", "2222-3435", 2008, 3)); 

        LocalDate today =  LocalDate.now();

      
        Membership membershipJohn = new Membership(today, today.plusYears(2), "SILVER");
        Membership membershipJohn2 = new Membership(today.plusYears(3), today.plusYears(5), "SILVER");
        Membership membershipJane = new Membership(today, today.plusYears(2), "GOLD");
        Membership membershipJack = new Membership(today.plusYears(3), today.plusYears(10), "BRONZE");
        Membership membershipSarah = new Membership(today, today.plusYears(2), "SILVER");
        Membership membershipBirgit = new Membership(today.plusDays(4), today.plusYears(2), "BRONZE");
        membershipJohn.setUser(userRepository.findByEmail("john.doe@ucll.be"));
        membershipJohn2.setUser(userRepository.findByEmail("john.doe@ucll.be"));
        membershipJane.setUser(userRepository.findByEmail("jane.doe@ucll.be"));
        membershipJack.setUser(userRepository.findByEmail("jack.doe@ucll.be"));
        membershipSarah.setUser(userRepository.findByEmail("sarah.doe@ucll.be"));
        membershipBirgit.setUser(userRepository.findByEmail("birgit.doe@ucll.be"));
        membershipRepository.save(membershipJohn);
        membershipRepository.save(membershipJohn2);
        membershipRepository.save(membershipJane);
        membershipRepository.save(membershipJack);
        membershipRepository.save(membershipSarah);
        membershipRepository.save(membershipBirgit);


        
        List<User> users =  userRepository.findAll();
        List<Publication> publications = publicationRepository.findAll();
        LocalDate fixedToday = LocalDate.of(2010, 3, 13);


        Loan loanJohn = new Loan(users.get(0),List.of(publications.get(0)),fixedToday.minusDays(30));
        Loan loanJane = new Loan(users.get(1),List.of(publications.get(1)),fixedToday.minusDays(46));
        Loan loanJack = new Loan(users.get(2),List.of(publications.get(2)),fixedToday.minusDays(50));
        Loan loanJack2 = new Loan(users.get(2),List.of(publications.get(2)),fixedToday.minusDays(40));
        Loan loanSarah = new Loan(users.get(3),List.of(publications.get(3)),fixedToday.minusDays(2));
        Loan loanSarah2 = new Loan(users.get(3),List.of(publications.get(3)),fixedToday.minusDays(2));
        Loan loanBirgit = new Loan(users.get(4),List.of(publications.get(3), publications.get(2), publications.get(0)), today.minusDays(2)); // Intentionally used today instead of fixedToday so that it is always an active loan.
        Loan loanBirgit2 = new Loan(users.get(4),List.of(publications.get(2), publications.get(1), publications.get(0)), fixedToday.minusDays(20)); // Intentionally used today instead of fixedToday so that it is always an active loan.
        
        loanJohn.returnLoan(fixedToday.minusDays(20));
        loanJane.returnLoan(fixedToday.minusDays(30));
        loanJack.returnLoan(fixedToday.minusDays(40));
        loanJack2.returnLoan(fixedToday.minusDays(30));
        loanSarah.returnLoan(fixedToday);
        loanSarah2.returnLoan(fixedToday);
        // loanBirgit is never returned, because it's active
        loanBirgit2.returnLoan(fixedToday);

        loanRepository.save(loanJohn);
        loanRepository.save(loanJane);
        loanRepository.save(loanJack);
        loanRepository.save(loanJack2);
        loanRepository.save(loanSarah);
        loanRepository.save(loanSarah2);
        loanRepository.save(loanBirgit);
        loanRepository.save(loanBirgit2);
    }
}

