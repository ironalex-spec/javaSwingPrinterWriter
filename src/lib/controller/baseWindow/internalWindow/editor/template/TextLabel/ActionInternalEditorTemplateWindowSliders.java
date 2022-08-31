package lib.controller.baseWindow.internalWindow.editor.template.TextLabel;

import lib.service.internal.editor.template.ServiceInternalTemplateEditor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionInternalEditorTemplateWindowSliders implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        ServiceInternalTemplateEditor.getInstance().refreshTemplateWithTextParameters();
    }
}
