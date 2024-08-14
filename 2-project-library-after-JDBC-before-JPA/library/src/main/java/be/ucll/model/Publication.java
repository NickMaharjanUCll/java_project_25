package be.ucll.model;

import java.time.Year;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public abstract class Publication {
    @NotBlank(message = "Title is required.")   
    private String title;

    @Positive(message = "Publication year must be a non-negative integer.")
    private int publicationYear;

    @Positive(message = "Available copies must be a non-negative integer.")
    private int availableCopies;


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
