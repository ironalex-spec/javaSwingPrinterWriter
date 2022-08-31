package lib.controller.baseWindow.internalWindow.print.label.textLabel;

import lib.service.internal.print.label.ServiceInternalLabel;

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
        ServiceInternalLabel.getInstance().refreshTemplateWithTextParameters();
    }
}
