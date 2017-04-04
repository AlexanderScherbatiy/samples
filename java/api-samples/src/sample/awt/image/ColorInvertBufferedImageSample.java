package sample.awt.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

/**
 * Created by alexsch on 4/4/2017.
 */
public class ColorInvertBufferedImageSample {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(900, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel() {

                @Override
                public void paint(Graphics g) {
                    super.paint(g);

                    int w = getWidth() / 3;
                    int h = getHeight();

                    BufferedImage image = createSampleImage(w, h);
                    BufferedImageOp invertedOp = new ColorInvertBufferedImageOp();
                    BufferedImageOp thresholdOp = new ThresholdBufferedImageOp(50, 200, 125);

                    BufferedImage invertedImage = invertedOp.filter(image, null);
                    BufferedImage thresholdImage = thresholdOp.filter(image, null);


                    g.drawImage(image, 0, 0, this);
                    g.drawImage(invertedImage, w, 0, this);
                    g.drawImage(thresholdImage, 2 * w, 0, this);
                }
            };

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static BufferedImage createSampleImage(int w, int h) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLUE);
        g.drawLine(0, h / 2, w, h / 2);
        g.drawLine(w / 2, 0, w / 2, h);
        g.setColor(Color.RED);
        g.drawOval(0, 0, w, h);
        return image;
    }
}
