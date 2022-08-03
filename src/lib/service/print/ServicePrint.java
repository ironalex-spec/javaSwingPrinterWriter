package lib.service.print;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

public class ServicePrint {
    public static String[] getAvailiblePrinter(){
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
}
