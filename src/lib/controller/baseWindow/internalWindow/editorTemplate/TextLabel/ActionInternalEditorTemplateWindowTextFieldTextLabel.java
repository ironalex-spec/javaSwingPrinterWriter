package lib.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;

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
        ServiceInternalTemplateEditor.refreshTemplateWithTextParameters();
    }
}