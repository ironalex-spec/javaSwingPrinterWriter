package lib.controller.baseWindow.internalWindow.print.label;


import lib.service.print.ServicePrint;
import lib.app.Settings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalPrintLabelWindowButtonPrint implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      /*  ServicePaintTransform.rotateClockwise90(AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME_ROTATE_90, AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
*/
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        try {
           double tempTop = Double.parseDouble(printerAppInternalPrintLabelWindow.getPrintMarginTopValue());
           Settings.PRINTER_PAPER_PADDING_TOP_MM = tempTop;
        } catch (NumberFormatException nfe){

        }

        try {
            double tempLeft = Double.parseDouble(printerAppInternalPrintLabelWindow.getPrintMarginLeftValue());
            Settings.PRINTER_PAPER_PADDING_LEFT_MM = tempLeft;
        } catch (NumberFormatException nfe){

        }




        ServicePrint.printSelectedPngFile(PrinterAppBaseWindow.getInstance().getSelectedPrinter(), Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME);
    }
}
