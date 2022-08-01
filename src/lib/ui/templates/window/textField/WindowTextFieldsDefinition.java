package lib.ui.templates.window.textField;

import lib.ui.templates.window.WindowElementsDefinition;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class WindowTextFieldsDefinition implements WindowElementsDefinition {
    private JFrame frame;
    private HashMap<String, JTextField> textFields = new HashMap<String, JTextField>();

    public WindowTextFieldsDefinition(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void add(String key, String text, int x, int y, int width, int height){
        JTextField jTextField = new JTextField(text, 25);
        jTextField.setBounds(x,y,width, height);
        jTextField.setVisible(true);

        jTextField.setToolTipText("Hello");

        frame.add(jTextField);

        textFields.put(key, jTextField);
    }

    public void addToolTipText(String key, String text){
        textFields.get(key).setToolTipText(text);
    }

    @Override
    public void addActionListener(String key, ActionListener actionListener){
        JTextField jTextField = textFields.get(key);
        jTextField.addActionListener(actionListener);
    }
}
