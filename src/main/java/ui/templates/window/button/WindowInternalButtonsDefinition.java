package main.java.ui.templates.window.button;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.swing.*;
import java.util.HashMap;

public class WindowInternalButtonsDefinition implements WindowInternalElementsDefinition {
    private JInternalFrame jInternalFrameFrame;

    private HashMap<String, JButton> buttons = new HashMap<String, JButton>();

    public WindowInternalButtonsDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }


    public void add(JComponent jDesktopPane, String keyButton, String text, int x, int y, int width, int height){
        JButton btn = new JButton(text);

        btn.setBounds(x,y,width, height);
        btn.setVisible(true);

        jDesktopPane.add(btn);

        buttons.put(keyButton, btn);
    }

    @Override
    public void addActionListener(String key, ElementActionListener elementActionListener) {
        JButton button = buttons.get(key);
        button.addActionListener(elementActionListener);
    }
}
