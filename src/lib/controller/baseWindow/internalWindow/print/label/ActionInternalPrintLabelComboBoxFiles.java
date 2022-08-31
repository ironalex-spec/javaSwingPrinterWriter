package lib.controller.baseWindow.internalWindow.print.label;


import lib.service.Service;
import lib.service.internal.print.label.ServiceInternalLabel;
import lib.app.Settings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalPrintLabelComboBoxFiles implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalPrintLabelWindow.getFileLabelChooseComboBox();

        if (selectedItem != null) {
            ServiceInternalLabel.getInstance().setDefaultControlTextLabelTemplate();

            boolean enableTextControl = ServiceInternalLabel.getInstance().isEnableComponentTextControl();

            ServiceInternalLabel.getInstance().enableComponentsControl(enableTextControl);

            ServiceInternalLabel.getInstance().updatePanelImage(Settings.LABEL_PCX_TO_PNG_FOLDER + selectedItem);

            printerAppInternalPrintLabelWindow.setPrintButtonEnable(enableTextControl && PrinterAppBaseWindow.getInstance().getSelectedPrinter() != null);

            Service.copyFileAnotherDirectory(Settings.LABEL_PCX_TO_PNG_FOLDER + selectedItem, Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME);
        }
    }
}
