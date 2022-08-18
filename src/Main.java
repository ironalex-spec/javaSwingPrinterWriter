import lib.app.BuildVersion;
import lib.repository.file.parcer.xml.AppConfigParser;
import lib.service.license.ServiceAppLicense;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.information.PrinterAppInfoMessages;
import lib.ui.screens.information.PrinterAppInfoWindow;

public class Main {
    public static void main(String[] args){
        if (ServiceAppLicense.checkAppLicense()) {
            if (AppConfigParser.parse()) {
                PrinterAppBaseWindow.getInstance();
            }
        } else {
            PrinterAppInfoWindow.showErrorWindow("Wrong License", PrinterAppInfoMessages.MESSAGE_ERROR_LICENSE);

            System.exit(0);
        }
    }
}
