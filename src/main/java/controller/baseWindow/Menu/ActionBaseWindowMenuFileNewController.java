package main.java.controller.baseWindow.Menu;

import main.java.ui.screens.main.PrinterAppBaseWindow;
import main.java.ui.screens.main.internal.PrinterAppInternalWindow;
import main.java.ui.templates.BaseWindow;
import main.java.ui.templates.window.ElementActionControler;

public class ActionBaseWindowMenuFileNewController implements ElementActionControler {
    @Override
    public void doMethod() {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalWindow.getInstance(baseWindow);
    }
}
