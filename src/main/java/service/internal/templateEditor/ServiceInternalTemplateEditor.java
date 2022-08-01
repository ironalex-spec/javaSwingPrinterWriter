package main.java.service.internal.templateEditor;

import main.java.service.Service;
import main.java.service.print.ServicePrintTextAsImage;
import main.java.settings.AppSettings;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServiceInternalTemplateEditor {
    public static void updatePanelImage(String pathImage){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.updateImage(pathImage);
    }

    public static void enableComponentsControl(boolean enable){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.setSaveButtonEnable(enable);
        printerAppInternalTemplateEditorWindow.setClearButtonEnable(enable);
        printerAppInternalTemplateEditorWindow.setTextTextFieldEnable(enable);
        printerAppInternalTemplateEditorWindow.setXPosTextSliderEnable(enable);
        printerAppInternalTemplateEditorWindow.setYPosTextSliderEnable(enable);
        printerAppInternalTemplateEditorWindow.setFontComboBoxEnable(enable);
        printerAppInternalTemplateEditorWindow.setTextSizeTextFieldEnable(enable);
    }

    public static void refreshTemplateWithTextParameters(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        String textValue = printerAppInternalTemplateEditorWindow.getTextLabel();
        Object fontValue = printerAppInternalTemplateEditorWindow.getFontChooseComboBox();
        Integer height = Integer.parseInt(printerAppInternalTemplateEditorWindow.getHeight());
        Integer width = Integer.parseInt(printerAppInternalTemplateEditorWindow.getWidth());

        int xPosSlider = printerAppInternalTemplateEditorWindow.getXPosTextSliderValue();
        float xOffcet = sliderToFloatRecalc(xPosSlider);
        int xOffcet_mm = (int)(xOffcet * (float) width.intValue());

        int yPosSlider = printerAppInternalTemplateEditorWindow.getYPosTextSliderValue();
        float yOffcet = sliderToFloatRecalc(yPosSlider);
        int yOffcet_mm = (int)(yOffcet * (float) height.intValue());

        Object selectedFile = printerAppInternalTemplateEditorWindow.getFileTemplateChooseComboBox();

        ServicePrintTextAsImage.addImgText(AppSettings.templateFolder + selectedFile, AppSettings.templateTempFolder + "tempText.png", width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,10, (String) fontValue);

        ServiceInternalTemplateEditor.updatePanelImage(AppSettings.templateTempFolder + "tempText.png");
    }

    public static void setDefaultControlTextLabelTemplate(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.setTextLabel("");
    }


    public static String generateFilenameByPrm(int width, int height, int fillet){
        String filename = "_" + width + "_" + height + "_" + fillet + "_.png";

        return filename;
    }

    public static void clearAllGeneratedFiles(){
        String[] files = Service.listFilesForFolder(AppSettings.templateFolder);
        for(String s : files){
            if (isValidFilename(s)){
                try {
                    Files.delete(Paths.get(AppSettings.templateFolder + s));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void clearFileByFilename(String filename){
        if (isValidFilename(filename)){
            try {
                Files.delete(Paths.get(AppSettings.templateFolder + filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Integer getWidthFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[1]);
        } else {
            return null;
        }
    }

    public static Integer getHeightFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[2]);
        } else {
            return null;
        }
    }

    public static Integer getFilletFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[3]);
        } else {
            return null;
        }
    }

    private static String[] getDataFromFilename(String filename){
        String[] split = filename.split("_");

        if(isValidFilename(filename)){
            return split;
        } else {
            return null;
        }
    }

    private static boolean isValidFilename(String filename){
        String[] split = filename.split("_");

        if(split.length < 5){
            return false;
        } else {
            return true;
        }
    }

    private static float sliderToFloatRecalc(int slider){
        return  (float)(slider / (1.0 * AppSettings.MAX_SLIDER_VALUE));
    }
}
