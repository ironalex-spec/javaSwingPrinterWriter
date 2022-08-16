import lib.repository.file.parcer.xml.AppConfigParser;
import lib.repository.print.RepositoryPrinterOptions;
import lib.ui.screens.PrinterAppBaseWindow;

public class Main {
    public static void main(String[] args){
        if(AppConfigParser.parse()) {
            PrinterAppBaseWindow.getInstance();
        }
    }
}
