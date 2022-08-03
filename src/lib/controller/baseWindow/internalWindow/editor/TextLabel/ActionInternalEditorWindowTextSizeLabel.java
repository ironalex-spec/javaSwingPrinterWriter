package lib.controller.baseWindow.internalWindow.editor.TextLabel;

import lib.service.internal.editor.ServiceInternalEditor;
import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionInternalEditorWindowTextSizeLabel implements KeyListener {
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
