package toy.testopenservice.exception;

public class TOSException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TOSException(String message) {
        super(message);
    }
}
