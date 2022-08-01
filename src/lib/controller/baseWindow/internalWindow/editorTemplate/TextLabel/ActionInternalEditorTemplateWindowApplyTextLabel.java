package lib.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

import lib.service.Service;
import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowApplyTextLabel implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        Object selectedFile = printerAppInternalTemplateEditorWindow.getFileTemplateChooseComboBox();

        if (Service.renameFile(AppSettings.templateTempFolder + AppSettings.TEMPLATE_TEMP_DEFAULT_NAME, (String) selectedFile)) {
            Service.moveFileAnotherDirectory(AppSettings.templateTempFolder + (String) selectedFile, AppSettings.templateFolder + (String) selectedFile);

            ServiceInternalTemplateEditor.setDefaultControlTextLabelTemplate();
        }
    }
}
