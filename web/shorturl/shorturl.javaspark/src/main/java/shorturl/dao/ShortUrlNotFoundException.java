package shorturl.dao;

public class ShortUrlNotFoundException extends Exception {

    public ShortUrlNotFoundException(String message) {
        super(message);
    }
}
