package datatype.concurrent.actor;

/**
 * Created by alexsch on 5/8/2017.
 */
public class SumActorTest {

    public static void main(String[] args) {

        runEchoTest(new SynchronizedActorFactory());
    }

    private static void runEchoTest(ActorFactory factory) {

        Actor sum = factory.createActor(new ActorFactory.MessageHandler() {

            int sum = 0;

            @Override
            public void handleMessage(Object message, Actor sender) {
                try {
                    int n = Integer.parseInt(message.toString());
                    if (n < 0) {
                        System.out.printf("sum: %d\n", sum);
                        sum = 0;
                    } else {
                        sum += n;
                    }
                } catch (NumberFormatException ignore) {
                }
            }
        });


        final int[] ints = {1, 10, 100, 1_000, 10_000, 100000};

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j <= ints[i]; j++) {
                sum.sendMessage(Integer.toString(j), null);
            }
            sum.sendMessage("-1", null);
        }
    }
}
