package capitilize;

import org.junit.Test;

import static org.junit.Assert.*;

import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Unit test for simple RestCapitalize.
 */
public class RestCapitalizeTest {


    @Test
    public void test() {
        String body = request("GET", "/capitalize/test");
        assertEquals("TEST", body);
    }

    private String request(String method, String path) {
        try {
            URL url = new URL("http://localhost:5678" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());

            return body;
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

}
