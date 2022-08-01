package lib.controller.baseWindow.internalWindow.editorTemplate;

import lib.service.Service;
import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowComboBoxFiles implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalTemplateEditorWindow.getFileTemplateChooseComboBox();

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

            ServiceInternalTemplateEditor.setDefaultControlTextLabelTemplate();

            boolean enableButton = Service.isAllDataIntNumeric(printerAppInternalTemplateEditorWindow.getTextFieldsValues());

            ServiceInternalTemplateEditor.enableComponentsControl(enableButton);

            ServiceInternalTemplateEditor.updatePanelImage(AppSettings.templateFolder + selectedItem);
        }
    }
}
