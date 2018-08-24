package shorturl.util;

public class HashShortUrlGenerator implements ShortUrlGenerator {

    @Override
    public String createShortUrl(String url) {
        return String.format("u%d", url.hashCode());
    }
}
