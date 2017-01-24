package deadlock;

/**
 * Created by alexsch.
 */
public class ClassInitializationDeadlock {
    static class A {
        static final B b = new B();
    }

    static class B {
        static final A a = new A();
    }

    public static void main(String[] args) {
        new Thread(A::new).start();
        new B();
    }
}
