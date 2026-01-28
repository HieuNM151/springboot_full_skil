package vn.hieunm.exception;

public class ResourceNotFoundExeption extends RuntimeException{
    public ResourceNotFoundExeption(String message) {
        super(message);
    }

    public ResourceNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
