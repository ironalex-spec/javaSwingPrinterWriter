package lib.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalEditorTemplateWindowComboBoxFonts implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalTemplateEditor.refreshTemplateWithTextParameters();
    }
}
