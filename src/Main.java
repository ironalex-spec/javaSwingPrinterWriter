import lib.repository.file.parcer.xml.AppConfigParser;
import lib.ui.screens.PrinterAppBaseWindow;

public class Main {
    public static void main(String[] args){
        if(AppConfigParser.parse()) {
            PrinterAppBaseWindow.getInstance();
        }
    }
}
