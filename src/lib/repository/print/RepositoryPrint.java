package lib.repository.print;

import lib.settings.AppSettings;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RepositoryPrint {
    public static void printSelectedPngFile(PrintService myService, String filePathAndName){
        try {
            FileInputStream fis = new FileInputStream(filePathAndName);
            Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PNG, null);
            DocPrintJob printJob = myService.createPrintJob();

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
            aset.add(new MediaPrintableArea(0, 0, 100, 40, MediaPrintableArea.MM));

            printJob.print(pdfDoc, aset);
            fis.close();
        } catch (PrintException printException){
            printException.printStackTrace();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /*consoleRun:  print /d:"TSC TTP-244CE ETHERNET" printer_.png*/
                /*Printer control managment*/
                /*printmanagement.msc*/

                /*Set Default printer*/
                /*consoleRun: RUNDLL32 PRINTUI.DLL,PrintUIEntry /y /n "TSC TTP-244CE ETHERNET" */

                /*QUEE printer*/
                /*rundll32 printui.dll,PrintUIEntry /o /n "TSC TTP-244CE ETHERNET"*/

                /*printer property*/
                /*rundll32 printui.dll,PrintUIEntry /p /n "TSC TTP-244CE ETHERNET"*/

                /*printer sample page print*/
                /*rundll32 printui.dll,PrintUIEntry /p /n "TSC TTP-244CE ETHERNET"*/


    /*Print with OTHER utility*/
            /*"C:\Program Files (x86)\Printfil\Printfil" printer__.png "TSC TTP-244CE ETHERNET"*/
            /*C:\Program Files\IrfanView\i_view64.exe' .\test_image.png /print*/

    /*Cmd open photo viewer Windows*/
    /*rundll32 PhotoViewer.dll, ImageView_Fullscreen*/

    /*Cmd print photo Windows standart library*/
        /*rundll32 shimgvw.dll ImageView_PrintTo /pt d:\Printer\pdf\test.jpg "TSC TTP-244CE ETHERNET"*/
                                                                             /* "Microsoft Print to PDF"*/
                        /*
                        ImageView_Fullscreen
                        ImageView_FullscreenA
                        ImageView_FullscreenW
                        ImageView_PrintTo
                        ImageView_PrintToA
                        ImageView_PrintToW
                        ImageView_fullscreenW
                        DllCanUnloadNow
                        DllGetClassObject
                        */

        /*mspaint /pt d:\Printer\pdf\test.jpg "TSC TTP-244CE ETHERNET*/

    public static void printSelectedPngFileAsImage(PrintService myService, String filePathAndName){
        try {
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPrintService(myService);

            final BufferedImage bufferedImage = ImageIO.read(new File(filePathAndName));
            printJob.setPrintable(new Printable() {
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex != 0) {
                        return NO_SUCH_PAGE;
                    }
                    graphics.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
                    return PAGE_EXISTS;
                }
            });

            try {
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

                aset.add(new PrinterResolution(AppSettings.PRINTER_RESOLUTION_DPI_X, AppSettings.PRINTER_RESOLUTION_DPI_Y, PrinterResolution.DPI));
                aset.add(new MediaPrintableArea(0, 0, AppSettings.PRINTER_PAPER_WIDTH_MM, AppSettings.PRINTER_PAPER_HEIGHT_MM, MediaPrintableArea.MM));
                /*aset.add(OrientationRequested.LANDSCAPE);*/
                aset.add(OrientationRequested.PORTRAIT);

                /*PageFormat pageFormat = printJob.getPageFormat(aset);
                printJob.pageDialog(pageFormat);*/
                /*printJob.pageDialog(aset);
                printJob.printDialog();*/

                printJob.print(aset);
            } catch (PrinterException e1) {
                e1.printStackTrace();
            }

        } catch (PrinterException e) {
            throw new RuntimeException(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void printSelectedPngFileAsSimpleDoc(PrintService myService, String filePathAndName){
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
