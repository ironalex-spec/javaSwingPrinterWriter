package lib.controller.baseWindow.Menu;

import lib.ui.screens.information.PrinterAppInfoMessages;
import lib.ui.screens.information.PrinterAppInfoWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionBaseWindowMenuHelpAboutController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        PrinterAppInfoWindow.showAppInfoWindow("Application about", PrinterAppInfoMessages.MESSAGE_APPLICATION_ABOUT);
    }
}
