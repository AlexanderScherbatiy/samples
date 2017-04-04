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
public class ColorInvertBufferedImageOp implements BufferedImageOp {

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {

        int w = src.getWidth();
        int h = src.getHeight();

        if (dest == null) {
            dest = new BufferedImage(w, h, src.getType());
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int rgb = src.getRGB(i, j);
                rgb = 0xFFFFFF - rgb;
                dest.setRGB(i, j, rgb);
            }
        }

        return dest;
    }

    @Override
    public Rectangle2D getBounds2D(BufferedImage src) {
        return new Rectangle(src.getWidth(), src.getHeight());
    }

    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
        return null;
    }

    @Override
    public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
        double x = srcPt.getX();
        double y = srcPt.getY();
        if (dstPt == null) {
            return new Point2D.Double(x, y);
        }
        dstPt.setLocation(x, y);
        return dstPt;
    }

    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }
}
