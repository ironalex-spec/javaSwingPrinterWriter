package lib.controller.baseWindow.internalWindow.print.template.textTemplate;

import lib.service.internal.print.template.ServiceInternalTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalPrintTemplateWindowComboBoxFonts implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalTemplate.getInstance().refreshTemplateWithTextParameters();
    }
}
