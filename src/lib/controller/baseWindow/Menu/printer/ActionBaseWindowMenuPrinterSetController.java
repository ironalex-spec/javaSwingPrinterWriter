package lib.controller.baseWindow.Menu.printer;

import lib.repository.print.RepositoryPrinterOptions;
import lib.service.internal.print.template.ServiceInternalTemplate;
import lib.service.internal.print.label.ServiceInternalLabel;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintTemplateWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionBaseWindowMenuPrinterSetController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();

        PrinterAppBaseWindow printerAppBaseWindow = PrinterAppBaseWindow.getInstance();

        printerAppBaseWindow.setStatusPrinterName(menuItem.getText());

        RepositoryPrinterOptions.getInstance().makeDefaultPrinter(menuItem.getText());

        printerAppBaseWindow.setQueuePrinterEnable(true);
        printerAppBaseWindow.setPropertyPrinterEnable(true);
        printerAppBaseWindow.setPrintSettingsEnable(true);

        if (PrinterAppInternalPrintTemplateWindow.isExistInstance()) {
            PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(printerAppBaseWindow.getBaseWindow());

            boolean enableTextControl = ServiceInternalTemplate.getInstance().isEnableComponentTextControl();

            printerAppInternalPrintTemplateWindow.setPrintButtonEnable(enableTextControl && PrinterAppBaseWindow.getInstance().getSelectedPrinter() != null);
        }

        if (PrinterAppInternalPrintLabelWindow.isExistInstance()) {
            PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(printerAppBaseWindow.getBaseWindow());

            boolean enableTextControl = ServiceInternalLabel.getInstance().isEnableComponentTextControl();

            printerAppInternalPrintLabelWindow.setPrintMarginTextFieldsEnable(enableTextControl && PrinterAppBaseWindow.getInstance().getSelectedPrinter() != null);
            printerAppInternalPrintLabelWindow.setPrintButtonEnable(enableTextControl && PrinterAppBaseWindow.getInstance().getSelectedPrinter() != null);
        }
    }
}
