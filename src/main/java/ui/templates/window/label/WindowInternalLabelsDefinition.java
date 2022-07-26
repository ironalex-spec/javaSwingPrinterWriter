package main.java.ui.templates.window.label;

import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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
            BufferedImage img = null;

            if (filePath != null) {
                img = ImageIO.read(new File(filePath));
            } else {
                img = ImageIO.read(new File("src/main/resources/editor/img/default.jpg"));
            }

            ImageIcon imgIco = new ImageIcon(img);
            JLabel lbl = new JLabel(imgIco);

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
            ImageIcon imageIcon = new ImageIcon(img);

            if (lbl != null) {
                lbl.setIcon(imageIcon);
            }

            imageIcon = null;
            img = null;
            System.gc();
        }
        catch (IOException e) {}
    }

    static void clearImage(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, img.getWidth(), img.getHeight());
        g2.dispose();
    }


    public void addActionListener(String key, ActionListener actionListener) {
        ;
    }

    private JLabel getLabel(String keyLabel){
        return labels.get(keyLabel);
    }

    @Override
    protected void finalize() throws Throwable
    {
        /*System.out.println("Garbage collector in action! Deleted InternalLabelDefinition object!");*/
    }
}
