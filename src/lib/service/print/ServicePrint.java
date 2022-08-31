package lib.service.print;

import lib.repository.paint.RepositoryPicture;
import lib.repository.print.RepositoryPrint;
import lib.app.Settings;

import javax.print.*;
import java.awt.image.BufferedImage;

public class ServicePrint {
    private static PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);

    public static String[] getAvailiblePrinters(){
        if (ps.length == 0) {
            return null;
        } else {
            String[] printerNames = new String[ps.length];
            int i = 0;
            for (PrintService printService : ps) {
                printerNames[i] = printService.getName();
                i++;
            }
            return printerNames;
        }
    }

    public static void printSelectedPngFile(String printerName, String filePathAndName){
        if (ps.length == 0) {
            throw new IllegalStateException("No Printer found");
        }

        PrintService myService = null;

        for (PrintService printService : ps) {
            if (printService.getName().equals(printerName)) {
                myService = printService;
                break;
            }
        }

        if (myService == null) {
            throw new IllegalStateException("Printer not found");
        }

        RepositoryPrint.printerPrint(myService, filePathAndName);
    }

    public static float getScaleImageForTargetPrinter(String pathBaseImg){
        BufferedImage baseImg = RepositoryPicture.getPicture(pathBaseImg);

        float widthScale = Settings.PPI_INCH_Screen* Settings.PRINTER_PAPER_WIDTH_MM / baseImg.getWidth();
        float heightScale = Settings.PPI_INCH_Screen* Settings.PRINTER_PAPER_WIDTH_MM / baseImg.getHeight();

        return widthScale > heightScale ? heightScale : widthScale;
    }
}
