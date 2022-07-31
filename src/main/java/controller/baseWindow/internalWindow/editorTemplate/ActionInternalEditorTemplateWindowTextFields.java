package main.java.controller.baseWindow.internalWindow.editorTemplate;

import main.java.service.Service;
import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.*;

public class ActionInternalEditorTemplateWindowTextFields implements ActionListener, KeyListener {

    @Override
    public void actionPerformed(ActionEvent e) {
  /*      ServiceInternalEditorTemplate serviceInternalEditorTemplate = ServiceInternalEditorTemplate.getInstance();
*/
        /*serviceInternalEditorTemplate.setSaveButtonEnable();*/
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        boolean enableButton = Service.isAllDataNumericInteger(printerAppInternalTemplateEditorWindow.getTextFieldsValues());

        ServiceInternalTemplateEditor.enableComponentsControl(enableButton);
    }
}
