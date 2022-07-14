package main.java.ui.templates;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.button.WindowButtonsDefinition;
import main.java.ui.templates.window.label.WindowLabelsDefinition;
import main.java.ui.templates.window.textField.WindowTextFieldsDefinition;

import javax.swing.*;

public class Window {
    private JFrame frame;
    private WindowButtonsDefinition windowButtonDefinition;
    private WindowLabelsDefinition windowLabelDefinition;

    private WindowTextFieldsDefinition windowTextFieldsDefinition;

    public Window(int x, int y, int width, int height) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setLocation(x, y);
        frame.setLayout(null);
        frame.setVisible(true);

        windowButtonDefinition = new WindowButtonsDefinition(frame);
        windowLabelDefinition = new WindowLabelsDefinition(frame);
        windowTextFieldsDefinition = new WindowTextFieldsDefinition(frame);
    }

    public Window(){
        this(0,0,100,100);
    }

    public void addButton(String key, String text, int x, int y, int width, int height){
        windowButtonDefinition.add(key, text, x, y,width, height);
    }

    public void addButtonListener(String key, ElementActionListener elementActionListener){
        windowButtonDefinition.addListener(key, elementActionListener);
    }

    public void addLabel(String key, String text, int x, int y, int width, int height){
        windowLabelDefinition.add(key, text, x, y,width, height);
    }

    public void addTextField(String key, String text, int x, int y, int width, int height){
        windowTextFieldsDefinition.add(key, text, x, y,width, height);
    }

    public void addTextFieldListener(String key, ElementActionListener elementActionListener){
        windowTextFieldsDefinition.addListener(key, elementActionListener);
    }

    public void repaint(){
        frame.repaint();
    }
    public void setWindowVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}