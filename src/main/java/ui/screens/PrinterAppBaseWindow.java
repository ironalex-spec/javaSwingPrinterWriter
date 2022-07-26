package main.java.ui.screens;

import main.java.controller.MyFirstMenuI1tem1Controller;
import main.java.controller.baseWindow.Menu.ActionBaseWindowMenuFileNewController;
import main.java.controller.baseWindow.Menu.ActionBaseWindowMenuFileNewTemplateController;
import main.java.controller.baseWindow.Menu.ActionBaseWindowMenuPrinterSetController;
import main.java.ui.templates.BaseWindow;
import main.java.ui.templates.window.ElementActionListener;

import java.awt.*;

public class PrinterAppBaseWindow {

    private static PrinterAppBaseWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;

    public PrinterAppBaseWindow(){
        printerAppBaseWindow = new BaseWindow(0,0,800,500, 20);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        printerAppBaseWindow.setLocation(dim.width/2-printerAppBaseWindow.getSize().width/2, dim.height/2-printerAppBaseWindow.getSize().height/2);

        printerAppBaseWindow.updateWindowIcon("src/main/resources/img/printerLogo.png");

        printerAppBaseWindow.addMenu("Menu_File", "File", 100, 20);
        printerAppBaseWindow.addMenu("Menu_Printer", "Printer", 100, 20);
        printerAppBaseWindow.addMenuHorizontalGlue();
        printerAppBaseWindow.addMenu("Menu_Help", "Help", 100, 20);

        printerAppBaseWindow.addMenuItem("Menu_File", "Menu_File_Item_NewTemplate", "New Template", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_File", "Menu_File_Item_NewTemplate", new ElementActionListener(new ActionBaseWindowMenuFileNewTemplateController()));

        printerAppBaseWindow.addMenuItem("Menu_File", "Menu_File_Item_New", "New Editor", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_File", "Menu_File_Item_New", new ElementActionListener(new ActionBaseWindowMenuFileNewController()));


        printerAppBaseWindow.addMenuItem("Menu_Printer", "Menu_Printer_Item_Set", "Set", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_Printer", "Menu_Printer_Item_Set", new ElementActionListener(new ActionBaseWindowMenuPrinterSetController()));

        printerAppBaseWindow.addMenuItem("Menu_Help", "Menu_Help_Item_About", "About", 100, 20);
        printerAppBaseWindow.addMenuItemActionListener("Menu_Help", "Menu_Help_Item_About", new ElementActionListener(new MyFirstMenuI1tem1Controller()));
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
}
