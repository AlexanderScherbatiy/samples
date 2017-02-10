package pattern.creational.factory_method;

/**
 * Created by alexsch on 2/10/2017.
 */
public class ShapeFactory {

    public static Shape createShape(String shapeName, int... props) {

        switch (shapeName.toLowerCase()) {
            case "rectangle":
                return new RectangleShape(props[0], props[1]);
            case "ellipse":
                return new RectangleShape(props[0], props[1]);
            case "square":
                return new SquareShape(props[0]);
            default:
                throw new IllegalArgumentException(shapeName);
        }
    }
}
