package lib.controller.baseWindow.internalWindow.print.template;


import lib.service.print.ServicePrint;
import lib.app.Settings;
import lib.ui.screens.PrinterAppBaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalPrintTemplateWindowButtonPrint implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ServicePrint.printSelectedPngFile(PrinterAppBaseWindow.getInstance().getSelectedPrinter(), Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME);
    }
}
