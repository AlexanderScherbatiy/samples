package shorturl.dao;

import org.junit.Test;
import shorturl.util.HashShortUrlGenerator;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HashMapShortUrlDaoTest {

    @Test
    public void testShortUrl() {
        ShortUrlDAO dao = createDao();

        String url1 = dao.getShortUrl("1");
        assertNotNull(url1);
        assertTrue(url1.equals(dao.getShortUrl("1")));

        String url2 = dao.getShortUrl("2");
        assertNotNull(url2);
        assertTrue(url2.equals(dao.getShortUrl("2")));

        assertNotEquals(url1, url2);
    }

    @Test
    public void testUrl() throws ShortUrlNotFoundException {

        ShortUrlDAO dao = createDao();
        String url = "1";
        String shortUrl = dao.getShortUrl(url);
        assertTrue(url.equals(dao.getUrl(shortUrl)));
    }

    @Test(expected = ShortUrlNotFoundException.class)
    public void testUrlNotFound() throws ShortUrlNotFoundException {

        ShortUrlDAO dao = createDao();
        dao.getUrl("nonexistent");
    }

    private ShortUrlDAO createDao() {
        return new HashMapShortUrlDao(new HashShortUrlGenerator());
    }
}