package example.micronaut;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/sqr")
public class SqrController {

    @Get(value = "/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    String sqr(String value) {
        int v = Integer.parseInt(value);
        int sqr = v * v;
        return Integer.toString(sqr);
    }
}
