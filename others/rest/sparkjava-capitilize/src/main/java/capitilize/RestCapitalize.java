package capitilize;

import static spark.Spark.*;

public class RestCapitalize {


    public static void main(String[] args) {

        port(5678);
        init();

        get("/capitalize/:word", (request, response) -> {

            String word = request.params("word");
            return word.toUpperCase();
        });
    }
}
