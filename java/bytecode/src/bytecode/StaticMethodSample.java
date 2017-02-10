package bytecode;

/**
 * Created by alexsch on 2/10/2017.
 */
public class StaticMethodSample {
    /*
      public static int sum(int, int);
        Code:
           0: iload_0
           1: iload_1
           2: iadd
           3: ireturn
        LineNumberTable:
          line 10: 0
     */
    public static int sum(int a, int b) {
        return a + b;
    }
}
