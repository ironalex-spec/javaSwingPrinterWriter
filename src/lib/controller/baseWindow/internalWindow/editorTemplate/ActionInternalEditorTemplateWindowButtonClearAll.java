package lib.controller.baseWindow.internalWindow.editorTemplate;


import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalEditorTemplateWindowButtonClearAll implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        ServiceInternalTemplateEditor.clearAllGeneratedFiles();

        printerAppInternalTemplateEditorWindow.updateComboBoxFileItem();

        printerAppInternalTemplateEditorWindow.chooseComboBoxObject(AppSettings.TEMPLATE_DEFAULT_NAME);

        ServiceInternalTemplateEditor.updatePanelImage(AppSettings.templateFolder + AppSettings.TEMPLATE_DEFAULT_NAME);
    }
}
