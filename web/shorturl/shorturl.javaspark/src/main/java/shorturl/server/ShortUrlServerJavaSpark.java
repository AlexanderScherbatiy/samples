package shorturl.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shorturl.dao.ShortUrlDAO;
import shorturl.dao.ShortUrlNotFoundException;
import spark.Spark;

import static spark.Spark.exception;
import static spark.Spark.port;
import static spark.Spark.get;

public class ShortUrlServerJavaSpark implements ShortUrlServer {

    private static final Logger logger = LoggerFactory.getLogger(ShortUrlServerJavaSpark.class);

    private final int port;
    private final ShortUrlDAO shortUrlDAO;

    public ShortUrlServerJavaSpark(ShortUrlDAO shortUrlDAO) {
        this(8080, shortUrlDAO);
    }

    public ShortUrlServerJavaSpark(int port, ShortUrlDAO shortUrlDAO) {
        this.port = port;
        this.shortUrlDAO = shortUrlDAO;
    }

    public void start() {

        port(port);

        get("/create/:name", (request, response) ->
        {
            String url = request.params(":name");
            String shortURL = shortUrlDAO.getShortUrl(url);
            return String.format("url: %s, short url: %s", url, shortURL);
        });

        get("/open/:name", (request, response) ->
        {
            String shortURL = request.params(":name");
            String url = shortUrlDAO.getUrl(shortURL);
            return String.format("%s: <a href='http://%s'>%s</a>", shortURL, url, url);
        });

        exception(ShortUrlNotFoundException.class, (exception, request, response) ->
        {
            logger.error(exception.getMessage());
            response.body(exception.getMessage());
        });
    }

    @Override
    public void stop() {
        Spark.stop();
    }
}
