package be.ucll.model;

// Should we use RuntimeException or Exception? If we use Exception, then the build fails if we do not handle all of them.
public class DomainException extends RuntimeException {

    // public String field;

    public DomainException(String errorMessage) {
        super(errorMessage); // https://www.baeldung.com/java-new-custom-exception
    }

    // public DomainException(String field, String errorMessage) {
    //     super(errorMessage);
    //     this.field = field;
    // }

    // public String getField() {
    //     return field;
    // }
}

// Q&A Is this class written in a way that is expected? Y
