package lib.controller.baseWindow.internalWindow.editor;


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
            String filename = (String) selectedItem;

            Integer width = ServiceFile.getWidthFromFilename(filename);
            Integer height = ServiceFile.getHeightFromFilename(filename);
            Integer fillet = ServiceFile.getFilletFromFilename(filename);

            if (width != null && height != null && fillet != null) {
                ;
            } else {
                ;
            }

            ServiceInternalEditor.setDefaultControlTextLabelTemplate();

            boolean enableButton = !((String) selectedItem).equals(AppSettings.TEMPLATE_DEFAULT_NAME);

            ServiceInternalEditor.enableComponentsControl(enableButton);

            ServiceInternalEditor.updatePanelImage(AppSettings.TEMPLATE_FOLDER + selectedItem);
        }
    }
}
