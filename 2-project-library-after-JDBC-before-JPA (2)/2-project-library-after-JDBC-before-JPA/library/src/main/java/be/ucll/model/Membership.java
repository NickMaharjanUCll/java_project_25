package be.ucll.model;



import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    protected Membership(){}
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;



    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be equal or after today.")
    @Column(name = "start_date") // Update column name to match database
    private LocalDate startDate;

    
    @NotNull(message = "End date is required.")
    @Column(name = "end_date") // Update column name to match database
    private LocalDate endDate;

    

    @NotNull(message = "type is required.")
    private String type;
    
    
   

    public Membership(LocalDate startDate, LocalDate enDate, String type){
        setStartDate(startDate);
        setEndDate(enDate);
        setType(type);
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(@Valid LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@Valid LocalDate endDate) {
        if(endDate == null){
            this.endDate = endDate;  //Q& = This is because of validator and tests?
        }
        else if(!endDate.isAfter(startDate.plusYears(1))){  // Q& Must the endDate be exactly one year after start date? Or is it one year or more after start date?
            throw new DomainException("End date must be 1 year after the start date.");
        }
        
        this.endDate = endDate;
    }
    
    public String getType() {
        return type;
    }

  
    public void setType(@Valid String type) {
        if(type == null){
            this.type = type;
        }
        else if(!(type.equals("SILVER") || type.equals("BRONZE") || type.equals("GOLD"))){
            throw new DomainException("Invalid membership type.");
        }
        this.type = type;
    }

}
