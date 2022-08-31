package lib.controller.baseWindow.internalWindow.print.label.textLabel;

import lib.service.internal.print.label.ServiceInternalLabel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionInternalPrintLabelWindowSliders implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        ServiceInternalLabel.getInstance().refreshTemplateWithTextParameters();
    }
}
