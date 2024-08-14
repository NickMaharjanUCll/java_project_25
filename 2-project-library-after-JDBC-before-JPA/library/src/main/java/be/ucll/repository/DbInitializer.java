package be.ucll.repository;

import org.springframework.stereotype.Component;

import be.ucll.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {
    private UserRepository userRepository;

    public DbInitializer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialize(){
        userRepository.save(new User("John Doe", 25, "john.doe@ucll.be", "john1234"));
        userRepository.save(new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234"));
        userRepository.save(new User("Jack Doe", 5, "jack.doe@ucll.be", "jack1234"));
        userRepository.save(new User("Sarah Doe", 4, "sarah.doe@ucll.be", "sarah1234"));
        userRepository.save(new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234"));
    }
}
