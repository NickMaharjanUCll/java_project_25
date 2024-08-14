package be.ucll.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String errorMessage) {
        super(errorMessage);
    }
}
