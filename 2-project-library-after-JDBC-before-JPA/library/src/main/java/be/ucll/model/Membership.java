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

    protected Membership() {}

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be equal to or after today.")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "End date is required.")
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull(message = "Membership type is required.")
    private String type;

    @Column(name ="free_loan")
    private int freeLoans;

    public Membership(LocalDate startDate, LocalDate endDate, String type) {
        setType(type);
        setStartDate(startDate);
        setEndDate(endDate);
        setFreeLoans(getDefaultFreeLoansForType(type)); // Set default free loans based on type
    }

    private int getDefaultFreeLoansForType(String type) {
        switch (type) {
            case "BRONZE":
                return 5;
            case "SILVER":
                return 10;
            case "GOLD":
                return 15;
            default:
                throw new DomainException("Invalid membership type.");
        }
    }

    public int getFreeLoans() {
        return freeLoans;
    }

    public void setFreeLoans(int freeLoans) {
        if (type != null) {
            if (type.equals("BRONZE") && !(freeLoans >= 0 && freeLoans <= 5)) {
                throw new DomainException("Invalid number of free loans for BRONZE membership.");
            } else if (type.equals("SILVER") && !(freeLoans >= 6 && freeLoans <= 10)) {
                throw new DomainException("Invalid number of free loans for SILVER membership.");
            } else if (type.equals("GOLD") && !(freeLoans >= 11 && freeLoans <= 15)) {
                throw new DomainException("Invalid number of free loans for GOLD membership.");
            }
        }
        this.freeLoans = freeLoans;
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
        if (endDate == null) {
            this.endDate = endDate;
        } else if (!endDate.isAfter(startDate.plusYears(1))) {
            throw new DomainException("End date must be at least 1 year after the start date.");
        }
        this.endDate = endDate;
    }

    public void redeemFreeLoan() {
        if (freeLoans == 0) {
            throw new DomainException("No more free loans available within membership.");
        }
        freeLoans--;
    }

    public String getType() {
        return type;
    }

    public void setType(@Valid String type) {
        if (type == null) {
            throw new DomainException("Membership type cannot be null.");
        } else if (!(type.equals("SILVER") || type.equals("BRONZE") || type.equals("GOLD"))) {
            throw new DomainException("Invalid membership type.");
        }
        this.type = type;
    }
}
