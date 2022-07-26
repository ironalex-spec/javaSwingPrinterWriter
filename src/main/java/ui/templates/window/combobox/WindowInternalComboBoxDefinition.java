package main.java.ui.templates.window.combobox;

import javax.swing.*;
import java.awt.event.ActionListener;
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

    public void addActionListener(String key, ActionListener actionListener) {
        JComboBox comboBox = comboBoxes.get(key);
        comboBox.addActionListener(actionListener);
    }

    @Override
    protected void finalize() throws Throwable
    {
        /*System.out.println("Garbage collector in action! Deleted WindowInternalComboBoxDefinition object!");*/
    }
}
