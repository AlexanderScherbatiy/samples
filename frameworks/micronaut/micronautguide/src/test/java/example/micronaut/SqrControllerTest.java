package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class SqrControllerTest {

    @Inject
    @Client("/sqr")
    HttpClient client;

    @Test
    public void testSqr() {
        HttpRequest<String> request = HttpRequest.GET("/4");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("16", body);
    }
}
