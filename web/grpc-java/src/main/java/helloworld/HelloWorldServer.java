package helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldServer {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldServer.class.getName());

    public static void main(String[] args) throws Exception {

        int port = 50051;

        Server server = null;
        try {
            server = ServerBuilder
                    .forPort(port)
                    .addService(new HelloWorldImpl())
                    .build()
                    .start();

            LOGGER.info("Server started, listening on port " + port);
            server.awaitTermination();
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }
    }

    static class HelloWorldImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloWorld.HelloRequest req, StreamObserver<HelloWorld.HelloReply> responseObserver) {
            String name = req.getName();
            String replyMessage = String.format("Hello, %s!", name);
            LOGGER.info(String.format("request: '%s', response: '%s'", name, replyMessage));
            HelloWorld.HelloReply reply = HelloWorld.HelloReply.newBuilder().setMessage(replyMessage).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
