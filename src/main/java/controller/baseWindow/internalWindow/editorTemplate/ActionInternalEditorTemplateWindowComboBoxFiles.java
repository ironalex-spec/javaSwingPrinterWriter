package main.java.controller.baseWindow.internalWindow.editorTemplate;

import main.java.service.baseWindow.internalWindow.editorTemplate.ServiceInternalEditorTemplate;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowComboBoxFiles implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalEditorTemplate serviceInternalEditorTemplate = ServiceInternalEditorTemplate.getInstance();

        serviceInternalEditorTemplate.updatePanelImageComboBoxSelected();
    }
}
