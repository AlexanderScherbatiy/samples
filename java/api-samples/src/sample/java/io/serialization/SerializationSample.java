package sample.java.io.serialization;

import java.io.*;

/**
 * Created by alexsch on 4/22/2017.
 */
public class SerializationSample {


    static class Point implements Serializable {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("Point[%d, %d]", x, y);
        }
    }

    public static void main(String[] args) throws Exception {

        File pointFile = File.createTempFile("point", ".ser");
        pointFile.deleteOnExit();

        System.out.println("file: " + pointFile.getAbsolutePath());
        Point point = new Point(3, 5);
        System.out.printf("initial: %s%n", point);

        try (
                FileOutputStream fos = new FileOutputStream(pointFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {

            oos.writeObject(point);
            oos.flush();
        }
        point.x = -point.x;
        point.y = -point.y;

        System.out.printf("changed: %s%n", point);

        try (
                FileInputStream fis = new FileInputStream(pointFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            Point p = (Point) ois.readObject();
            System.out.printf("read   : %s%n", p);
        }
    }
}
