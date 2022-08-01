package main.java.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

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

public class ActionInternalEditorTemplateWindowTextFieldTextLabel implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ServiceInternalTemplateEditor.refreshTemplateWithTextParameters();
    }
}
