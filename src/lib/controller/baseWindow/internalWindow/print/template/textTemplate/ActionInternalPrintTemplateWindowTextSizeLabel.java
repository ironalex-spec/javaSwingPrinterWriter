package lib.controller.baseWindow.internalWindow.print.template.textTemplate;

import lib.service.internal.print.template.ServiceInternalTemplate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionInternalPrintTemplateWindowTextSizeLabel implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ServiceInternalTemplate.getInstance().refreshTemplateWithTextParameters();
    }
}
