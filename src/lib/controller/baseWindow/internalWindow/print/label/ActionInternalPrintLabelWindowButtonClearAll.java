package lib.controller.baseWindow.internalWindow.print.label;


import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalPrintLabelWindowButtonClearAll implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalTemplateEditor.clearAllGeneratedLabelPNGFiles();
     }
}
