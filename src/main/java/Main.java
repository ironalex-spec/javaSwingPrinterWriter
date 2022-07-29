package main.java;

import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;
import main.java.ui.screens.PrinterAppBaseWindow;

public class Main {
    private static int internalWindowNumber = 0;

    public static int getInternalWindowNumber(){
        return internalWindowNumber;
    }

    public static void setInternalWindowNumber(int internalWindowNumber){
        Main.internalWindowNumber = internalWindowNumber;
    }

    public static void main(String[] args){
        PrinterAppBaseWindow.getInstance();
        System.out.println(ServiceInternalTemplateEditor.getWidthFromFilename("_32_22_11_.png"));
        System.out.println(ServiceInternalTemplateEditor.getHeightFromFilename("_32_22_11_.png"));
        System.out.println(ServiceInternalTemplateEditor.getFilletFromFilename("_32_22_11_.png"));
    }
}
