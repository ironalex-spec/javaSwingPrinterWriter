package main.java.controller.internalWindow.editorTemplate;

import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowButtonSave implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalTemplateEditorWindow.getComboBoxSelectedItem("Pane_1_ComboBox_Files");

        printerAppInternalTemplateEditorWindow.updateImage("src/main/resources/editor/img/" + selectedItem);

    }
}
