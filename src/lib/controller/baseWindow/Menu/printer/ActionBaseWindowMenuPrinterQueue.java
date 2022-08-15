package lib.controller.baseWindow.Menu.printer;

import lib.repository.print.RepositoryPrinterOptions;
import lib.ui.screens.PrinterAppBaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionBaseWindowMenuPrinterQueue implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PrinterAppBaseWindow printerAppBaseWindow = PrinterAppBaseWindow.getInstance();

        RepositoryPrinterOptions.getInstance().printerQueueOpen(printerAppBaseWindow.getSelectedPrinter());
    }
}
