package lib.controller.baseWindow.internalWindow.print.template.textTemplate;

import lib.service.internal.print.template.ServiceInternalTemplate;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionInternalPrintTemplateWindowSliders implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        ServiceInternalTemplate.getInstance().refreshTemplateWithTextParameters();
    }
}
