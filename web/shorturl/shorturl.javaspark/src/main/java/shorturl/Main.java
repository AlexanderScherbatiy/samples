package shorturl;

import shorturl.dao.HashMapShortUrlDao;
import shorturl.dao.ShortUrlDAO;
import shorturl.server.ShortUrlServer;
import shorturl.server.ShortUrlServerJavaSpark;
import shorturl.util.ShortUrlGenerator;
import shorturl.util.HashShortUrlGenerator;

public class Main {

    public static void main(String[] args) {

        ShortUrlGenerator shortUrlGenerator = new HashShortUrlGenerator();
        ShortUrlDAO dao = new HashMapShortUrlDao(shortUrlGenerator);
        ShortUrlServer server = new ShortUrlServerJavaSpark(dao);
        server.start();
    }
}
