package main.java.ui.templates.window.label;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.WindowElementsDefinition;
import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.swing.*;
import java.util.HashMap;

public class WindowInternalLabelsDefinition implements WindowInternalElementsDefinition {
    private JInternalFrame jInternalFrameFrame;
    private JDesktopPane jDesktopPane = new JDesktopPane();

    private HashMap<String, JLabel> labels = new HashMap<String, JLabel>();

    public WindowInternalLabelsDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }


    public void add(JDesktopPane jDesktopPane, String keyLabel, String text, int x, int y, int width, int height){


        JLabel lbl = new JLabel(text);
        lbl.setBounds(x,y,width, height);
        lbl.setVerticalTextPosition(JLabel.BOTTOM);
        lbl.setHorizontalTextPosition(JLabel.CENTER);

        jDesktopPane.add(lbl);

        labels.put(keyLabel, lbl);
    }


    public void addActionListener(String key, ElementActionListener elementActionListener) {
        ;
    }
}
