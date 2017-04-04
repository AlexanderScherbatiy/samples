package sample.awt.image;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;

/**
 * Created by alexsch on 4/4/2017.
 */
public class ColorInvertBufferedImageOp extends AbstractBufferedImageOp {
    @Override
    public int filterColor(int rgb) {
        return 0xFFFFFFFF - rgb;
    }
}
