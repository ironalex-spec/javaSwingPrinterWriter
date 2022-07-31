package main.java.ui.templates.window.slider;

import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class WindowInternalSlidersDefinition {
    private JInternalFrame jInternalFrameFrame;

    private HashMap<String, JSlider> sliders = new HashMap<String, JSlider>();

    public WindowInternalSlidersDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }


    public void add(JComponent jDesktopPane, String keyButton, int x, int y, int width, int height, int minVal, int maxVal, int initValue){
        JSlider slider = new JSlider(JSlider.HORIZONTAL,
                minVal, maxVal, initValue);

        //Turn on labels at major tick marks.
        /*slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);*/

        slider.setBounds(x,y,width, height);
        slider.setVisible(true);

        jDesktopPane.add(slider);

        sliders.put(keyButton, slider);
    }

    public void setSliderEnable(String key, boolean enable){
        JSlider slider = sliders.get(key);
        slider.setEnabled(enable);
    }

    public void addChangeListener(String key, ChangeListener changeListener) {
        JSlider slider = sliders.get(key);
        slider.addChangeListener(changeListener);
    }
}
