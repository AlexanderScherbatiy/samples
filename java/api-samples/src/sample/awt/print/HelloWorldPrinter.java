package sample.awt.print;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * Created by alexsch on 2/1/2017.
 */
public class HelloWorldPrinter implements Printable {

    public static void main(String[] args) throws Exception {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new HelloWorldPrinter());

        boolean doPrint = job.printDialog();
        if (doPrint) {
            job.print();
        }
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        g.drawString("Hello world!", 100, 100);
        return PAGE_EXISTS;
    }
}
