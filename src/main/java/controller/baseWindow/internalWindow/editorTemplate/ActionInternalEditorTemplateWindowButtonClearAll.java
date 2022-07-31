package main.java.controller.baseWindow.internalWindow.editorTemplate;


import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;
import main.java.service.print.ServicePrintRoundedRectangleAsImage;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalEditorTemplateWindowButtonClearAll implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);


        ServiceInternalTemplateEditor.clearAllGeneratedFiles();

        printerAppInternalTemplateEditorWindow.updateComboBoxFileItem();

        printerAppInternalTemplateEditorWindow.chooseComboBoxObject("default.jpg");
    }
}
