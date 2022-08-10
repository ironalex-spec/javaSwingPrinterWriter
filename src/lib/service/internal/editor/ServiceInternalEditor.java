package lib.service.internal.editor;

import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.paint.ServicePaintText;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintTemplateWindow;
import lib.ui.templates.BaseWindow;

public class ServiceInternalEditor {
    public static void updatePanelImage(String pathImage){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(baseWindow);

        printerAppInternalPrintTemplateWindow.updateImage(pathImage);
    }

    public static void enableComponentsControl(boolean enable){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(baseWindow);

        printerAppInternalPrintTemplateWindow.setTextTextFieldEnable(enable);
        printerAppInternalPrintTemplateWindow.setXPosTextSliderEnable(enable);
        printerAppInternalPrintTemplateWindow.setYPosTextSliderEnable(enable);
        printerAppInternalPrintTemplateWindow.setFontComboBoxEnable(enable);
        printerAppInternalPrintTemplateWindow.setTextSizeTextFieldEnable(enable);
    }

    public static boolean isEnableComponentTextControl(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalPrintTemplateWindow.getFileTemplateChooseComboBox();

        boolean enableTextControl = !((String) selectedItem).equals(AppSettings.TEMPLATE_DEFAULT_NAME);

        return enableTextControl;
    }

    public static void refreshTemplateWithTextParameters(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(baseWindow);

        Object textSize = printerAppInternalPrintTemplateWindow.getTextSizeLabel();
        Object filenameChoose = printerAppInternalPrintTemplateWindow.getFileTemplateChooseComboBox();

        if(Service.isDataIntNumeric(textSize)) {
            try {
                Integer height = ServiceFile.getHeightFromFilename((String) filenameChoose);
                Integer width = ServiceFile.getWidthFromFilename((String) filenameChoose);

                String textValue = printerAppInternalPrintTemplateWindow.getTextLabel();

                Object fontValue = printerAppInternalPrintTemplateWindow.getFontChooseComboBox();

                int xPosSlider = printerAppInternalPrintTemplateWindow.getXPosTextSliderValue();
                float xOffcet = Service.sliderToFloatRecalc(xPosSlider);
                int xOffcet_mm = (int) (xOffcet * (float) width.intValue() / 2);

                int yPosSlider = printerAppInternalPrintTemplateWindow.getYPosTextSliderValue();
                float yOffcet = Service.sliderToFloatRecalc(yPosSlider);
                int yOffcet_mm = (int) (yOffcet * (float) height.intValue() / 2);

                Object selectedFile = printerAppInternalPrintTemplateWindow.getFileTemplateChooseComboBox();

                ServicePaintText.addImgText(AppSettings.TEMPLATE_FOLDER + selectedFile,
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
        PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(baseWindow);

        printerAppInternalPrintTemplateWindow.setTextLabel("");
        printerAppInternalPrintTemplateWindow.setXPosTextSliderValue(0);
        printerAppInternalPrintTemplateWindow.setYPosTextSliderValue(0);

    }
}
