package serialization;

import java.io.Serializable;
import java.util.Objects;

public class CyclicClass2 implements Serializable {

    private final String name;

    private CyclicClass1 object1;

    public CyclicClass2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CyclicClass1 getObject1() {
        return object1;
    }

    public void setObject1(CyclicClass1 object1) {
        this.object1 = object1;
    }
}
