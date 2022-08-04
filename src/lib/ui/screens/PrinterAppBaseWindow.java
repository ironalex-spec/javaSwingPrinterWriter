package lib.ui.screens;

import lib.controller.baseWindow.Menu.ActionBaseWindowMenuFileNewController;
import lib.controller.MyFirstMenuI1tem1Controller;
import lib.controller.baseWindow.Menu.ActionBaseWindowMenuFileNewTemplateController;
import lib.controller.baseWindow.Menu.ActionBaseWindowMenuPrinterSetController;
import lib.service.file.ImageMagicAPI;
import lib.service.print.ServicePrint;
import lib.ui.templates.BaseWindow;

import java.awt.*;

public class PrinterAppBaseWindow {

    private static PrinterAppBaseWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;

    private String[] printerNames;

    private String selectedPrinter;

    private PrinterAppBaseWindow(){
        callStaticMethods();

        printerAppBaseWindow = new BaseWindow(0,0,900,600, 20);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        printerAppBaseWindow.setLocation(dim.width/2-printerAppBaseWindow.getSize().width/2, dim.height/2-printerAppBaseWindow.getSize().height/2);

        printerAppBaseWindow.updateWindowIcon("src/resources/img/printerLogo.png");

        printerAppBaseWindow.addMenu("Menu_File", "File", 100, 20);
        printerAppBaseWindow.addMenu("Menu_Printer", "Printer", 100, 20);
        printerAppBaseWindow.addMenu("Menu_SelectPrinter", "Printer : ", 500, 20);
        printerAppBaseWindow.addMenuHorizontalGlue();
        printerAppBaseWindow.addMenu("Menu_Help", "Help", 100, 20);

        printerAppBaseWindow.setMenuEnable("Menu_SelectPrinter", false);
        printerAppBaseWindow.addMenuItem("Menu_File", "Menu_File_Item_NewTemplate", "Template editor", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_File", "Menu_File_Item_NewTemplate", new ActionBaseWindowMenuFileNewTemplateController());

        printerAppBaseWindow.addMenuItem("Menu_File", "Menu_File_Item_New", "Print template", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_File", "Menu_File_Item_New", new ActionBaseWindowMenuFileNewController());

        if(ImageMagicAPI.getInstance().isAPIInstalled()) {
            printerAppBaseWindow.addMenuItem("Menu_File", "Menu_File_Item_FileLabel", "Print from file", 100, 20);
            /*  printerAppBaseWindow.addMenuItemActionListener("Menu_File", "Menu_File_Item_FileLabel", new ActionBaseWindowMenuFileNewController());
             */
        }

        printerAppBaseWindow.addSubMenu("Menu_Printer", "Menu_Printer_Submenu_Set", "Set", 100, 20);
         if(printerNames != null) {
            for (int i = 0; i < printerNames.length; i++) {
                printerAppBaseWindow.addSubMenuItem("Menu_Printer", "Menu_Printer_Submenu_Set", "Menu_Printer_Submenu_Set_Item" + printerNames[i], printerNames[i]);
                printerAppBaseWindow.addSubMenuItemActionListener("Menu_Printer", "Menu_Printer_Submenu_Set", "Menu_Printer_Submenu_Set_Item" + printerNames[i], new ActionBaseWindowMenuPrinterSetController());
            }
        }

        printerAppBaseWindow.addMenuItem("Menu_Help", "Menu_Help_Item_About", "About", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_Help", "Menu_Help_Item_About", new MyFirstMenuI1tem1Controller());
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
        printerNames = ServicePrint.getAvailiblePrinters();
    }

    /*public void getSelectedMenuPrinter(){
        return printerAppBaseWindow.getSubMenuItemName();
    }*/
    public void setStatusPrinterName(String printerName){
        selectedPrinter = printerName;
        printerAppBaseWindow.setMenuText("Menu_SelectPrinter", "Printer : " + printerName);
    }
}
