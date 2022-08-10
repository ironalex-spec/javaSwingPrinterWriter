package lib.service.internal.label;

import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.paint.ServicePaintText;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.templates.BaseWindow;

public class ServiceInternalLabelEditor {
    public static void updatePanelImage(String pathImage){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        printerAppInternalPrintLabelWindow.updateImage(pathImage);
    }

    public static void enableComponentsControl(boolean enable){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        printerAppInternalPrintLabelWindow.setTextTextFieldEnable(enable);
        printerAppInternalPrintLabelWindow.setXPosTextSliderEnable(enable);
        printerAppInternalPrintLabelWindow.setYPosTextSliderEnable(enable);
        printerAppInternalPrintLabelWindow.setFontComboBoxEnable(enable);
        printerAppInternalPrintLabelWindow.setTextSizeTextFieldEnable(enable);
    }

    public static boolean isEnableComponentTextControl(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalPrintLabelWindow.getFileLabelChooseComboBox();

        boolean enableTextControl = !((String) selectedItem).equals(AppSettings.TEMPLATE_DEFAULT_NAME);

        return enableTextControl;
    }

    public static void refreshTemplateWithTextParameters(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        Object textSize = printerAppInternalPrintLabelWindow.getTextSizeLabel();
        Object selectedFile = printerAppInternalPrintLabelWindow.getFileLabelChooseComboBox();

        if(Service.isDataIntNumeric(textSize)) {
            try {
                Integer height = ServiceFile.getImageHeightMMFromPixelSize(AppSettings.LABEL_PCX_TO_PNG_FOLDER + selectedFile);
                Integer width = ServiceFile.getImageWidthMMFromPixelSize(AppSettings.LABEL_PCX_TO_PNG_FOLDER + selectedFile);

                String textValue = printerAppInternalPrintLabelWindow.getTextLabel();

                Object fontValue = printerAppInternalPrintLabelWindow.getFontChooseComboBox();

                int xPosSlider = printerAppInternalPrintLabelWindow.getXPosTextSliderValue();
                float xOffcet = Service.sliderToFloatRecalc(xPosSlider);
                int xOffcet_mm = (int) (xOffcet * (float) width.intValue() / 2);

                int yPosSlider = printerAppInternalPrintLabelWindow.getYPosTextSliderValue();
                float yOffcet = Service.sliderToFloatRecalc(yPosSlider);
                int yOffcet_mm = (int) (yOffcet * (float) height.intValue() / 2);

                ServicePaintText.addImgText(AppSettings.LABEL_PCX_TO_PNG_FOLDER + selectedFile,
                        AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                updatePanelImage(AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }

    public static void setDefaultControlTextLabelTemplate(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        printerAppInternalPrintLabelWindow.setTextLabel("");
        printerAppInternalPrintLabelWindow.setXPosTextSliderValue(0);
        printerAppInternalPrintLabelWindow.setYPosTextSliderValue(0);

    }
}
