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
public abstract class AbstractBufferedImageOp implements BufferedImageOp {
    
    public abstract int filterColor(int rgb);

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {

        int w = src.getWidth();
        int h = src.getHeight();

        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                dest.setRGB(i, j, filterColor(src.getRGB(i, j)));
            }
        }

        return dest;
    }

    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {

        if (destCM == null) {
            destCM = src.getColorModel();
        }

        int w = src.getWidth();
        int h = src.getHeight();

        return new BufferedImage(destCM,
                destCM.createCompatibleWritableRaster(w, h),
                destCM.isAlphaPremultiplied(),
                null
        );
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
    public Rectangle2D getBounds2D(BufferedImage src) {
        return src.getRaster().getBounds();
    }

    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }
}
