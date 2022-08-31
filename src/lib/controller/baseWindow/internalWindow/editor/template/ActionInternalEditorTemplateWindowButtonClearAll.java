package lib.controller.baseWindow.internalWindow.editor.template;


import lib.service.file.ServiceFile;
import lib.service.internal.editor.template.ServiceInternalTemplateEditor;
import lib.app.Settings;
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

        ServiceFile.clearAllGeneratedTemplateFiles();

        printerAppInternalTemplateEditorWindow.updateComboBoxFileItem();

        printerAppInternalTemplateEditorWindow.chooseComboBoxObject(Settings.TEMPLATE_DEFAULT_NAME);

        ServiceInternalTemplateEditor.getInstance().updatePanelImage(Settings.TEMPLATE_FOLDER + Settings.TEMPLATE_DEFAULT_NAME);
    }
}
