package lib.controller.baseWindow.internalWindow.print.label;


import lib.service.internal.print.label.ServiceInternalLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalPrintLabelWindowButtonRefresh implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ServiceInternalLabel.getInstance().clearAllGeneratedLabelPNGFiles();

        ServiceInternalLabel.getInstance().convertPCX_To_Png();
    }
}
