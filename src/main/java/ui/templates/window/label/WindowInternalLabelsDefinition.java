package main.java.ui.templates.window.label;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.WindowElementsDefinition;

import javax.swing.*;
import java.util.HashMap;

public class WindowInternalLabelsDefinition implements WindowElementsDefinition {
    private JInternalFrame jInternalFrameFrame;
    private HashMap<String, JLabel> labels = new HashMap<String, JLabel>();

    public WindowInternalLabelsDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }

    @Override
    public void add(String key, String text, int x, int y, int width, int height){
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x,y,width, height);
        lbl.setVerticalTextPosition(JLabel.BOTTOM);
        lbl.setHorizontalTextPosition(JLabel.CENTER);

        jInternalFrameFrame.add(lbl);
        labels.put(key, lbl);
    }

    @Override
    public void addActionListener(String key, ElementActionListener elementActionListener) {
        ;
    }
}
