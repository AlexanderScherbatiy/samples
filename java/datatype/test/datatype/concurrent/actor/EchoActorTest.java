package datatype.concurrent.actor;

/**
 * Created by alexsch on 5/8/2017.
 */
public class EchoActorTest {

    public static void main(String[] args) {

        runEchoTest(new SynchronizedActorFactory());
    }

    private static void runEchoTest(ActorFactory factory) {

        Actor echo = factory.createActor((msg, act) -> {
            System.out.printf("[echo] '%s'\n", msg);
        });

        final String[] messages = {
                "Hello, World!",
                "Hello, Actor!"
        };

        for (String message : messages) {
            echo.sendMessage(message, null);
        }
    }
}
