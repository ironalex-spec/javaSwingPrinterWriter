package lib.ui.templates.window.button;

import lib.ui.templates.window.WindowInternalElementsDefinition;

import javax.swing.*;
import java.awt.event.ActionListener;
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

    public void setButtonEnable(String key, boolean enable){
        JButton button = buttons.get(key);
        button.setEnabled(enable);
    }

    @Override
    public void addActionListener(String key, ActionListener actionListener) {
        JButton button = buttons.get(key);
        button.addActionListener(actionListener);
    }
}
