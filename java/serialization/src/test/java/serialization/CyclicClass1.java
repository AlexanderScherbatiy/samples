package serialization;

import java.io.Serializable;

public class CyclicClass1 implements Serializable {

    private final String name;
    private CyclicClass2 object2;

    public CyclicClass1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CyclicClass2 getObject2() {
        return object2;
    }

    public void setObject2(CyclicClass2 object2) {
        this.object2 = object2;
    }
}
