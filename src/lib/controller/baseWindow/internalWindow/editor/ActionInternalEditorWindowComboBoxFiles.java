package lib.controller.baseWindow.internalWindow.editor;


import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.internal.editor.ServiceInternalEditor;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalEditorWindow;

import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorWindowComboBoxFiles implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalEditorWindow printerAppInternalEditorWindow = PrinterAppInternalEditorWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalEditorWindow.getFileTemplateChooseComboBox();

        if (selectedItem != null) {
            ServiceInternalEditor.setDefaultControlTextLabelTemplate();

            boolean enableTextControl = ServiceInternalEditor.isEnableComponentTextControl();

            ServiceInternalEditor.enableComponentsControl(enableTextControl);

            ServiceInternalEditor.updatePanelImage(AppSettings.TEMPLATE_FOLDER + selectedItem);

            printerAppInternalEditorWindow.setPrintButtonEnable(enableTextControl && PrinterAppBaseWindow.getInstance().getSelectedPrinter() != null);

            Service.copyFileAnotherDirectory(AppSettings.TEMPLATE_FOLDER + selectedItem, AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
        }
    }
}
