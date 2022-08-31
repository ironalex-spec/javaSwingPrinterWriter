package lib.controller.baseWindow.internalWindow.editor.template.TextLabel;

import lib.service.internal.editor.template.ServiceInternalTemplateEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowComboBoxFonts implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalTemplateEditor.getInstance().refreshTemplateWithTextParameters();
    }
}
