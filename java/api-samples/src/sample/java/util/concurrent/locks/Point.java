package sample.java.util.concurrent.locks;

/**
 * Created by alexsch on 4/23/2017.
 */
public interface Point {

    void set(double x, double y);

    double getX();

    double getY();

    Point getCopy();

    double getDistance();
}
