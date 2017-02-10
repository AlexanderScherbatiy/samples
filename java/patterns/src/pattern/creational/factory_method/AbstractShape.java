package pattern.creational.factory_method;

/**
 * Created by alexsch on 2/10/2017.
 */
public class AbstractShape implements Shape {

    private final int width;
    private final int height;

    public AbstractShape(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("%s size: [%d, %d]", getClass().getSimpleName(), getWidth(), getHeight());
    }
}
