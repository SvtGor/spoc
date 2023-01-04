package pl.hirely.springboot.booklibrary.model.service.exception;

public class NoBookException extends RuntimeException {
    public NoBookException(String message) {
        super(message);
    }
}
