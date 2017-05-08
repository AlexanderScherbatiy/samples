package datatype.concurrent.actor;

/**
 * Created by alexsch on 5/7/2017.
 */
public interface Actor {

    void sendMessage(Object message, Actor sender);
}
