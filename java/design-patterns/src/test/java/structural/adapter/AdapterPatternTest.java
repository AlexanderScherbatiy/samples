package structural.adapter;

import org.junit.Test;
import java.util.StringTokenizer;
import static org.junit.Assert.*;

public class AdapterPatternTest {

    @Test
    public void testAdapterPattern() {

        StringTokenizer tokenizer = new StringTokenizer("Hello World");

        Iterable<String> iterable = new IterableAdapter(tokenizer);

        int i = 0;
        for (String str : iterable) {
            switch (i) {
                case 0:
                    assertEquals("Hello", str);
                    break;
                case 1:
                    assertEquals("World", str);
                    break;

                default:
                    fail("Wrong string number!");
            }
            i++;
        }
    }
}
