package main.java.ui.templates.window.combobox;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class WindowInternalComboBoxDefinition {
    private JInternalFrame jInternalFrameFrame;

    private HashMap<String, JComboBox> comboBoxes = new HashMap<String, JComboBox>();

    public WindowInternalComboBoxDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }


    public void add(JComponent jDesktopPane, String keyComboBox, Object[] objects, int x, int y, int width, int height){
            if (objects == null) {
                objects = new Object[] {};
            }

            JComboBox  comboBox= new JComboBox(objects);

            comboBox.setBounds(x,y,width, height);

            jDesktopPane.add(comboBox);

            comboBoxes.put(keyComboBox, comboBox);
    }

    public void addComboBoxItem(String keyComboBox, Object object) {
        JComboBox comboBox = comboBoxes.get(keyComboBox);

        comboBox.addItem(object);
    }

    public Object getComboBoxSelectedItem(String keyComboBox) {
        JComboBox comboBox = comboBoxes.get(keyComboBox);

        return comboBox.getSelectedItem();
    }

    public void addActionListener(String key, ElementActionListener elementActionListener) {
        JComboBox comboBox = comboBoxes.get(key);
        comboBox.addActionListener(elementActionListener);
    }

    @Override
    protected void finalize() throws Throwable
    {
        /*System.out.println("Garbage collector in action! Deleted WindowInternalComboBoxDefinition object!");*/
    }
}
