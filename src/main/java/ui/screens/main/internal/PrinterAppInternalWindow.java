package main.java.ui.screens.main.internal;

import main.java.controller.MyFirstMenuI1tem1Controller;
import main.java.controller.baseWindow.Menu.ActionBaseWindowMenuFileNewController;
import main.java.controller.baseWindow.Menu.ActionBaseWindowMenuPrinterSetController;
import main.java.ui.screens.main.PrinterAppBaseWindow;
import main.java.ui.templates.BaseWindow;
import main.java.ui.templates.InternalWindow;
import main.java.ui.templates.window.ElementActionControler;
import main.java.ui.templates.window.ElementActionListener;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrinterAppInternalWindow {
    private static PrinterAppInternalWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;

    public PrinterAppInternalWindow(BaseWindow baseWindow){
        printerAppBaseWindow =  baseWindow;

        final InternalWindow internalWindow = new InternalWindow(baseWindow, "Editor", 0,0,baseWindow.getWidth() - 10,baseWindow.getHeight()-60);
        try {
            internalWindow.setMaximum(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        internalWindow.addButton("Pane2","MyFirstLabel3","Label13", 10,30,100,10);
        internalWindow.addComboBox("Pane2","MyFirstComboBox1", null, 10,50,100,20);

        internalWindow.addComboBoxItem("MyFirstComboBox1", "Hello");
        internalWindow.addComboBoxItem("MyFirstComboBox1", "Hello2");
        internalWindow.addComboBoxItem("MyFirstComboBox1", "Hello3");


        internalWindow.addComboBoxActionListener("MyFirstComboBox1", new ElementActionListener(new ElementActionControler() {
            @Override
            public void doMethod() {
                System.out.println(internalWindow.getComboBoxSelectedItem("MyFirstComboBox1"));
            }
        }));


        internalWindow.addLabelAsImage("Pane1","MyFirstLabel","smile.jpg", 0,10,100,100);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane2", "Pane1", 100);
        internalWindow.addScrolPaneOneComponent( "ScrolPane1", "Pane1", true);


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

    public static PrinterAppInternalWindow getInstance(BaseWindow baseWindow)
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new PrinterAppInternalWindow(baseWindow);
        }
        return single_instance;
    }
}
