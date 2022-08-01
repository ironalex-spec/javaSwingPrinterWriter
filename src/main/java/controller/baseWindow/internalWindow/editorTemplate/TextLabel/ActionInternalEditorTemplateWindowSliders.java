package main.java.controller.baseWindow.internalWindow.editorTemplate.TextLabel;

import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionInternalEditorTemplateWindowSliders implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        ServiceInternalTemplateEditor.refreshTemplateWithTextParameters();
    }
}
