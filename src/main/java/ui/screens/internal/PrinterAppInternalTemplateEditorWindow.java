package main.java.ui.screens.internal;

import main.java.ui.templates.BaseWindow;
import main.java.ui.templates.InternalWindow;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrinterAppInternalTemplateEditorWindow {
    private static PrinterAppInternalTemplateEditorWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;
    private InternalWindow internalWindow;

    public PrinterAppInternalTemplateEditorWindow(BaseWindow baseWindow){
        printerAppBaseWindow =  baseWindow;

        internalWindow = new InternalWindow(baseWindow, "Template editor", 0,0,baseWindow.getWidth() - 10,baseWindow.getHeight()-60);
        try {
            internalWindow.setMaximum(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        internalWindow.addButton("Pane_1","MyFirstLabel3","Label13", 10,30,100,30);


        internalWindow.addLabelAsImage("Pane_2","LabelImage_1",null, 0,10,100,100);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane_1", "Pane_2", 200);
        internalWindow.addScrolPaneOneComponent( "ScrolPane_1", "Pane_2", true);

        internalWindow.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                clearInternalWindowInstance();
            }
        });
    }

    public BaseWindow getBaseWindow(){
        return  printerAppBaseWindow;
    }

    public void updateImage(String imagePath){
        internalWindow.updateLabelImage("ScrolPane_1","MyFirstLabel",imagePath);
    }

    private void  clearInternalWindowInstance(){
        single_instance = null;
    }

    public static PrinterAppInternalTemplateEditorWindow getInstance(BaseWindow baseWindow)
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new PrinterAppInternalTemplateEditorWindow(baseWindow);
        }
        return single_instance;
    }
}
