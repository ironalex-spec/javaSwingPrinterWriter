package lib.controller.baseWindow.internalWindow.print.editor.TextLabel;

import lib.service.internal.editor.ServiceInternalEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorWindowComboBoxFonts implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalEditor.refreshTemplateWithTextParameters();
    }
}
