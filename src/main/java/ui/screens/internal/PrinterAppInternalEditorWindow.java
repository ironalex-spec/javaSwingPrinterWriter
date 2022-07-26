package main.java.ui.screens.internal;

import main.java.controller.internalEditorWindow.ActionInternalEditorWindowComboBoxTemplates;
import main.java.ui.templates.BaseWindow;
import main.java.ui.templates.InternalWindow;
import main.java.ui.templates.window.ElementActionListener;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrinterAppInternalEditorWindow {
    private static PrinterAppInternalEditorWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;

    public PrinterAppInternalEditorWindow(BaseWindow baseWindow){
        printerAppBaseWindow =  baseWindow;

        final InternalWindow internalWindow = new InternalWindow(baseWindow, "Editor", 0,0,baseWindow.getWidth() - 10,baseWindow.getHeight()-60);
        try {
            internalWindow.setMaximum(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        internalWindow.addButton("Pane_1","MyFirstLabel3","Label13", 10,30,100,30);
        internalWindow.addComboBox("Pane_1","MyFirstComboBox1", null, 10,70,100,20);

        internalWindow.addComboBoxItem("MyFirstComboBox1", "Hello");
        internalWindow.addComboBoxItem("MyFirstComboBox1", "Hello2");
        internalWindow.addComboBoxItem("MyFirstComboBox1", "Hello3");


        internalWindow.addComboBoxActionListener("MyFirstComboBox1", new ElementActionListener(new ActionInternalEditorWindowComboBoxTemplates()));


        internalWindow.addLabelAsImage("Pane_2","MyFirstLabel","smile.jpg", 0,10,100,100);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane_1", "Pane_2", 200);
        internalWindow.addScrolPaneOneComponent( "ScrolPane1", "Pane_2", true);


        internalWindow.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                clearInternalWindowInstance();
            }
        });
    }

    public BaseWindow getBaseWindow(){
        return  printerAppBaseWindow;
    }

    private void  clearInternalWindowInstance(){
        single_instance = null;
    }

    public static PrinterAppInternalEditorWindow getInstance(BaseWindow baseWindow)
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new PrinterAppInternalEditorWindow(baseWindow);
        }
        return single_instance;
    }
}
