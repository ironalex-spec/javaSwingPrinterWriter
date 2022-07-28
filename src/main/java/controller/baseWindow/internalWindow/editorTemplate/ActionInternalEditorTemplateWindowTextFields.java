package main.java.controller.baseWindow.internalWindow.editorTemplate;

import main.java.service.baseWindow.internalWindow.editorTemplate.ServiceInternalEditorTemplate;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ActionInternalEditorTemplateWindowTextFields extends FocusAdapter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalEditorTemplate serviceInternalEditorTemplate = ServiceInternalEditorTemplate.getInstance();

        serviceInternalEditorTemplate.setSaveButtonEnable();
    }

    @Override
    public void focusLost(FocusEvent e) {
        ServiceInternalEditorTemplate serviceInternalEditorTemplate = ServiceInternalEditorTemplate.getInstance();

        serviceInternalEditorTemplate.setSaveButtonEnable();
    }
}
