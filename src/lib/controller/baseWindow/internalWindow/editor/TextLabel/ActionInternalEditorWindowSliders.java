package lib.controller.baseWindow.internalWindow.editor.TextLabel;

import lib.service.internal.editor.ServiceInternalEditor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionInternalEditorWindowSliders implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        ServiceInternalEditor.refreshTemplateWithTextParameters();
    }
}
