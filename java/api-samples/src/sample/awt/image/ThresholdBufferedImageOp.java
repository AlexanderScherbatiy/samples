package sample.awt.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by alexsch on 4/4/2017.
 */
public class ThresholdBufferedImageOp extends AbstractBufferedImageOp {


    private final int min;
    private final int max;
    private final int threshold;

    public ThresholdBufferedImageOp(int min, int max, int threshold) {
        this.min = min;
        this.max = max;
        this.threshold = threshold;
    }

    @Override
    public int filterColor(int rgb) {

        Color color = new Color(rgb);
        int r = threshold(color.getRed());
        int g = threshold(color.getGreen());
        int b = threshold(color.getBlue());
        int a = color.getAlpha();

        return new Color(r, g, b, a).getRGB();
    }

    private int threshold(int value) {
        return value < threshold ? min : max;
    }
}
