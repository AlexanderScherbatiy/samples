package serialization;

import java.io.Serializable;
import java.util.Objects;

public class TestSerializationClass implements Serializable {

    private final int numInt;
    private final long longInt;
    private final float numFloat;
    private final double numDouble;
    private final String str;

    public TestSerializationClass(int numInt, long longInt, float numFloat, double numDouble, String str) {
        this.numInt = numInt;
        this.longInt = longInt;
        this.numFloat = numFloat;
        this.numDouble = numDouble;
        this.str = str;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSerializationClass that = (TestSerializationClass) o;
        return numInt == that.numInt &&
                longInt == that.longInt &&
                Float.compare(that.numFloat, numFloat) == 0 &&
                Double.compare(that.numDouble, numDouble) == 0 &&
                str.equals(that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numInt, longInt, numFloat, numDouble, str);
    }
}
