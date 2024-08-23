package be.ucll.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.Loan;
import be.ucll.model.Publication;
import be.ucll.model.User;
import be.ucll.repository.LoanRepository;
import be.ucll.repository.PublicationRepository;
import be.ucll.repository.UserRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Service
public class LoanService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;  

    @Autowired
    private PublicationRepository publicationRepository;

    public LoanService(UserRepository userRepository ,LoanRepository loanRepository, PublicationRepository publicationRepository){
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.publicationRepository = publicationRepository;
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getLoansByUser(String email, boolean onlyActive){
        if(!userRepository.existsByEmail(email)){
        throw new ServiceException("User does not exist.");
        }
        List<Loan> userLoans = loanRepository.findByUserEmail(email);
        if (onlyActive) {
            userLoans = loanRepository.findByUserEmailAndEndDateIsNull(email);
        }

        if(userLoans.isEmpty()){
            return userLoans;
        }
        return userLoans;
    }

    public String deleteLoans(String email) {
        if(!userRepository.existsByEmail(email)){
            throw new ServiceException("User does not exist.");
        } else if (loanRepository.findByUserEmail(email).size() == 0) {
            throw new ServiceException("User has no loans.");
        } else if (loanRepository.findByUserEmailAndEndDateIsNull(email).size() != 0) {
            throw new ServiceException("User has active loans.");
        }

         loanRepository.deleteByUserEmail(email);
         return "Loans of User successfully deleted";
    }


    @Transactional
    public Loan updatedLoanByEmailandStartDate(String email, LocalDate startDate, List<Long> publicationIds){
        if(!userRepository.existsByEmail(email)){
            throw new ServiceException("User not found.");
        }
         if(loanRepository.findByUserEmail(email).isEmpty()){ // we can also do by the size loanRepository.findByUserEmail(email).size() == 0
            throw new ServiceException("User has no loan.");
        }
        
        if(loanRepository.findByUserEmailAndEndDateIsNull(email).isEmpty()){ // same as above can use the size
            throw new ServiceException("User has an active loan.");
        }

        // for (Long publicationId : publications){
        //     if(!loanRepository.existsByPublicationId(publicationId)){
        //         throw new ServiceException("Publication with Id" + publicationId + "not found");
        //     }
        }

        // List<Publication> publications2 = publicationIDs.stream().map(x -> {
        //     return publicationRepository.findById(x).orElse(() -> new ServiceException(String.format("publication with Id %d not found.", x)));
        // }).toList();
        
        // User user = userRepository.findByEmail(email);
        
        // Loan loan = new Loan(user,publications2,startDate ,startDate.plusDays(30));
        // loanRepository.save(loan);


        // return loan;

    //     List<Publication> publications = new ArrayList<>();
    //     for (Long publicationId : publicationIds) {
    //         Optional<Publication> publication = publicationRepository.findById(publicationId);
    //         if (!publication.isPresent()) {
    //             throw new ServiceException("Publication with id " + publicationId + " not found.");
    //         }

    //         publications.add(publication.get());
    //     }

    //     User user = userRepository.findByEmail(email);
    //     Loan loan = new Loan(user, publications, startDate);
    //     loanRepository.save(loan);

    //     return loan;
    // }
}