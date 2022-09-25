package lib.ui.screens;

import lib.controller.baseWindow.Menu.ActionBaseWindowMenuFilePrintLabelController;
import lib.controller.baseWindow.Menu.ActionBaseWindowMenuFilePrintTemplateController;
import lib.controller.baseWindow.Menu.ActionBaseWindowMenuFileEditorTemplateController;
import lib.controller.baseWindow.Menu.ActionBaseWindowMenuHelpAboutController;
import lib.controller.baseWindow.Menu.printer.ActionBaseWindowMenuPrinterProperty;
import lib.controller.baseWindow.Menu.printer.ActionBaseWindowMenuPrinterQueue;
import lib.controller.baseWindow.Menu.printer.ActionBaseWindowMenuPrinterSetController;
import lib.controller.baseWindow.Menu.printer.ActionBaseWindowMenuPrinterSettings;
import lib.repository.file.ImageMagicAPI;
import lib.repository.print.RepositoryPrinterOptions;
import lib.service.print.ServicePrint;
import lib.app.Settings;
import lib.ui.templates.BaseWindow;

import java.awt.*;

public class PrinterAppBaseWindow {

    private static PrinterAppBaseWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;

    private String[] printerNames;

    private String selectedPrinter;

    private PrinterAppBaseWindow(){
        callStaticMethods();

        printerAppBaseWindow = new BaseWindow("PrintLabel",0,0, Settings.baseWindowWidth, Settings.baseWindowHeight, 20);
        printerAppBaseWindow.updateDesktopPaneWithImage("src/resources/img/gif_printing.gif");

        String xPosition = Settings.baseWindowPosition.split("_")[0];
        String yPosition = Settings.baseWindowPosition.split("_")[1];

        int xLocation = getWindowLocationX(xPosition);
        int yLocation = getWindowLocationY(yPosition);

        printerAppBaseWindow.setLocation(xLocation, yLocation);

        printerAppBaseWindow.setAlwaysOnTop(true);

        printerAppBaseWindow.updateWindowIcon("src/resources/img/printerLogo.png");

        printerAppBaseWindow.addMenu("Menu_File", "File", 40, 20);
        printerAppBaseWindow.addMenu("Menu_Printer", "Printer", 60, 20);
        printerAppBaseWindow.addMenu("Menu_SelectPrinter", "Printer : ", 200, 20);
        printerAppBaseWindow.addMenuHorizontalGlue();
        printerAppBaseWindow.addMenu("Menu_Help", "Help", 40, 20);

        printerAppBaseWindow.setMenuEnable("Menu_SelectPrinter", false);

        printerAppBaseWindow.addSubMenu("Menu_File", "Menu_File_Item_Editor", "Editor", 100, 20);
        printerAppBaseWindow.addSubMenuItem("Menu_File","Menu_File_Item_Editor", "Menu_File_Item_Editor_Template", "Template");
        printerAppBaseWindow.addSubMenuItemActionListener("Menu_File", "Menu_File_Item_Editor","Menu_File_Item_Editor_Template", new ActionBaseWindowMenuFileEditorTemplateController());

        printerAppBaseWindow.addSubMenu("Menu_File", "Menu_File_Submenu_Print", "Print", 100, 20);
        printerAppBaseWindow.addSubMenuItem("Menu_File","Menu_File_Submenu_Print", "Menu_File_Submenu_Print_Item_Template", "Template");
        printerAppBaseWindow.addSubMenuItemActionListener("Menu_File", "Menu_File_Submenu_Print","Menu_File_Submenu_Print_Item_Template", new ActionBaseWindowMenuFilePrintTemplateController());

        if (Settings.typePCXConversion == 1){
            printerAppBaseWindow.addSubMenuItem("Menu_File", "Menu_File_Submenu_Print", "Menu_File_Submenu_Print_Item_Label", "Label");
            printerAppBaseWindow.addSubMenuItemActionListener("Menu_File", "Menu_File_Submenu_Print", "Menu_File_Submenu_Print_Item_Label", new ActionBaseWindowMenuFilePrintLabelController());
        } else if (ImageMagicAPI.getInstance().isAPIInstalled() ) {
            printerAppBaseWindow.addSubMenuItem("Menu_File", "Menu_File_Submenu_Print", "Menu_File_Submenu_Print_Item_Label", "Label");
            printerAppBaseWindow.addSubMenuItemActionListener("Menu_File", "Menu_File_Submenu_Print", "Menu_File_Submenu_Print_Item_Label", new ActionBaseWindowMenuFilePrintLabelController());
        }

        printerAppBaseWindow.addSubMenu("Menu_Printer", "Menu_Printer_Submenu_Set", "Set", 100, 20);
         if(printerNames != null) {
            for (int i = 0; i < printerNames.length; i++) {
                printerAppBaseWindow.addSubMenuItem("Menu_Printer", "Menu_Printer_Submenu_Set", "Menu_Printer_Submenu_Set_Item" + printerNames[i], printerNames[i]);
                printerAppBaseWindow.addSubMenuItemActionListener("Menu_Printer", "Menu_Printer_Submenu_Set", "Menu_Printer_Submenu_Set_Item" + printerNames[i], new ActionBaseWindowMenuPrinterSetController());
            }
        }

        if(RepositoryPrinterOptions.getInstance().getOptionExist()) {
            printerAppBaseWindow.addMenuItem("Menu_Printer", "Menu_Printer_Item_Queue", "Queue", 100, 20);
            printerAppBaseWindow.setMenuItemEnable("Menu_Printer", "Menu_Printer_Item_Queue", false);
            printerAppBaseWindow.addMenuItemActionListener("Menu_Printer", "Menu_Printer_Item_Queue", new ActionBaseWindowMenuPrinterQueue());

            printerAppBaseWindow.addMenuItem("Menu_Printer", "Menu_Printer_Item_PrintSettings", "Print settings", 100, 20);
            printerAppBaseWindow.setMenuItemEnable("Menu_Printer", "Menu_Printer_Item_PrintSettings", false);
            printerAppBaseWindow.addMenuItemActionListener("Menu_Printer", "Menu_Printer_Item_PrintSettings", new ActionBaseWindowMenuPrinterSettings());

            printerAppBaseWindow.addMenuItem("Menu_Printer", "Menu_Printer_Item_Property", "Property", 100, 20);
            printerAppBaseWindow.setMenuItemEnable("Menu_Printer", "Menu_Printer_Item_Property", false);
            printerAppBaseWindow.addMenuItemActionListener("Menu_Printer", "Menu_Printer_Item_Property", new ActionBaseWindowMenuPrinterProperty());
       }

        printerAppBaseWindow.addMenuItem("Menu_Help", "Menu_Help_Item_About", "About", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_Help", "Menu_Help_Item_About", new ActionBaseWindowMenuHelpAboutController());
    }

    public BaseWindow getBaseWindow(){
        return  printerAppBaseWindow;
    }

    public static PrinterAppBaseWindow getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new PrinterAppBaseWindow();
        }
        return single_instance;
    }

    public String getSelectedPrinter(){
        return selectedPrinter;
    }

    private void callStaticMethods(){
        if (Settings.typePCXConversion == 0) {
            ImageMagicAPI.getInstance();
        }

        printerNames = ServicePrint.getAvailiblePrinters();
    }

    /*public void getSelectedMenuPrinter(){
        return printerAppBaseWindow.getSubMenuItemName();
    }*/
    public void setStatusPrinterName(String printerName){
        selectedPrinter = printerName;
        printerAppBaseWindow.setMenuText("Menu_SelectPrinter", "Printer : " + printerName);
    }

    public void setPropertyPrinterEnable(boolean enable){
        printerAppBaseWindow.setMenuItemEnable("Menu_Printer", "Menu_Printer_Item_Property", enable);
    }

    public void setPrintSettingsEnable(boolean enable){
        printerAppBaseWindow.setMenuItemEnable("Menu_Printer", "Menu_Printer_Item_PrintSettings", enable);
    }

    public void setQueuePrinterEnable(boolean enable){
        printerAppBaseWindow.setMenuItemEnable("Menu_Printer", "Menu_Printer_Item_Queue", enable);
    }

    private int getWindowLocationX(String position){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int xLocation = 0;
        switch (position) {
            case "CENTER" :
                xLocation = dim.width/2-printerAppBaseWindow.getSize().width/2;
                break;
            case "LEFT" :
                xLocation = 0;
                break;
            case "RIGHT" :
                xLocation = dim.width-printerAppBaseWindow.getSize().width;
                break;
            default:
                xLocation = 0;
        }

        return xLocation;
    }

        private int getWindowLocationY(String position){
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            int yLocation = 0;
            switch (position) {
                case "CENTER" :
                    yLocation = dim.height/2-printerAppBaseWindow.getSize().height/2;
                    break;
                case "UP" :
                    yLocation = 0;
                    break;
                case "DOWN" :
                    yLocation = dim.height-printerAppBaseWindow.getSize().height;
                    break;
                default:
                    yLocation = 0;
            }

            return yLocation;
        }
}
