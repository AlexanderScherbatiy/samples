package sample.java.util.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by alexsch on 4/23/2017.
 */
public class ReadWriteLockPoint implements Point {

    private double x;
    private double y;

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public ReadWriteLockPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void set(double x, double y) {
        writeLock.lock();
        try {
            this.x = x;
            this.y = y;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public double getX() {
        readLock.lock();
        try {
            return x;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public double getY() {
        readLock.lock();
        try {
            return y;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public double getDistance() {

        double curX;
        double curY;
        readLock.lock();
        try {
            curX = x;
            curY = y;
        } finally {
            readLock.unlock();
        }

        return Math.sqrt(curX * curX + curY * curY);
    }

    @Override
    public Point getCopy() {
        readLock.lock();
        try {
            return new ReadWriteLockPoint(x, y);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public String toString() {

        double curX;
        double curY;
        readLock.lock();
        try {
            curX = x;
            curY = y;
        } finally {
            readLock.unlock();
        }

        return String.format("Point[%.2f, %.2f]", curX, curY);
    }
}
