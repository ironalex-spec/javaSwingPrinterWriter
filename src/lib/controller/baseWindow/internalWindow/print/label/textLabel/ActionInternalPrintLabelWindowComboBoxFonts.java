package lib.controller.baseWindow.internalWindow.print.label.textLabel;

import lib.service.internal.print.label.ServiceInternalLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionInternalPrintLabelWindowComboBoxFonts implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalLabel.getInstance().refreshTemplateWithTextParameters();
    }
}
