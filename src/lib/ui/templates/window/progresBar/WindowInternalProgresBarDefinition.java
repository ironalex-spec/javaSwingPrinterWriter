package lib.ui.templates.window.progresBar;

import lib.ui.templates.window.WindowInternalElementsDefinition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class WindowInternalProgresBarDefinition{
    private JInternalFrame jInternalFrameFrame;

    private HashMap<String, JProgressBar> progressBars = new HashMap<String, JProgressBar>();

    public WindowInternalProgresBarDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }


    public void add(JComponent jDesktopPane, String keyProgresBar, int maxValue, int x, int y, int width, int height){
            JProgressBar jProgressBar = new JProgressBar(0, maxValue);

            jProgressBar.setBounds(x,y,width, height);
            jProgressBar.setValue(0);
            jProgressBar.setStringPainted(true);


            jDesktopPane.add(jProgressBar);

            progressBars.put(keyProgresBar, jProgressBar);
    }

    public void setValue(String keyProgresBar, int Value){
        JProgressBar jProgressBar = progressBars.get(keyProgresBar);
        jProgressBar.setValue(Value);
    }

    @Override
    protected void finalize() throws Throwable
    {
        /*System.out.println("Garbage collector in action! Deleted InternalLabelDefinition object!");*/
    }
}
