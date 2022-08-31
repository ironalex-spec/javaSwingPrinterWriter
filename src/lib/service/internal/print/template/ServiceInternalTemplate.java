package lib.service.internal.print.template;

import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.paint.ServicePaintText;
import lib.app.Settings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintTemplateWindow;
import lib.ui.templates.BaseWindow;

public class ServiceInternalTemplate {
    private static ServiceInternalTemplate single_instance;
    private PrinterAppInternalPrintTemplateWindow printerAppInternalPrintTemplateWindow;

    public static ServiceInternalTemplate getInstance() {
        if (single_instance == null) {
            single_instance = new ServiceInternalTemplate();
        }
        return single_instance;
    }

    public static void clearInstance(){
        single_instance = null;
    }

    ServiceInternalTemplate(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        printerAppInternalPrintTemplateWindow = PrinterAppInternalPrintTemplateWindow.getInstance(baseWindow);
    }

    public void updatePanelImage(String pathImage){
        printerAppInternalPrintTemplateWindow.updateImage(pathImage);
    }

    public void enableComponentsControl(boolean enable){
        printerAppInternalPrintTemplateWindow.setTextTextFieldEnable(enable);
        printerAppInternalPrintTemplateWindow.setXPosTextSliderEnable(enable);
        printerAppInternalPrintTemplateWindow.setYPosTextSliderEnable(enable);
        printerAppInternalPrintTemplateWindow.setFontComboBoxEnable(enable);
        printerAppInternalPrintTemplateWindow.setTextSizeTextFieldEnable(enable);
    }

    public boolean isEnableComponentTextControl(){
        Object selectedItem = printerAppInternalPrintTemplateWindow.getFileTemplateChooseComboBox();

        boolean enableTextControl = !((String) selectedItem).equals(Settings.TEMPLATE_DEFAULT_NAME);

        return enableTextControl;
    }

    public void refreshTemplateWithTextParameters(){
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

                ServicePaintText.addImgText(Settings.TEMPLATE_FOLDER + selectedFile,
                        Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                updatePanelImage(Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME);
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }

    public void setDefaultControlTextLabelTemplate(){
        printerAppInternalPrintTemplateWindow.setTextLabel("");
        printerAppInternalPrintTemplateWindow.setXPosTextSliderValue(0);
        printerAppInternalPrintTemplateWindow.setYPosTextSliderValue(0);
    }
}
