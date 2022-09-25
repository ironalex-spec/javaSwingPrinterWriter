package lib.repository.print;

import lib.repository.file.RepositoryConsole;
import lib.repository.file.RepositoryFileLabel;
import lib.app.Settings;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RepositoryPrint {

    public static boolean printerPrint(PrintService myService, String filePathAndName){
        boolean isPrint = false;

        switch (Settings.typePrinting){
            case 0 :
                isPrint = printSelectedPngFileAsWindowsPhotoViewer(filePathAndName);
                break;

            case 1:
                isPrint = printSelectedPngFileAsImage(myService, filePathAndName);
                break;

            case 2:
                isPrint = printSelectedPngFile(myService, filePathAndName);
                break;

            case 3:
                isPrint = printSelectedPngFileAsSimpleDoc(myService, filePathAndName);
                break;

            default:
                isPrint = printSelectedPngFileAsWindowsPhotoViewer(filePathAndName);
                break;
        }

        return isPrint;
    }
    private static boolean printSelectedPngFile(PrintService myService, String filePathAndName){
        boolean isExecute = false;
        try {
            FileInputStream fis = new FileInputStream(filePathAndName);
            Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PNG, null);
            DocPrintJob printJob = myService.createPrintJob();

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
            aset.add(new MediaPrintableArea(0, 0, 100, 40, MediaPrintableArea.MM));

            printJob.print(pdfDoc, aset);
            fis.close();
            isExecute = true;
        } catch (PrintException printException){
            printException.printStackTrace();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return isExecute;
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

    /*PowerShell need work only with scripts*/
    /*powershell.exe -executionpolicy remotesigned src\resources\scripts\powershell\print.ps1*/
    /*powershell start-process -verb Print d:\Education\Own\JAVA\Projects\MyPrinterApp\src\resources\editor\img\label\179237333.bmp*/

    private static boolean printSelectedPngFileAsImage(PrintService myService, String filePathAndName){
        boolean isExecute = false;
        try {
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPrintService(myService);

            final BufferedImage bufferedImage = ImageIO.read(new File(filePathAndName));
            printJob.setPrintable(new Printable() {
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex != 0) {
                        return NO_SUCH_PAGE;
                    }
                    graphics.drawImage(bufferedImage,

                            (int) (Settings.PRINTER_PAPER_PADDING_LEFT_MM * Settings.inchToCmValue),
                            (int) (Settings.PRINTER_PAPER_PADDING_TOP_MM * Settings.inchToCmValue),

                            (int) (Settings.PRINTER_PAPER_WIDTH_MM * Settings.inchToCmValue),
                            (int) (Settings.PRINTER_PAPER_HEIGHT_MM * Settings.inchToCmValue), null);

                    return PAGE_EXISTS;
                }
            });

            try {
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

                aset.add(new PrinterResolution(Settings.PRINTER_RESOLUTION_DPI_X, Settings.PRINTER_RESOLUTION_DPI_Y, PrinterResolution.DPI));
                aset.add(new MediaPrintableArea(0, 0, Settings.PRINTER_PAPER_WIDTH_MM, Settings.PRINTER_PAPER_HEIGHT_MM, MediaPrintableArea.MM));
                /*aset.add(OrientationRequested.LANDSCAPE);*/
                aset.add(OrientationRequested.PORTRAIT);

                //myPaper.setImageableArea(0, 0, 0, 0);

                PageFormat pageFormat = printJob.getPageFormat(aset);

                Paper myPaper = pageFormat.getPaper();
                myPaper.setSize(Settings.PRINTER_PAPER_WIDTH_MM * Settings.inchToCmValue * 72.0, Settings.PRINTER_PAPER_HEIGHT_MM * Settings.inchToCmValue * 72.0);
                myPaper.setImageableArea(0.0, 0.0, Settings.PRINTER_PAPER_WIDTH_MM * Settings.inchToCmValue * 72.0, Settings.PRINTER_PAPER_HEIGHT_MM * Settings.inchToCmValue * 72.0);

                pageFormat.setPaper(myPaper);

                //printJob.pageDialog(pageFormat);

                printJob.print(aset);
                isExecute = true;
            } catch (PrinterException e1) {
                e1.printStackTrace();
            }

        } catch (PrinterException e) {
            throw new RuntimeException(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return isExecute;
    }

    private static boolean printSelectedPngFileAsSimpleDoc(PrintService myService, String filePathAndName){
        boolean isExecute = false;
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
            isExecute = true;
        } catch (PrintException printException){
            printException.printStackTrace();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return isExecute;
    }

    private static boolean printSelectedPngFileAsWindowsPhotoViewer(String filepath){
        boolean isExecute = false;

        String dir = System.getProperty("user.dir");

        /*Change path dividers*/
        filepath = filepath.replace('/','\\');

        RepositoryFileLabel.getInstance().replaceLineFile("src/resources/scripts/powershell/templatePrint.vbs", 1,
                "Const sPathFile = \""+ dir + "\\"+ filepath + "\"");

        String[] commands = new String[]{"cscript", "src/resources/scripts/powershell/templatePrint.vbs"};

        isExecute = RepositoryConsole.getInstance().consoleExecuteOtherThread(commands);

        return isExecute;
    }
}
