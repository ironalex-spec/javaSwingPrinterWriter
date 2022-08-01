package main.java.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

import main.java.service.Service;
import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;
import main.java.settings.AppSettings;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowComboBoxFonts implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalTemplateEditor.refreshTemplateWithTextParameters();
    }
}
