package datatype.concurrent.actor;

/**
 * Created by alexsch on 5/7/2017.
 */
public interface ActorFactory {


    Actor createActor(MessageHandler messageHandler);

    interface MessageHandler {
        void handleMessage(Object message, Actor sender);
    }
}
