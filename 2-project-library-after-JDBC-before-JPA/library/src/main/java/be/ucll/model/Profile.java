package be.ucll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Profile(){}

    @NotBlank(message = "bio is required")
    private String bio;
    public String getBio() {
        return bio;
    }


    public void setBio(String bio) {
        if(bio == null || bio.trim().isEmpty()){
            throw new DomainException("Bio should not be empty");
        }
        this.bio = bio;
    }

    @NotBlank(message = "Location is required")
    private String location;

    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        if(location == null || location.trim().isEmpty()){
            throw new DomainException("Location should not be empty");
        }
        this.location = location;
    }

    @NotBlank(message = "interest is required")
    private String interest;


    public String getInterest() {
        return interest;
    }


    public void setInterest(String interest) {
        if(interest == null || interest.trim().isEmpty()){
            throw new DomainException("Interest should not be empty");
        }
        this.interest = interest;
    }


    public Profile(String bio, String location, String interest){
        setBio(bio);
        setInterest(interest);
        setLocation(location);
    }

    
    
}
