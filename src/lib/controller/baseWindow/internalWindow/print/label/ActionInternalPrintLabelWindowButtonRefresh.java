package lib.controller.baseWindow.internalWindow.print.label;


import lib.service.file.ImageMagicAPI;
import lib.service.file.ServiceFile;
import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalPrintLabelWindowButtonRefresh implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        ServiceInternalTemplateEditor.clearAllGeneratedLabelPNGFiles();

        ImageMagicAPI.getInstance().convertFolderPCX_To_PNG(AppSettings.LABEL_EXTERNAL_PCX_FOLDER, AppSettings.LABEL_PCX_TO_PNG_FOLDER);

        printerAppInternalPrintLabelWindow.updateComboBoxLabelItem();
    }
}
