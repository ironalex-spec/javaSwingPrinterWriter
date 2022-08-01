package lib.service.internal.templateEditor;

import lib.service.Service;
import lib.service.print.ServicePrintTextAsImage;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.templates.BaseWindow;

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

        Object textSize = printerAppInternalTemplateEditorWindow.getTextSizeLabel();
        if(Service.isDataIntNumeric(textSize)) {
            try {
                Integer height = Integer.parseInt(printerAppInternalTemplateEditorWindow.getHeight());
                Integer width = Integer.parseInt(printerAppInternalTemplateEditorWindow.getWidth());

                String textValue = printerAppInternalTemplateEditorWindow.getTextLabel();
                printerAppInternalTemplateEditorWindow.setApplyButtonEnable(!textValue.equals(""));

                Object fontValue = printerAppInternalTemplateEditorWindow.getFontChooseComboBox();

                int xPosSlider = printerAppInternalTemplateEditorWindow.getXPosTextSliderValue();
                float xOffcet = sliderToFloatRecalc(xPosSlider);
                int xOffcet_mm = (int) (xOffcet * (float) width.intValue() / 2);

                int yPosSlider = printerAppInternalTemplateEditorWindow.getYPosTextSliderValue();
                float yOffcet = sliderToFloatRecalc(yPosSlider);
                int yOffcet_mm = (int) (yOffcet * (float) height.intValue() / 2);

                Object selectedFile = printerAppInternalTemplateEditorWindow.getFileTemplateChooseComboBox();

                ServicePrintTextAsImage.addImgText(AppSettings.templateFolder + selectedFile,
                        AppSettings.templateTempFolder + AppSettings.TEMPLATE_TEMP_DEFAULT_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                ServiceInternalTemplateEditor.updatePanelImage(AppSettings.templateTempFolder + AppSettings.TEMPLATE_TEMP_DEFAULT_NAME);
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }

    public static void setDefaultControlTextLabelTemplate(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.setTextLabel("");
        printerAppInternalTemplateEditorWindow.setApplyButtonEnable(false);
        printerAppInternalTemplateEditorWindow.setXPosTextSliderValue(0);
        printerAppInternalTemplateEditorWindow.setYPosTextSliderValue(0);

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
