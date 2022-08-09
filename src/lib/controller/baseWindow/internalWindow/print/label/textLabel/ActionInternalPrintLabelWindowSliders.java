package lib.controller.baseWindow.internalWindow.print.label.textLabel;

import lib.service.internal.editor.ServiceInternalEditor;
import lib.service.internal.label.ServiceInternalLabelEditor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionInternalPrintLabelWindowSliders implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        ServiceInternalLabelEditor.refreshTemplateWithTextParameters();
    }
}
