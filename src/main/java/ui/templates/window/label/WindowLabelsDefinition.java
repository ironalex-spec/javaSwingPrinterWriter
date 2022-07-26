package main.java.ui.templates.window.label;

import main.java.ui.templates.window.WindowElementsDefinition;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class WindowLabelsDefinition implements WindowElementsDefinition {
    private JFrame frame;
    private HashMap<String, JLabel> labels = new HashMap<String, JLabel>();

    public WindowLabelsDefinition(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void add(String key, String text, int x, int y, int width, int height){
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x,y,width, height);
        lbl.setVerticalTextPosition(JLabel.BOTTOM);
        lbl.setHorizontalTextPosition(JLabel.CENTER);

        frame.add(lbl);
        labels.put(key, lbl);
    }

    @Override
    public void addActionListener(String key, ActionListener actionListener) {
        ;
    }
}
