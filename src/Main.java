import lib.service.file.ImageMagicAPI;
import lib.service.print.ServicePrint;
import lib.ui.screens.PrinterAppBaseWindow;

public class Main {
    private static int internalWindowNumber = 0;

    public static int getInternalWindowNumber(){
        return internalWindowNumber;
    }

    public static void setInternalWindowNumber(int internalWindowNumber){
        Main.internalWindowNumber = internalWindowNumber;
    }

    public static void main(String[] args){
        ImageMagicAPI.getInstance();
        PrinterAppBaseWindow.getInstance();
    }
}
