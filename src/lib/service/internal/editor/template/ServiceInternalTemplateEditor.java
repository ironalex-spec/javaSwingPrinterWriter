package lib.service.internal.editor.template;

import lib.service.Service;
import lib.service.paint.ServicePaintText;
import lib.app.Settings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.templates.BaseWindow;

public class ServiceInternalTemplateEditor {
    private static ServiceInternalTemplateEditor single_instance;
    private  PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow;

    public static ServiceInternalTemplateEditor getInstance() {
        if (single_instance == null) {
            single_instance = new ServiceInternalTemplateEditor();
        }
        return single_instance;
    }

    public static void clearInstance(){
        single_instance = null;
    }

    ServiceInternalTemplateEditor(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);
    }

    public void updatePanelImage(String pathImage){
        printerAppInternalTemplateEditorWindow.updateImage(pathImage);
    }

    public void enableComponentsControl(boolean enable){
        printerAppInternalTemplateEditorWindow.setSaveButtonEnable(enable);
        printerAppInternalTemplateEditorWindow.setClearButtonEnable(enable);
        printerAppInternalTemplateEditorWindow.setTextTextFieldEnable(enable);
        printerAppInternalTemplateEditorWindow.setXPosTextSliderEnable(enable);
        printerAppInternalTemplateEditorWindow.setYPosTextSliderEnable(enable);
        printerAppInternalTemplateEditorWindow.setFontComboBoxEnable(enable);
        printerAppInternalTemplateEditorWindow.setTextSizeTextFieldEnable(enable);
    }

    public void refreshTemplateWithTextParameters(){
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

                ServicePaintText.addImgText(Settings.TEMPLATE_FOLDER + selectedFile,
                        Settings.TEMPLATE_TEMP_FOLDER + Settings.TEMPLATE_TEMP_DEFAULT_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                updatePanelImage(Settings.TEMPLATE_TEMP_FOLDER + Settings.TEMPLATE_TEMP_DEFAULT_NAME);
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }

    public void setDefaultControlTextLabelTemplate(){
        printerAppInternalTemplateEditorWindow.setTextLabel("");
        printerAppInternalTemplateEditorWindow.setApplyButtonEnable(false);
        printerAppInternalTemplateEditorWindow.setXPosTextSliderValue(0);
        printerAppInternalTemplateEditorWindow.setYPosTextSliderValue(0);
    }
}
