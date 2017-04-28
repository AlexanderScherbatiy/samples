package sample.java.lang;

import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by alexsch on 4/27/2017.
 */
public class MethodOverride {

    public static class ReturnTypeA {
    }

    public static class ReturnTypeB extends ReturnTypeA {
    }

    public static class ArgumentTypeA {
    }

    public static class ArgumentTypeB extends ArgumentTypeA {
    }

    public static class ExceptionA extends Exception {
    }

    public MethodOverride() {
        super();
    }

    public static class ExceptionB extends ExceptionA {
    }


    public static class A {

        protected ReturnTypeA method(ArgumentTypeA a) throws ExceptionA {
            return new ReturnTypeA();
        }
    }

    public static class B extends A {

        @Override
        protected ReturnTypeB method(ArgumentTypeA a) throws ExceptionB {
            return new ReturnTypeB();
        }

/*
        // Parent return type changing is not allowed
        // Argument type changing is not allowed
        // Parent exception is not allowed
        @Override
        private Object method(ArgumentTypeB a) throws Exception {
            return new ReturnTypeB();
        }
*/
    }
}
