package helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.logging.Logger;

public class HelloWorldClient {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldClient.class.getName());

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    HelloWorldClient(ManagedChannel channel) {
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void hello(String name) {
        LOGGER.info("Send hello " + name + " request ...");
        HelloWorld.HelloRequest request = HelloWorld.HelloRequest.newBuilder()
                .setName(name)
                .build();

        try {
            HelloWorld.HelloReply response = blockingStub.sayHello(request);
            LOGGER.info("Greeting: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            LOGGER.warning("RPC failed: " + e.getStatus());
        }
    }

    public static void main(String[] args) throws Exception {

        String user = "world";
        if (args.length > 0) {
            user = args[0]; /* Use the arg as the name to hello if provided */
        }

        int port = 50051;
        String host = "localhost";

        ManagedChannel channel = null;
        try {

            channel = ManagedChannelBuilder.forAddress(host, port)
                    .usePlaintext()
                    .build();

            HelloWorldClient client = new HelloWorldClient(channel);
            client.hello(user);
        } finally {

            if (channel != null) {
                channel.shutdown();
            }
        }
    }
}
