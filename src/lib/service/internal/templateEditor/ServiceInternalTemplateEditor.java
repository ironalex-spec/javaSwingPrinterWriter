package lib.service.internal.templateEditor;

import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.internal.label.ServiceInternalLabelEditor;
import lib.service.paint.ServicePaintText;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.templates.BaseWindow;

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
                float xOffcet = Service.sliderToFloatRecalc(xPosSlider);
                int xOffcet_mm = (int) (xOffcet * (float) width.intValue() / 2);

                int yPosSlider = printerAppInternalTemplateEditorWindow.getYPosTextSliderValue();
                float yOffcet = Service.sliderToFloatRecalc(yPosSlider);
                int yOffcet_mm = (int) (yOffcet * (float) height.intValue() / 2);

                Object selectedFile = printerAppInternalTemplateEditorWindow.getFileTemplateChooseComboBox();

                ServicePaintText.addImgText(AppSettings.TEMPLATE_FOLDER + selectedFile,
                        AppSettings.TEMPLATE_TEMP_FOLDER + AppSettings.TEMPLATE_TEMP_DEFAULT_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                ServiceInternalTemplateEditor.updatePanelImage(AppSettings.TEMPLATE_TEMP_FOLDER + AppSettings.TEMPLATE_TEMP_DEFAULT_NAME);
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

    public static void clearAllGeneratedLabelPNGFiles(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        ServiceFile.clearAllGeneratedLabelPNGFiles();

        printerAppInternalPrintLabelWindow.updateComboBoxLabelItem();

        printerAppInternalPrintLabelWindow.chooseComboBoxLabelObject(AppSettings.TEMPLATE_DEFAULT_NAME);

        ServiceInternalLabelEditor.updatePanelImage(AppSettings.LABEL_PCX_TO_PNG_FOLDER + AppSettings.TEMPLATE_DEFAULT_NAME);

    }
}
