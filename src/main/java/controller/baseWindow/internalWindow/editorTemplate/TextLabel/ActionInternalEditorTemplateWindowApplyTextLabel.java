package main.java.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

import main.java.service.Service;
import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;
import main.java.service.print.ServicePrintTextAsImage;
import main.java.settings.AppSettings;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
