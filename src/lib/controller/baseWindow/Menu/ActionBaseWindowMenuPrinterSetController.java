package lib.controller.baseWindow.Menu;

import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalEditorWindow;
import lib.ui.templates.BaseWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionBaseWindowMenuPrinterSetController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();

        PrinterAppBaseWindow printerAppBaseWindow = PrinterAppBaseWindow.getInstance();

        printerAppBaseWindow.setStatusPrinterName(menuItem.getText());
    }
}
