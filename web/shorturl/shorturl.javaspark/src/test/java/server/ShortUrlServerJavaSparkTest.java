package server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import shorturl.dao.HashMapShortUrlDao;
import shorturl.server.ShortUrlServer;
import shorturl.server.ShortUrlServerJavaSpark;
import shorturl.util.HashShortUrlGenerator;
import shorturl.util.ShortUrlGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShortUrlServerJavaSparkTest {

    private static final int PORT = 12345;
    private static final String URL = "testUrl";
    private static ShortUrlGenerator shortUrlGenerator;
    private static ShortUrlServer shortUrlServer;

    @BeforeClass
    public static void setUp() {
        shortUrlGenerator = new HashShortUrlGenerator();
        shortUrlServer = new ShortUrlServerJavaSpark(PORT, new HashMapShortUrlDao(shortUrlGenerator));
        shortUrlServer.start();
    }

    @AfterClass
    public static void tearDown() {
        shortUrlServer.stop();
    }

    @Test
    public void testCreate() throws Exception {
        String url = URL;
        String response = String.format("url: %s, short url: %s", url, shortUrlGenerator.createShortUrl(url));
        String httpUrl = String.format("http://localhost:%d/create/%s", PORT, url);
        String result = read(httpUrl);
        assertEquals(response, result);
    }

    @Test
    public void testOpen() throws Exception {
        String url = URL;
        String shortUrl = shortUrlGenerator.createShortUrl(url);
        String httpUrl = String.format("http://localhost:%d/open/%s", PORT, shortUrl);
        String result = read(httpUrl);
        assertTrue(result.contains(url));
    }

    private String read(String httpUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(httpUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(false);
        connection.connect();

        try (InputStream is = connection.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        } finally {
            connection.disconnect();
        }
    }
}
