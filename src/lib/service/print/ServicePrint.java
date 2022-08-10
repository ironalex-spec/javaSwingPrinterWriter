package lib.service.print;

import lib.repository.print.RepositoryPrint;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

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

        RepositoryPrint.printSelectedPngFile(myService, filePathAndName);
    }
}
