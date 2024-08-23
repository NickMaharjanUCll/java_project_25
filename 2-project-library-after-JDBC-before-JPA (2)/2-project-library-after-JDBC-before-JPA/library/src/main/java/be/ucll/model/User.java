package be.ucll.model;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "users")
public class User {
    private String name;

    
    private int age;
    private String email;
    private String password;

    protected User(){}

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

  
    public Profile getProfile() {
        return profile;
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonManagedReference
    private List<Membership> memberships = new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER, mappedBy="user")
    @JsonManagedReference
    private List<Loan> loans = new ArrayList<>();
    
    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        loans.add((Loan) loans);
    }

    public User(String name, int age, String email, String password) {
        setName(name);
        setAge(age);
        setEmail(email);
        setPassword(password);
    }

    public User(String name, int age, String email, String password, Profile profile) {
        setName(name);
        setAge(age);
        setEmail(email);
        setPassword(password);
        setProfile(profile);
        // this.memberships = new ArrayList<>();
    }

    public void setProfile(@Valid Profile profile) {
        if(age <= 17){
            throw new DomainException("age should be greater than 17");
        }
        this.profile = profile;
    }


    public void setName(String name) {
        if (
            name == null ||
            name.equals("") ||
            name.equals( " ".repeat(name.length()))
        ) {
            throw new DomainException("Name is required.");
        }

        this.name = name;
    }

    public void setAge(int age) {
        if (!(age > 0 && age < 101)) {
            throw new DomainException("Age must be a positive integer between 0 and 101.");
        }

        this.age = age;
    }

    public void setEmail(String email) {
        if (!(email.contains("@") && email.contains("."))) {
            throw new DomainException("E-mail must be a valid email format.");
        }
        if((getEmail() != null) && (!getEmail().equals(email))){
            throw new DomainException("Email cannot be changed.");
        }

        this.email = email;
    }

    public void setPassword(String password) {
        if (password.length() < 8) {
            throw new DomainException("Password must be at least 8 characters long.");
        }

        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }   

    public void addMembership(Membership membership){
        if(membership.equals(null)){
            memberships.add(membership);
        }

        for(Membership membershipdate : memberships){
            LocalDate a = membership.getStartDate();
            LocalDate b = membership.getEndDate();
            LocalDate c = membershipdate.getStartDate();
            LocalDate d = membershipdate.getEndDate();
            if (b.isBefore(c) || a.isAfter(d)){
                throw new DomainException("User has already a membership on that date.");
            }
        }
        
    }
    public List<Membership> getMemberships(){
        return memberships;
    }

    public Membership getActivMembership(LocalDate date){
        for (Membership membership : memberships){

            if((membership.getStartDate().isBefore(date)) ||  membership.getStartDate().isEqual(date) && membership.getEndDate().isAfter(date)){
                return membership;
            }
        }
        return null;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + age;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (age != other.age)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }




}
