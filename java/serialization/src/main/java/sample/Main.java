package sample;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import serialization.ByteBufferSerializer;
import serialization.Serializer;

public class Main {

    public static void main(String[] args) throws Exception {

        Person alice = new Person("Alice", 28);

        // Java ByteBufferSerializer
        Serializer byteBufferSerializer = new ByteBufferSerializer();
        byte[] bytes = byteBufferSerializer.serialize(alice);
        Person deserializedAlice = (Person) byteBufferSerializer.deserialize(bytes);
        System.out.printf("java serializer buffer size: %d, object: %s%n", bytes.length, deserializedAlice);

        // Kryo
        Kryo kryo = new Kryo();
        kryo.register(Person.class);

        ByteArrayOutputStream kryoByteArray = new ByteArrayOutputStream();
        try (Output output = new Output(kryoByteArray)) {
            kryo.writeObject(output, alice);
        }

        bytes = kryoByteArray.toByteArray();
        try (Input input = new Input(new ByteArrayInputStream(bytes))) {
            Person kryoAlice = kryo.readObject(input, Person.class);
            System.out.printf("kryo buffer size: %d, object: %s%n", kryoByteArray.toByteArray().length, kryoAlice);
        }

        // ProtoBuf
        ProtoPerson.Person protoPerson = ProtoPerson.Person
                .newBuilder()
                .setName("Alice")
                .setAge(28)
                .build();

        byte[] protobufBytes = protoPerson.toByteArray();

        ProtoPerson.Person deserializedProtoAlice = ProtoPerson.Person.parseFrom(protobufBytes);
        System.out.printf("protobuf buffer size: %d, name: %s, age: %d%n",
                protobufBytes.length, deserializedProtoAlice.getName(), deserializedProtoAlice.getAge());
    }
}
