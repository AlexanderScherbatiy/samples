package serialization;

import org.junit.Assert;
import org.junit.Test;

public class ByteBufferSerializerTest {

    @Test
    public void test() throws Exception {

        TestSerializationClass testClass = new TestSerializationClass(1, 2l, 3f, 4d, "abc");

        Serializer serializer = new ByteBufferSerializer();
        byte[] bytes = serializer.serialize(testClass);
        TestSerializationClass deserializedClass = (TestSerializationClass) serializer.deserialize(bytes);

        Assert.assertEquals(testClass, deserializedClass);
    }
}
