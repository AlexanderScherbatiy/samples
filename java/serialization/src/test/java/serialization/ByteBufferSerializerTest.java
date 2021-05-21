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

    @Test
    public void testCyclic() throws Exception {

        CyclicClass1 obj1 = new CyclicClass1("obj1");
        CyclicClass2 obj2 = new CyclicClass2("obj2");

        obj1.setObject2(obj2);
        obj2.setObject1(obj1);

        Serializer serializer = new ByteBufferSerializer();
        byte[] bytes = serializer.serialize(obj1);
        CyclicClass1 deserializedClass = (CyclicClass1) serializer.deserialize(bytes);

        Assert.assertEquals(obj1.getName(), deserializedClass.getName());
        Assert.assertEquals(obj1.getObject2().getName(), deserializedClass.getObject2().getName());
        Assert.assertEquals(obj1.getObject2().getObject1().getName(), deserializedClass.getObject2().getObject1().getName());
    }
}
