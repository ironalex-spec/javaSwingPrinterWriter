package lib.repository.print;

import lib.repository.file.RepositoryConsole;
import lib.app.Settings;

public class RepositoryPrinterOptions {
    private static RepositoryPrinterOptions single_instance;
    private static boolean isOptionExist;

    RepositoryPrinterOptions(){
        //WINDOWS XP not support powershell
        //String[] commands = new String[]{"powershell", "-command","(" + Settings.WINDOWS_PRINTUI_DLL_FOLDER + "printui.dll" +").VersionInfo"};

        isOptionExist = true;//RepositoryConsole.getInstance().consoleExecuteOtherThread(commands);
    }

    public boolean printerPrintSettingsOpen(String printerName){
        if (isOptionExist) {
            return executePrintUI_DLL(printerName, "/e");
        } else {
            return false;
        }
    }

    public boolean printerPropertyOpen(String printerName){
        if (isOptionExist) {
            return executePrintUI_DLL(printerName, "/p");
        } else {
            return false;
        }
    }

    public boolean printerQueueOpen(String printerName){
        if (isOptionExist) {
            return executePrintUI_DLL(printerName, "/o");
        } else {
            return false;
        }
    }

    public boolean makeDefaultPrinter(String printerName){
        if (isOptionExist) {
            return executePrintUI_DLL(printerName, "/y");
        } else {
            return false;
        }
    }



    private boolean executePrintUI_DLL(String printerName, String attribute){
        boolean isExecute = false;

        String[] commands = new String[]{"rundll32 ", Settings.WINDOWS_PRINTUI_DLL_FOLDER + "printui.dll,PrintUIEntry", attribute,
                "/n", printerName};

        isExecute = RepositoryConsole.getInstance().consoleExecute(commands, false);

        return isExecute;
    }

    public static RepositoryPrinterOptions getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new RepositoryPrinterOptions();
        }
        return single_instance;
    }

    public boolean getOptionExist(){
        return isOptionExist;
    }
}
