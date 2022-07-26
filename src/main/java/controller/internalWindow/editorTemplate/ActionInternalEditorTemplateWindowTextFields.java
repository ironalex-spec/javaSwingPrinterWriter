package main.java.controller.internalWindow.editorTemplate;

import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ActionInternalEditorTemplateWindowTextFields extends FocusAdapter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        System.out.println(printerAppInternalTemplateEditorWindow.isTextFieldDataValid());

    }

    @Override
    public void focusLost(FocusEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.activateSaveButton(printerAppInternalTemplateEditorWindow.isTextFieldDataValid());
        System.out.println(printerAppInternalTemplateEditorWindow.isTextFieldDataValid());
    }
}
