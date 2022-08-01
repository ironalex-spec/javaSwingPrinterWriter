package lib.controller.baseWindow.internalWindow.editorTemplate;

import lib.service.Service;
import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.templates.BaseWindow;

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

        boolean enableButton = Service.isAllDataIntNumeric(printerAppInternalTemplateEditorWindow.getTextFieldsValues());

        ServiceInternalTemplateEditor.enableComponentsControl(enableButton);
    }
}
