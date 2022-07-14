package main.java.ui.templates.window.textField;

import main.java.ui.templates.window.ElementActionListener;

import javax.swing.*;
import java.util.HashMap;

public class WindowTextFieldsDefinition {
    private JFrame frame;
    private HashMap<String, JTextField> textFields = new HashMap<String, JTextField>();

    public WindowTextFieldsDefinition(JFrame frame){
        this.frame = frame;
    }

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


    public void addListener(String key, ElementActionListener elementActionListener){
        JTextField jTextField = textFields.get(key);
        jTextField.addActionListener(elementActionListener);
    }
}
