package main.java.ui.templates.window.textField;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.swing.*;
import java.util.HashMap;

public class WindowInternalTextFieldsDefinition implements WindowInternalElementsDefinition {
    private JInternalFrame jInternalFrameFrame;
    private HashMap<String, JTextField> textFields = new HashMap<String, JTextField>();

    public WindowInternalTextFieldsDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }

    @Override
    public void add(JComponent jDesktopPane, String keyTextField, String text, int x, int y, int width, int height){
        JTextField jTextField = new JTextField(text, 25);
        jTextField.setBounds(x,y,width, height);
        jTextField.setVisible(true);

        jDesktopPane.add(jTextField);

        textFields.put(keyTextField, jTextField);
    }


    @Override
    public void addActionListener(String keyTextField, ElementActionListener elementActionListener){
        JTextField jTextField = textFields.get(keyTextField);
        jTextField.addActionListener(elementActionListener);
    }
}
