package main.java.ui.templates.window.button;

import main.java.ui.templates.window.ElementActionListener;

import javax.swing.*;
import java.util.HashMap;

public class WindowButtonsDefinition {
    private JFrame frame;
    private HashMap<String, JButton> buttons = new HashMap<String, JButton>();

    public WindowButtonsDefinition(JFrame frame){
        this.frame = frame;
    }

    public void add(String key, String text, int x, int y, int width, int height){
        JButton btn = new JButton(text);
        btn.setBounds(x,y,width, height);
        btn.setVisible(true);

        frame.add(btn);
        buttons.put(key, btn);
    }


    public void addListener(String key, ElementActionListener elementActionListener){
        JButton button = buttons.get(key);
        button.addActionListener(elementActionListener);
    }
}
