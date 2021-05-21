package sample;

import java.io.Serializable;

public class Hello implements Serializable {

    private final String name;

    public Hello(String name) {
        this.name = name;
    }

    public String hello() {
        return String.format("Hello, %s!", name);
    }
}
