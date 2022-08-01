package lib.controller.baseWindow.Menu;

import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalEditorWindow;
import lib.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionBaseWindowMenuFileNewController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalEditorWindow printerAppInternalEditorWindow = PrinterAppInternalEditorWindow.getInstance(baseWindow);
    }
}
