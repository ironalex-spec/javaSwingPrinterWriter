package lib.controller.baseWindow.internalWindow.print.label.textLabel;

import lib.service.internal.editor.ServiceInternalEditor;
import lib.service.internal.label.ServiceInternalLabelEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionInternalPrintLabelWindowTextSize implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ServiceInternalLabelEditor.refreshTemplateWithTextParameters();
    }
}
