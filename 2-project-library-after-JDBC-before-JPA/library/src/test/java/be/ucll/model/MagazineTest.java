package be.ucll.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class MagazineTest {
    private String info = "Number of input violations: ";
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    // Happy paths.
    @Test
    public void givenValidValues_whenCallingMagazineConstructor_thenMagazineIsCreatedWithThoseValues() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "2049-3630", 1991, 4);

        assertEquals("Kiriki", magazine.getTitle());
        assertEquals("Gorej", magazine.getEditor());
        assertEquals("2049-3630", magazine.getISSN());
        assertEquals(1991, magazine.getPublicationYear());
    }

    // Unhappy paths.
    // @Test
    // public void givenInvalidTitle_whenCallingSetTitle_thenDomainExceptionIsThrown() {
    //     Exception ex = Assertions.assertThrows(
    //         DomainException.class,
    //         () -> new Magazine("", "Gorej", "2049-3630", 1991, 4)
    //     );
    //     assertEquals("Title is required.", ex.getMessage());
    // }

    @Test
    public void givenBlankEditor_whenCallingSetEditor_thenConstraintViolationIsThrown() {
        Magazine magazine = new Magazine("Kiriki", "", "2049-3630", 1991, 4);

        Set<ConstraintViolation<Magazine>> violations = validator.validate(magazine);
        assertEquals(info + 1, info + violations.size());

        ConstraintViolation<Magazine> violation = violations.iterator().next();
        assertEquals("Editor is required.", violation.getMessage());
    }

    // Q& I say "thrown" but nothing is actually thrown?
    @Test
    public void givenBlankISSN_whenCallingSetISSN_thenConstraintViolationIsThrown() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "", 1991, 4);

        Set<ConstraintViolation<Magazine>> violations = validator.validate(magazine);
        assertEquals(info + 1, info + violations.size());

        ConstraintViolation<Magazine> violation = violations.iterator().next();
        assertEquals("ISSN is required.", violation.getMessage());
    }

    @Test
    public void givenNonPositivePublicationYear_whenCallingSetPublicationYear_thenConstraintViolationIsThrown() {
        Magazine magazine = new Magazine("Kiriki", "Gorej", "2049-3630", -89, 4);

        Set<ConstraintViolation<Magazine>> violations = validator.validate(magazine);
        assertEquals(info + 1, info + violations.size());

        ConstraintViolation<Magazine> violation = violations.iterator().next();
        assertEquals("Publication year must be a non-negative integer.", violation.getMessage());
    }

    @Test
    public void givenFuturePublicationYear_whenCallingSetPublicationYear_thenDomainExceptionIsThrown() {
        Calendar calendar = Calendar.getInstance();

        Exception exPublicationYearInFuture = Assertions.assertThrows(
            DomainException.class,
            () -> new Magazine("Kiriki", "Gorej", "2049-3630", calendar.get(Calendar.YEAR) + 555, 4)
        );
        assertEquals("Publication year cannot be in the future.", exPublicationYearInFuture.getMessage());
    }

    @Test
    public void givenNegativeAvailableCopies_whenCallingSetAvailableCopies_thenConstraintViolationIsThrown() {
        Magazine magazine = new Magazine("Kiriki" , "Gorej" , "2049-3630",2004, -23);

        Set<ConstraintViolation<Magazine>> violations = validator.validate(magazine);
        assertEquals(info + 1, info + violations.size());

        ConstraintViolation<Magazine> violation = violations.iterator().next();
        assertEquals("Available copies must be a non-negative integer.", violation.getMessage());
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }
}

// Q&A Is ISSN a String? Yes.