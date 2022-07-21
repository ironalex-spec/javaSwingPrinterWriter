package main.java.ui.templates.window.label;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.WindowElementsDefinition;
import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class WindowInternalLabelsDefinition implements WindowInternalElementsDefinition {
    private JInternalFrame jInternalFrameFrame;

    private HashMap<String, JLabel> labels = new HashMap<String, JLabel>();

    public WindowInternalLabelsDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }


    public void add(JComponent jDesktopPane, String keyLabel, String text, int x, int y, int width, int height){
            JLabel lbl = new JLabel(text);

            lbl.setBounds(x,y,width, height);
            lbl.setVerticalTextPosition(JLabel.BOTTOM);
            lbl.setHorizontalTextPosition(JLabel.CENTER);

            jDesktopPane.add(lbl);

            labels.put(keyLabel, lbl);
    }

    public void addLabelAsImage(JComponent jDesktopPane, String keyLabel, String filePath, int x, int y, int width, int height){
        try
        {
            BufferedImage img = ImageIO.read(new File(filePath));
            JLabel lbl = new JLabel(new ImageIcon(img));

            lbl.setBounds(x,y,width, height);
            lbl.setVerticalTextPosition(JLabel.BOTTOM);
            lbl.setHorizontalTextPosition(JLabel.CENTER);

            jDesktopPane.add(lbl);

            labels.put(keyLabel, lbl);
        }
        catch (IOException e) {}
    }

    public void updateLabelImage(String keyLabel, String filePath){
        try
        {
            BufferedImage img = ImageIO.read(new File(filePath));


            JLabel lbl = getLabel(keyLabel);

            if (lbl != null) {
                lbl.setIcon(new ImageIcon(img));
            }
        }
        catch (IOException e) {}
    }


    public void addActionListener(String key, ElementActionListener elementActionListener) {
        ;
    }

    private JLabel getLabel(String keyLabel){
        return labels.get(keyLabel);
    }
}
