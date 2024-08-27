package be.ucll.model;

import java.time.Year;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name = "publications")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Publication {

    protected Publication(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;


    @NotBlank(message = "Title is required.")   
    private String title;

    @Positive(message = "Publication year must be a non-negative integer.")
    private int publicationYear;

    @Positive(message = "Available copies must be a non-negative integer.")
    private int availableCopies;


    @Column(insertable = false, updatable= false)
    private String type;

    
    public Publication(String title, int publicationYear, int availableCopies) {
        setTitle(title);
        setPublicationYear(publicationYear);
        setAvailableCopies(availableCopies);
    }

    public void setTitle(@Valid String title) {
        this.title = title;
    }

    public void setPublicationYear(@Valid int publicationYear) {
        if (publicationYear > Year.now().getValue()) {
            throw new DomainException("Publication year cannot be in the future.");
        }

        this.publicationYear = publicationYear;
    }

    public void setAvailableCopies(@Valid int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public boolean  isInvalidString(String string) {
        return (string == null || string.trim().isEmpty());
    }

    public void lendPublication(){
        if(availableCopies > 0){
            availableCopies --;
        }
        else{
            throw new DomainException("No available copies left for publication.");
        }
    }
    public void returnPublication(){
        availableCopies++;
    }
}
