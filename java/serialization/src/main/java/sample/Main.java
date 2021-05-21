package sample;

import serialization.ByteBufferSerializer;
import serialization.Serializer;

public class Main {

    public static void main(String[] args) throws Exception {

        Hello hello = new Hello("Alice");
        Serializer byteBufferSerializer = new ByteBufferSerializer();
        byte[] bytes = byteBufferSerializer.serialize(hello);
        Hello deserializedHello = (Hello) byteBufferSerializer.deserialize(bytes);

        System.out.printf("byte buffer size: %d, hello string: %s%n", bytes.length, deserializedHello.hello());

        ProtoHello.Hello protoHello = ProtoHello.Hello
                .newBuilder()
                .setName("Bob")
                .build();

        byte[] protobufBytes = protoHello.toByteArray();

        ProtoHello.Hello deserializedProtoHello = ProtoHello.Hello.parseFrom(protobufBytes);
        System.out.printf("protobuf buffer size: %d, name: %s%n", protobufBytes.length, deserializedProtoHello.getName());
    }
}
