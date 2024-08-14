package be.ucll.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PublicationTest {
    private String info = "Number of input violations: ";

    public static ValidatorFactory validatorFactory;
    public static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void givenBlankTitle_whenCallingSetTitle_thenConstraintViolationIsThrown() {
        Magazine magazine = new Magazine("  ", "Gorej", "2049-3630", 1996, 4);
        // Blank means: empty string, string with spaces only, or null.

        Set<ConstraintViolation<Magazine>> violations = validator.validate(magazine);
        assertEquals(info + 1, info + violations.size());

        ConstraintViolation<Magazine> violation = violations.iterator().next();
        assertEquals("Title is required.", violation.getMessage());
    }    

    @Test
    public void givenNegativePublicationYear_whenCallingSetPublicationYear_thenConstraintViolationIsThrown() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "2049-3630", -6, 4);

        Set<ConstraintViolation<Magazine>> violations = validator.validate(magazine);
        assertEquals(info + 1, info + violations.size());

        ConstraintViolation<Magazine> violation = violations.iterator().next();
        assertEquals("Publication year must be a non-negative integer.", violation.getMessage());
    }

    @Test
    public void givenFuturePublicationYear_whenCallingSetPublicationYear_thenDomainExceptionIsThrown() {
        Exception ex = Assertions.assertThrows(
            DomainException.class,
            () -> new Magazine("Kiriki", "Gorej", "2049-3630", 3995, 4)
        );
        assertEquals("Publication year cannot be in the future.", ex.getMessage());
    }

    @Test
    public void givenNegativeAvailableCopies_whenCallingSetPublicationYear_thenConstraintViolationIsThrown() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "2049-3630", 1996, -9);
        Set<ConstraintViolation<Magazine>> violations = validator.validate(magazine);
        ConstraintViolation<Magazine> violation = violations.iterator().next();

        assertEquals(1, violations.size());
        assertEquals("Available copies must be a non-negative integer.", violation.getMessage());
    }

    @Test
    public void givenPublicationWithPositiveAvailableCopies_whenCallingLendPublication_thenAvailableCopiesDecreasesBy1() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 4);
        magazine.lendPublication();

        assertEquals(3, magazine.getAvailableCopies());
    }

    @Test
    public void givenPublicationWithNonPositiveAvailableCopies_whenCallingLendPublication_thenDomainExceptionIsThrown() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 0);
        
        Exception ex = Assertions.assertThrows(
            DomainException.class,
            () -> magazine.lendPublication()
        );
        assertEquals("No available copies left for publication.", ex.getMessage());
    }

    @Test
    public void givenPublication_whenCallingReturnPublication_thenAvailableCopiesIncreasesBy1() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 4);
        magazine.returnPublication();

        assertEquals(5, magazine.getAvailableCopies());   
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }
}
