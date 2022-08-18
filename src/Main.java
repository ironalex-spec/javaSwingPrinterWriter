import lib.repository.file.parcer.xml.AppConfigParser;
import lib.repository.print.RepositoryPrinterOptions;
import lib.service.license.ServiceAppLicense;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.error.PrinterAppErrorWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        if (ServiceAppLicense.checkAppLicense()) {
            if (AppConfigParser.parse()) {
                PrinterAppBaseWindow.getInstance();
            }
        } else {
            String message = "Wrong license application error!!!\n\n"
                    + "\u2709 mail: zhelezonsany@gmail.com\n"
                    + "\u2706 phone: +375-44-750-88-09";

            PrinterAppErrorWindow.showWindow("Wrong License", message);

            System.exit(0);
        }
    }
}
