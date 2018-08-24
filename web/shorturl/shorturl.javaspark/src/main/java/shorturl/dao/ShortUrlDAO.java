package shorturl.dao;

public interface ShortUrlDAO {

    String getShortUrl(String url);

    String getUrl(String shortUrl) throws ShortUrlNotFoundException;

}
