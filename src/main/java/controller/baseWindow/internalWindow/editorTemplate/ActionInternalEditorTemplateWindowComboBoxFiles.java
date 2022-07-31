package main.java.controller.baseWindow.internalWindow.editorTemplate;

import main.java.service.Service;
import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;
import main.java.settings.AppSettings;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowComboBoxFiles implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalTemplateEditorWindow.getComboBoxSelectedItem("Pane_1_ComboBox_Files");

        if (selectedItem != null) {
            String filename = (String) selectedItem;

            Integer width = ServiceInternalTemplateEditor.getWidthFromFilename(filename);
            Integer height = ServiceInternalTemplateEditor.getHeightFromFilename(filename);
            Integer fillet = ServiceInternalTemplateEditor.getFilletFromFilename(filename);

            if (width != null && height != null && fillet != null) {
                printerAppInternalTemplateEditorWindow.setWidth(width.toString());
                printerAppInternalTemplateEditorWindow.setHeight(height.toString());
                printerAppInternalTemplateEditorWindow.setFillet(fillet.toString());
            } else {
                printerAppInternalTemplateEditorWindow.setWidth("");
                printerAppInternalTemplateEditorWindow.setHeight("");
                printerAppInternalTemplateEditorWindow.setFillet("");
            }

            boolean enableButton = Service.isAllDataNumericInteger(printerAppInternalTemplateEditorWindow.getTextFieldsValues());

            printerAppInternalTemplateEditorWindow.setSaveButtonEnable(enableButton);
            printerAppInternalTemplateEditorWindow.setClearButtonEnable(enableButton);

            ServiceInternalTemplateEditor.updatePanelImage(AppSettings.templateFolder + selectedItem);
        }
    }
}
