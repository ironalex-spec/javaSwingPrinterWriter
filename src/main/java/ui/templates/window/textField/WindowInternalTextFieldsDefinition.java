package main.java.ui.templates.window.textField;

import main.java.ui.templates.window.WindowInternalElementsDefinition;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class WindowInternalTextFieldsDefinition implements WindowInternalElementsDefinition {
    private JInternalFrame jInternalFrameFrame;
    private HashMap<String, JFormattedTextField> textFields = new HashMap<String, JFormattedTextField>();

    public WindowInternalTextFieldsDefinition(JInternalFrame jInternalFrameFrame){
        this.jInternalFrameFrame = jInternalFrameFrame;
    }

    @Override
    public void add(JComponent jDesktopPane, String keyTextField, String text, int x, int y, int width, int height){
        JFormattedTextField jTextField = new JFormattedTextField();

        jTextField.setValue(text);
        jTextField.setBounds(x,y,width, height);
        jTextField.setVisible(true);

        jDesktopPane.add(jTextField);

        textFields.put(keyTextField, jTextField);
    }


    @Override
    public void addActionListener(String keyTextField, ActionListener actionListener){
        JFormattedTextField jTextField = textFields.get(keyTextField);
        jTextField.addActionListener(actionListener);
    }

    public void addDocumentListener(String keyTextField, DocumentListener documentListener){
        JFormattedTextField jTextField = textFields.get(keyTextField);
        jTextField.getDocument().addDocumentListener(documentListener);
    }

    public void addFocusListener(String keyTextField, FocusAdapter focusAdapter){
        JFormattedTextField jTextField = textFields.get(keyTextField);
        jTextField.addFocusListener(focusAdapter);
    }

    public void addKeyListener(String keyTextField, KeyListener keyListener){
        JFormattedTextField jTextField = textFields.get(keyTextField);
        jTextField.addKeyListener(keyListener);
    }

    public void setTextFieldFormat(String keyTextField, byte format){
        JFormattedTextField jTextField = textFields.get(keyTextField);
        setTextFieldFormat(jTextField, format);
    }

    public String getText(String keyTextField){
        JFormattedTextField jTextField = textFields.get(keyTextField);
        return jTextField.getText();
    }

    public void setTextFieldData(String keyTextField, Object object){
        JFormattedTextField jTextField = textFields.get(keyTextField);

        jTextField.setValue(object);
    }

    public void setTextFieldEnable(String keyTextField, boolean enable){
        JFormattedTextField jTextField = textFields.get(keyTextField);
        jTextField.setEnabled(enable);
    }

    private void setTextFieldFormat(JFormattedTextField jTextField, byte format){
        DefaultFormatterFactory factory = null;
        switch (format) {
            case (1):
                // Define the number factory.
                NumberFormat nf = NumberFormat.getIntegerInstance();
                nf.setMinimumIntegerDigits(0);
                nf.setMaximumIntegerDigits(10);
                NumberFormatter nff = new NumberFormatter(nf);
                nff.setMinimum(0);
                nff.setMaximum(10);
                factory = new DefaultFormatterFactory(nff);

                jTextField.setFormatterFactory(factory);
                break;

            case (2):
                // Define the decimal factory.
                DecimalFormat df = new DecimalFormat();
                NumberFormatter dnff = new NumberFormatter(df);
                factory = new DefaultFormatterFactory(dnff);

                jTextField.setFormatterFactory(factory);
                break;
            default:
                break;
        }
    }
}
