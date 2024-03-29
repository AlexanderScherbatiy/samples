package serialization;

import java.io.IOException;

public interface Serializer {

    byte[] serialize(Object obj) throws IOException;

    Object deserialize(byte[] array) throws IOException, ClassNotFoundException;
}
