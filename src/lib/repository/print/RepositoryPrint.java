package lib.repository.print;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.io.FileInputStream;
import java.io.IOException;

public class RepositoryPrint {
    /*public static void printSelectedPngFile(PrintService myService, String filePathAndName){
        try {
            FileInputStream fis = new FileInputStream(filePathAndName);
            Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PNG, null);
            DocPrintJob printJob = myService.createPrintJob();
            printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
            fis.close();
        } catch (PrintException printException){
            printException.printStackTrace();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }*/

    /*public static void printSelectedPngFile(PrintService myService, String filePathAndName){
        try {
            FileInputStream fis = new FileInputStream(filePathAndName);

            SimpleDoc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PNG, null);

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

            aset.add(new Copies(1));
            aset.add(MediaSizeName.NA_5X7);
            aset.add(Sides.DUPLEX);

            Object printableArea = myService.getSupportedAttributeValues(MediaPrintableArea.class, null, aset);

            DocPrintJob printJob = myService.createPrintJob();
            printJob.print(pdfDoc, aset);
            fis.close();
        } catch (PrintException printException){
            printException.printStackTrace();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }*/

 /*   PrinterJob printJob = PrinterJob.getPrinterJob();
    printJob.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex != 0) {
                    return NO_SUCH_PAGE;
                }
                graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                return PAGE_EXISTS;
            }
        });
    try {
        printJob.print();
    } catch (PrinterException e1) {
        e1.printStackTrace();
    }*/

    public static void printSelectedPngFile(PrintService myService, String filePathAndName){
        try {
            FileInputStream fis = new FileInputStream(filePathAndName);

            DocAttributeSet das = new HashDocAttributeSet();
            das.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
            das.add(new MediaPrintableArea(0, 0, 40, 100, MediaPrintableArea.MM));

            SimpleDoc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PNG, das);

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
            aset.add(new MediaPrintableArea(0, 0, 40, 100, MediaPrintableArea.MM));
            aset.add(new Copies(1));
            aset.add(Sides.DUPLEX);

            DocPrintJob printJob = myService.createPrintJob();

            printJob.print(pdfDoc, aset);
            fis.close();
        } catch (PrintException printException){
            printException.printStackTrace();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
