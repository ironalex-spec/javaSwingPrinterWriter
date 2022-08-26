package lib.controller.baseWindow.internalWindow.print.label;


import lib.repository.file.ImageMagicAPI;
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
        ServiceInternalTemplateEditor.clearAllGeneratedLabelPNGFiles();

        ServiceInternalTemplateEditor.convertPCX_To_Png();
    }
}
