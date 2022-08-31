package lib.controller.baseWindow.internalWindow.editor.template.TextLabel;

import lib.service.Service;
import lib.service.internal.editor.template.ServiceInternalTemplateEditor;
import lib.app.Settings;
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

        if (Service.renameFile(Settings.TEMPLATE_TEMP_FOLDER + Settings.TEMPLATE_TEMP_DEFAULT_NAME, (String) selectedFile)) {
            Service.moveFileAnotherDirectory(Settings.TEMPLATE_TEMP_FOLDER + (String) selectedFile, Settings.TEMPLATE_FOLDER + (String) selectedFile);

            ServiceInternalTemplateEditor.getInstance().setDefaultControlTextLabelTemplate();
        }
    }
}
