package main.java.controller.baseWindow.Menu;

import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;
import main.java.ui.templates.window.ElementActionControler;

public class ActionBaseWindowMenuFileNewTemplateController implements ElementActionControler {
    @Override
    public void doMethod() {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);
    }
}
