package pattern.creational.factory_method;

/**
 * Created by alexsch on 2/10/2017.
 */
public class FactoryMethodSample {

    public static void main(String[] args) {
        Shape shape = ShapeFactory.createShape("rectangle", 5, 3);
        System.out.println("shape: " + shape);

        shape = ShapeFactory.createShape("ellipse", 4, 2);
        System.out.println("shape: " + shape);

        shape = ShapeFactory.createShape("square", 7);
        System.out.println("shape: " + shape);
    }
}
