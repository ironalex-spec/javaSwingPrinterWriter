package lib.service.internal.editor;

import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.paint.ServicePaintTextAsImage;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalEditorWindow;
import lib.ui.templates.BaseWindow;

public class ServiceInternalEditor {
    public static void updatePanelImage(String pathImage){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalEditorWindow printerAppInternalEditorWindow = PrinterAppInternalEditorWindow.getInstance(baseWindow);

        printerAppInternalEditorWindow.updateImage(pathImage);
    }

    public static void enableComponentsControl(boolean enable){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalEditorWindow printerAppInternalEditorWindow = PrinterAppInternalEditorWindow.getInstance(baseWindow);

        printerAppInternalEditorWindow.setTextTextFieldEnable(enable);
        printerAppInternalEditorWindow.setXPosTextSliderEnable(enable);
        printerAppInternalEditorWindow.setYPosTextSliderEnable(enable);
        printerAppInternalEditorWindow.setFontComboBoxEnable(enable);
        printerAppInternalEditorWindow.setTextSizeTextFieldEnable(enable);
    }

    public static void refreshTemplateWithTextParameters(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalEditorWindow printerAppInternalEditorWindow = PrinterAppInternalEditorWindow.getInstance(baseWindow);

        Object textSize = printerAppInternalEditorWindow.getTextSizeLabel();
        Object filenameChoose = printerAppInternalEditorWindow.getFileTemplateChooseComboBox();

        if(Service.isDataIntNumeric(textSize)) {
            try {
                Integer height = ServiceFile.getHeightFromFilename((String) filenameChoose);
                Integer width = ServiceFile.getWidthFromFilename((String) filenameChoose);

                String textValue = printerAppInternalEditorWindow.getTextLabel();
                printerAppInternalEditorWindow.setPrintButtonEnable(!textValue.equals(""));

                Object fontValue = printerAppInternalEditorWindow.getFontChooseComboBox();

                int xPosSlider = printerAppInternalEditorWindow.getXPosTextSliderValue();
                float xOffcet = Service.sliderToFloatRecalc(xPosSlider);
                int xOffcet_mm = (int) (xOffcet * (float) width.intValue() / 2);

                int yPosSlider = printerAppInternalEditorWindow.getYPosTextSliderValue();
                float yOffcet = Service.sliderToFloatRecalc(yPosSlider);
                int yOffcet_mm = (int) (yOffcet * (float) height.intValue() / 2);

                Object selectedFile = printerAppInternalEditorWindow.getFileTemplateChooseComboBox();

                ServicePaintTextAsImage.addImgText(AppSettings.TEMPLATE_FOLDER + selectedFile,
                        AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                ServiceInternalEditor.updatePanelImage(AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }

    public static void setDefaultControlTextLabelTemplate(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        PrinterAppInternalEditorWindow printerAppInternalEditorWindow = PrinterAppInternalEditorWindow.getInstance(baseWindow);

        printerAppInternalEditorWindow.setTextLabel("");
        printerAppInternalEditorWindow.setXPosTextSliderValue(0);
        printerAppInternalEditorWindow.setYPosTextSliderValue(0);

    }
}
