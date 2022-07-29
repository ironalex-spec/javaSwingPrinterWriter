package main.java.service.internal.templateEditor;

import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

public class ServiceInternalTemplateEditor {
    public static void updatePanelImage(String pathImage){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.updateImage(pathImage);
    }

    public static String generateFilenameByPrm(int width, int height, int fillet){
        String filename = "_" + width + "_" + height + "_" + fillet + "_.png";

        return filename;
    }

    public static Integer getWidthFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (filesData != null) {
            return Integer.parseInt(filesData[1]);
        } else {
            return null;
        }
    }

    public static Integer getHeightFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (filesData != null) {
            return Integer.parseInt(filesData[2]);
        } else {
            return null;
        }
    }

    public static Integer getFilletFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (filesData != null) {
            return Integer.parseInt(filesData[3]);
        } else {
            return null;
        }
    }

    private static String[] getDataFromFilename(String filename){
        String[] split = filename.split("_");

        if(split.length < 5){
            return null;
        } else {
            return split;
        }
    }
}
