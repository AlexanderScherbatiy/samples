package sample.java.util.concurrent.locks;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by alexsch on 4/23/2017.
 */
public class StampedPoint implements Point {

    private double x;
    private double y;

    private final StampedLock stampedLock = new StampedLock();

    public StampedPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void set(double x, double y) {
        long stamp = stampedLock.writeLock();
        try {
            this.x = x;
            this.y = y;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    @Override
    public double getX() {
        long stamp = stampedLock.tryOptimisticRead();
        double curX = x;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                curX = x;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return curX;
    }

    @Override
    public double getY() {
        long stamp = stampedLock.tryOptimisticRead();
        double curY = y;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                curY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return curY;
    }

    @Override
    public double getDistance() {

        long stamp = stampedLock.tryOptimisticRead();
        double curX = x;
        double curY = y;

        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(curX * curX + curY * curY);
    }

    @Override
    public Point getCopy() {

        long stamp = stampedLock.tryOptimisticRead();
        double curX = x;
        double curY = y;

        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return new StampedPoint(curX, curY);
    }

    @Override
    public String toString() {
        long stamp = stampedLock.tryOptimisticRead();
        double curX = x;
        double curY = y;

        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return String.format("Point[%.2f, %.2f]", curX, curY);
    }
}
