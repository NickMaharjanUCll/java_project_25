package be.ucll.model;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Loan {

    protected Loan(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull(message = "A loan must be associated with an user.")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty(message = "A loan must be associated with one or more publications.") // Does not work, check test for further explanation.
    @ManyToMany
    @JoinTable(
        name = "loan_publications",
        joinColumns = @JoinColumn(name="loan_id"),
        inverseJoinColumns = @JoinColumn(name = "publication_id")
    )
    private List<Publication> publications; // Q&A Should we use ArrayList or List? List, use the most general until its necessary otherwise.

    @NotNull(message = "Start date is required.")
    @PastOrPresent(message = "Start date cannot be in the future.")
    @Column(name = "start_date")
    private LocalDate startDate;

    @PastOrPresent(message = "End date cannot be in the future.")
    @Column(name = "end_date")
    private LocalDate endDate;


    public Loan(User user, List<Publication> publications , LocalDate startDate, LocalDate endDate){
        setUser(user);
        setPublications(publications);
        setStartDate(startDate);
        if(startDate != null){
            this.endDate = startDate.plusDays(30);
        }
    }

    public void setUser(@Valid User user){
        this.user = user;
    }

    public void setStartDate(@Valid LocalDate startDate){
        if (endDate != null && startDate != null && startDate.isAfter(endDate)) {
            throw new DomainException("Start date cannot be after end date.");
        }
        this.startDate = startDate;
    }

    public void setEndDate(@Valid LocalDate endDate){
        if(endDate != null && startDate != null && endDate.isBefore(startDate)){
            throw new DomainException("End date cannot be before Start date.");
        }
        this.endDate = endDate;
    }

    public void setPublications(@Valid List<Publication> publications){ // Q&A Is it okay that we use List as a type here? Yes.
        if(publications == null || publications.isEmpty()){
            return;
        }

        // CAREFUL: If any of the publications has no copies left when registering the loan, an error should be given and the available copies of all other publications remain unchanged.
        

        for(Publication publication : publications){
            if(publication.getAvailableCopies() == 0){
                throw new DomainException("Unable to lend publication. No copies available for "+ publication.getTitle() + ".");
            }
        }

        for(Publication publication : publications){
                publication.lendPublication();
        }
        
        this.publications = publications; 
    }

    public User getUser(){
        return user;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public List<Publication> getPublications(){ // Q& Is it okay that we use List as a type here? Yes.
        return publications;
    }

    public void returnPublications(){
        for(Publication publication : publications){
            publication.returnPublication();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((publications == null) ? 0 : publications.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
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
        Loan other = (Loan) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (publications == null) {
            if (other.publications != null)
                return false;
        } else if (!publications.equals(other.publications))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        return true;
    }
}