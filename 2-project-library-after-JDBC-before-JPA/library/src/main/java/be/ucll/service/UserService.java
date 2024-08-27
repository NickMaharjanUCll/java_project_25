package be.ucll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.Membership;
import be.ucll.model.User;
import be.ucll.repository.MembershipRepository;
import be.ucll.repository.ProfileRepository;
// import be.ucll.repository.LoanRepository;
import be.ucll.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // private LoanRepository loanRepository;
    private MembershipRepository memberShipRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired // Q&A Should we include this line or not? As you prefer.
    public UserService(MembershipRepository memberShipRepository,UserRepository userRepository,ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        // this.loanRepository = loanRepository;
        this.profileRepository = profileRepository;
        this.memberShipRepository = memberShipRepository;
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
        if(user.getProfile() != null){
            profileRepository.save(user.getProfile());
        }
        return userRepository.save(user);
    }

   


    public List<User> getOldestUser(){
        List<User> oldestPerson = userRepository.findTop1ByOrderByAgeDesc();
        if(oldestPerson.isEmpty()){
            throw new ServiceException("No old user found.");
        }
        return oldestPerson;
    }

    public List<User> getUserWithSimilarInterest(String interest){
        if (interest.trim().isEmpty()){
            throw new ServiceException("Interest cannot be empty.");
        }
        List<User> PeopleWithSimilarInterest = userRepository.findByProfileIsNotNullAndProfileInterestIsNotNullAndProfileInterestIgnoreCaseContaining(interest);
        if(PeopleWithSimilarInterest.isEmpty()){
            throw new ServiceException("No user found with similar intrest in "+ PeopleWithSimilarInterest);
        }
        return PeopleWithSimilarInterest;
    }

    public List<User> getUserWithInterestByAge(String interest, int age){
        List<User> similarInterestandAge = userRepository.findByProfileInterestNotNullAndProfileInterestIgnoreCaseContainingAndAgeGreaterThanOrderByProfileLocationAsc(interest,age);
        if(interest.trim().isEmpty()){
            throw new ServiceException("Interest cannot be empty.");
        }

        return similarInterestandAge;
    }

    public User updateUser(String email, User userFromRequest){
        if (!userRepository.existsByEmail(email)) {
            throw new ServiceException("User does not exist.");
        }

        return userRepository.save( userFromRequest);
    }


    public User  getMembershipByEmailAndDate(String email, Membership membership){
        if (!userRepository.existsByEmail(email)){
            throw new ServiceException("User does not exist.");
        }
        User user = userRepository.findByEmail(email);
        
        membership.setUser(user);
        // user.addMembership(membership);
        // user.setProfile(user.getProfile());    
        // User user2 = new User(user.getName(), user.getAge(), user.getEmail(), user.getPassword());
        
        // for(Membership members : user.getMemberships()){
        //     user2.addMembership(members);

        // }

        user.addMembership(membership);
        
        userRepository.save(user);
         memberShipRepository.save(membership);

         return user;
    }
    // public String deleteUser(String email){
    //     if(!userRepository.existsByEmail(email)){
    //         throw new ServiceException("User does not exist.");
    //     }else if(loanRepository.findByUserEmail(email).size()!= 0){
    //         throw new ServiceException("User has active loans.");
    //     }

    //     loanRepository.deleteByUserEmail(email);

    //     return userRepository.deleteByEmail(email);
    // }
    
}