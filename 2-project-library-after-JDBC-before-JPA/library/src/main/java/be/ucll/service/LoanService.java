package be.ucll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.Loan;
import be.ucll.repository.LoanRepository;
import be.ucll.repository.UserRepository;

@Service
public class LoanService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;

    public LoanService(UserRepository userRepository ,LoanRepository loanRepository){
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    public List<Loan> getLoans() {
        return loanRepository.getLoans();
    }

    public List<Loan> getLoansByUser(String email, boolean onlyActive){
        if(!userRepository.existsByEmail(email)){
        throw new ServiceException("User does not exist.");
        }
        List<Loan> userLoans = loanRepository.findLoansByUserEmail(email, onlyActive);
        if(userLoans.isEmpty()){
            return userLoans;
        }
        return userLoans;
    }

    public String deleteLoans(String email) {
        if(!userRepository.existsByEmail(email)){
            throw new ServiceException("User does not exist.");
        } else if (loanRepository.findLoansByUserEmail(email, false).size() == 0) {
            throw new ServiceException("User has no loans.");
        } else if (loanRepository.findLoansByUserEmail(email, true).size() != 0) {
            throw new ServiceException("User has active loans.");
        }

        return loanRepository.deleteLoans(email);
    }
}