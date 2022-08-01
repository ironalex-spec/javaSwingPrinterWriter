package lib.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionInternalEditorTemplateWindowSliders implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        ServiceInternalTemplateEditor.refreshTemplateWithTextParameters();
    }
}
