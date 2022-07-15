package main.java.ui.templates.window.button;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.WindowElementsDefinition;

import javax.swing.*;
import java.util.HashMap;

public class WindowButtonsDefinition implements WindowElementsDefinition {
    private JFrame frame;
    private HashMap<String, JButton> buttons = new HashMap<String, JButton>();

    public WindowButtonsDefinition(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void add(String key, String text, int x, int y, int width, int height){
        JButton btn = new JButton(text);

        btn.setBounds(x,y,width, height);
        btn.setVisible(true);

        frame.add(btn);
        buttons.put(key, btn);
    }

    @Override
    public void addActionListener(String key, ElementActionListener elementActionListener){
        JButton button = buttons.get(key);
        button.addActionListener(elementActionListener);
    }
}
