package be.ucll.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.model.DomainException;
import be.ucll.model.Membership;
import be.ucll.model.User;
import be.ucll.service.UserService;


@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adults")
    public List<User> getAllAdultUsers() {
        return userService.getAllAdultUsers();
    }
    // Q&A There are two paths given in story 8. Use the one from the table.
    @GetMapping("/age/{min}/{max}")
    public List<User> getAllUsersInAgeRange(
        @PathVariable(value = "min") int min,
        @PathVariable(value = "max") int max
    )
        {
        return userService.getAllUsersInAgeRange(min, max);
    }
    
    @GetMapping
    public List<User> getAllUsersByName(@RequestParam(required = false) String name){
        if(name == null){
            return userService.getAllUsers();
        }
        else{
            return userService.getAllUsersByName(name);
        }
    }

    @GetMapping("/oldest")
    public List<User> getOldestUser() {
        return userService.getOldestUser();
    }
    
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User userFromRequest){
        return userService.updateUser(email, userFromRequest);
    }

    @GetMapping("/interest/{interest}")
    public List<User> getUserWithSimilarInterest(@PathVariable(value = "interest") String interest) {
        return userService.getUserWithSimilarInterest(interest);
    }
    
    @GetMapping("/interest/{interest}/{age}")
    public List<User> getUserWithInterestWIthAge(@PathVariable(value = "interest") String interest, @PathVariable(value = "age") int age) {
        return userService.getUserWithInterestByAge(interest, age);
    }
    
    @PostMapping("/{email}/membership")
    public User getMembershipByEmailAndDate(@PathVariable(value = "email") String email, @RequestBody Membership membership){
        return userService.getMembershipByEmailAndDate(email,membership);
    }




    // @DeleteMapping("/{email}")
    // public String deleteUser(@PathVariable String email){
    //     return userService.deleteUser(email);
    // }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DomainException.class})
    public Map<String, String> handleDomainException(DomainException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("DomainException", ex.getMessage());
        return errors;
    }

}

// Request Type
// GET URL
// http://localhost:8080/users Path Variables
// None Request Body
// None Output success
// -
// A list of User objects (JSON) - fields for each user: name, age,email, password.
// -
// The list may be empty if no users are found.Output error
// None

// Use constructor injection to inject an instance of UserService in this controller.