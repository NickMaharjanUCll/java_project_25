package be.ucll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.User;
import be.ucll.repository.LoanRepository;
import be.ucll.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;
    // @Autowired // Q&A Should we include this line or not? As you prefer.
    public UserService(UserRepository userRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllAdultUsers() {
        return userRepository.findByAgeGreaterThan(18);
    }

    public List<User> getAllUsersInAgeRange(int min, int max) {
        if (min > max) {
            throw new ServiceException("Minimum age cannot be greater than maximum age.");
        } else if (!(min >= 0 && max <= 150)) {
            throw new ServiceException("Invalid age range. Age must be between 0 and 150.");
        }

        return userRepository.findByAgeBetween(min, max);
    }

    public List<User> getAllUsersByName(String name){
        List<User> users = userRepository.findByName(name);
        if(users.isEmpty()|| name == null){
            throw new ServiceException("No users found with the specified name");
        }
        return users;
    }

    public User addUser(User user) {
        // Q& check if the does exist. It uses the userExists method from the userRepository as written in story 12. Is that okay? In story 13 it is written that we should create this method in the userService.
        if (userRepository.existsByEmail(user.getEmail())) {
            // TODO Redundancy.
            throw new ServiceException("User already exists.");
        }
        return userRepository.save(user);
    }

    //Q&A what is domain layer? Domain model.
    // Q& Is it okay that we throw domain exception here?
    // DONE: userRepository does not need the repository. ArrayList is in memory. Call all setter methods and execute validation logic in domain.
    public User updateUser(String email, User userFromRequest){
        if (!userRepository.existsByEmail(email)) {
            throw new ServiceException("User does not exist.");
        }

        return userRepository.save( userFromRequest);
    }

    public String deleteUser(String email){
        if(!userRepository.existsByEmail(email)){
            throw new ServiceException("User does not exist.");
        }else if(loanRepository.findLoansByUserEmail(email, true).size()!= 0){
            throw new ServiceException("User has active loans.");
        }

        loanRepository.deleteLoans(email);

        return userRepository.deleteByEmail(email);
    }
    
}