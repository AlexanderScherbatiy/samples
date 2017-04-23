package sample.java.util.concurrent.locks;

/**
 * Created by alexsch on 4/23/2017.
 */
public class SyncPoint implements Point {

    private double x;
    private double y;

    public SyncPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public synchronized void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public synchronized double getX() {
        return x;
    }

    @Override
    public synchronized double getY() {
        return y;
    }

    @Override
    public synchronized double getDistance() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public synchronized Point getCopy() {
        return new SyncPoint(x, y);
    }

    @Override
    public synchronized String toString() {
        return String.format("Point[%.2f, %.2f]", x, y);
    }
}
