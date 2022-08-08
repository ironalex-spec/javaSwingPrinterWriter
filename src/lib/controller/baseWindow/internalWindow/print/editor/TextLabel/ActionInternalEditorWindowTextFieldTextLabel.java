package lib.controller.baseWindow.internalWindow.print.editor.TextLabel;

import lib.service.internal.editor.ServiceInternalEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionInternalEditorWindowTextFieldTextLabel implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ServiceInternalEditor.refreshTemplateWithTextParameters();
    }
}
