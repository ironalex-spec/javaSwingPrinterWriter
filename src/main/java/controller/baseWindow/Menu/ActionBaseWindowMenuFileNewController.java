package main.java.controller.baseWindow.Menu;

import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionBaseWindowMenuFileNewController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalEditorWindow.getInstance(baseWindow);
    }
}
