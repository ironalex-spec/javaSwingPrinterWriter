package lib.service.print;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ServicePrint {
    public static String[] getAvailiblePrinters(){
        /*DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
        patts.add(Sides.DUPLEX);*/
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);

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
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
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
    }
}
