package lib.controller.baseWindow.internalWindow.print.label;


import lib.service.Service;
import lib.service.internal.editor.ServiceInternalEditor;
import lib.service.internal.label.ServiceInternalLabelEditor;
import lib.settings.AppSettings;
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
            ServiceInternalLabelEditor.setDefaultControlTextLabelTemplate();

            boolean enableTextControl = ServiceInternalLabelEditor.isEnableComponentTextControl();

            ServiceInternalLabelEditor.enableComponentsControl(enableTextControl);

            ServiceInternalLabelEditor.updatePanelImage(AppSettings.LABEL_PCX_TO_PNG_FOLDER + selectedItem);

            printerAppInternalPrintLabelWindow.setPrintButtonEnable(enableTextControl && PrinterAppBaseWindow.getInstance().getSelectedPrinter() != null);

            Service.copyFileAnotherDirectory(AppSettings.LABEL_PCX_TO_PNG_FOLDER + selectedItem, AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
        }
    }
}
