package shorturl.dao;

import shorturl.util.ShortUrlGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HashMapShortUrlDao implements ShortUrlDAO {

    private final Map<String, String> urlMap = new HashMap<>();
    private final Map<String, String> shortUrlMap = new HashMap<>();
    private final Lock lock = new ReentrantLock();
    private final ShortUrlGenerator shortUrlGenerator;

    public HashMapShortUrlDao(ShortUrlGenerator shortUrlGenerator) {
        this.shortUrlGenerator = shortUrlGenerator;
    }

    @Override
    public String getShortUrl(String url) {

        try {
            lock.lock();
            String shortUrl = shortUrlMap.get(url);
            if (shortUrl == null) {
                shortUrl = shortUrlGenerator.createShortUrl(url);
                shortUrlMap.put(url, shortUrl);
                urlMap.put(shortUrl, url);
            }
            return shortUrl;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getUrl(String shortUrl) throws ShortUrlNotFoundException {

        String url = null;
        try {
            lock.lock();
            url = urlMap.get(shortUrl);
        } finally {
            lock.unlock();
        }
        if (url == null) {
            throw new ShortUrlNotFoundException(String.format("Short url not found: %s", shortUrl));
        }
        return url;
    }
}
