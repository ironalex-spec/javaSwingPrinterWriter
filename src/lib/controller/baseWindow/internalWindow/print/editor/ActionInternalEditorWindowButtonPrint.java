package lib.controller.baseWindow.internalWindow.print.editor;


import lib.service.print.ServicePrint;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalEditorWindowButtonPrint implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ServicePrint.printSelectedPngFile(PrinterAppBaseWindow.getInstance().getSelectedPrinter(), AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
    }
}
