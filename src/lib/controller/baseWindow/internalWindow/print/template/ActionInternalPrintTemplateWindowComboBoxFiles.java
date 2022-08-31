package lib.controller.baseWindow.internalWindow.print.template;


import lib.service.Service;
import lib.service.internal.print.template.ServiceInternalTemplate;
import lib.app.Settings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintTemplateWindow;

import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalPrintTemplateWindowComboBoxFiles implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalPrintTemplateWindow.getFileTemplateChooseComboBox();

        if (selectedItem != null) {
            ServiceInternalTemplate.getInstance().setDefaultControlTextLabelTemplate();

            boolean enableTextControl = ServiceInternalTemplate.getInstance().isEnableComponentTextControl();

            ServiceInternalTemplate.getInstance().enableComponentsControl(enableTextControl);

            ServiceInternalTemplate.getInstance().updatePanelImage(Settings.TEMPLATE_FOLDER + selectedItem);

            printerAppInternalPrintTemplateWindow.setPrintButtonEnable(enableTextControl && PrinterAppBaseWindow.getInstance().getSelectedPrinter() != null);

            Service.copyFileAnotherDirectory(Settings.TEMPLATE_FOLDER + selectedItem, Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME);
        }
    }
}
