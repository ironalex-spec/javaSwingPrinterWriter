package lib.controller.baseWindow.internalWindow.editor.template.TextLabel;

import lib.service.internal.editor.template.ServiceInternalTemplateEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionInternalEditorTemplateWindowTextFieldTextLabel implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ServiceInternalTemplateEditor.getInstance().refreshTemplateWithTextParameters();
    }
}
