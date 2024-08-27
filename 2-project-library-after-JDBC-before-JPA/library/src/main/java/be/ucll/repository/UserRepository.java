package be.ucll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find all users
    List<User> findAll();

    // Find users older than a certain age
    List<User> findByAgeGreaterThan(int age);

    // Find users within an age range
    List<User> findByAgeBetween(int min, int max);

    // Find users by name
    List<User> findByName(String name);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    User findByEmail(String email);

    String deleteByEmail(String email);
    // Custom methods for complex operations should be in a custom repository
    List<User> findTop1ByOrderByAgeDesc();

    List<User> findByProfileIsNotNullAndProfileInterestIsNotNullAndProfileInterestIgnoreCaseContaining(String interest);
    
    List<User> findByProfileInterestNotNullAndProfileInterestIgnoreCaseContainingAndAgeGreaterThanOrderByProfileLocationAsc(String interest, int age);

}
