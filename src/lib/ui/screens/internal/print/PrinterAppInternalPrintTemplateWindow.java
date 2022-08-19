package lib.ui.screens.internal.print;

import lib.controller.baseWindow.internalWindow.print.editor.*;
import lib.controller.baseWindow.internalWindow.print.editor.textTemplate.ActionInternalEditorWindowComboBoxFonts;
import lib.controller.baseWindow.internalWindow.print.editor.textTemplate.ActionInternalEditorWindowSliders;
import lib.controller.baseWindow.internalWindow.print.editor.textTemplate.ActionInternalEditorWindowTextFieldTextLabel;
import lib.controller.baseWindow.internalWindow.print.editor.textTemplate.ActionInternalEditorWindowTextSizeLabel;
import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.settings.AppSettings;
import lib.ui.templates.BaseWindow;
import lib.ui.templates.InternalWindow;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrinterAppInternalPrintTemplateWindow {
    private static PrinterAppInternalPrintTemplateWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;
    private InternalWindow internalWindow;
    private String sValue_Input_TextLabel = null;
    private String sValue_Input_TextSizeLabel = null;

    private PrinterAppInternalPrintTemplateWindow(BaseWindow baseWindow){
        printerAppBaseWindow =  baseWindow;

        internalWindow = new InternalWindow(baseWindow, "Template print", 0,0,baseWindow.getWidth() - 10,baseWindow.getHeight()-60);
        try {
            internalWindow.setMaximum(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        internalWindow.addLabel("Pane_1","Pane_1_Label_Files","Template", 5,30,80,30);

        String[] filesName = ServiceFile.listFilesForFolder(AppSettings.TEMPLATE_FOLDER);
        internalWindow.addComboBox("Pane_1","Pane_1_ComboBox_Files", filesName, 90,30,150,30);
        internalWindow.addComboBoxActionListener("Pane_1_ComboBox_Files", new ActionInternalEditorWindowComboBoxFiles());

        internalWindow.addLabel("Pane_1","Pane_1_Label_TextInput","Text", 25,80,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_TextInput","", 90,80,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_TextInput", (byte) 0);
        internalWindow.setTextFieldEnable("Pane_1_TextField_TextInput", false);
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_TextInput", new ActionInternalEditorWindowTextFieldTextLabel());

        String[] fontNames = Service.listAvailableFonts();
        internalWindow.addLabel("Pane_1","Pane_1_Label_Fonts","Font", 230,55,80,30);
        internalWindow.addComboBox("Pane_1","Pane_1_ComboBox_Fonts", fontNames, 200,80,100,30);
        internalWindow.setComboBoxEnable("Pane_1_ComboBox_Fonts", false);
        internalWindow.addComboBoxActionListener("Pane_1_ComboBox_Fonts", new ActionInternalEditorWindowComboBoxFonts());

        internalWindow.addLabel("Pane_1","Pane_1_Label_TextSize","Size", 310,55,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_TextSize","2", 310,80,30,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_TextSize", (byte) 0);
        internalWindow.setTextFieldEnable("Pane_1_TextField_TextSize", false);
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_TextSize", new ActionInternalEditorWindowTextSizeLabel());

        internalWindow.addLabel("Pane_1","Pane_1_Label_xPosText","X position", 25,130,120,30);
        internalWindow.addSlider("Pane_1","Pane_1_Slider_xPosText",90, 130,250,30,-1*AppSettings.MAX_SLIDER_VALUE, AppSettings.MAX_SLIDER_VALUE, 0);
        internalWindow.setSliderEnable("Pane_1_Slider_xPosText", false);
        internalWindow.addSliderListener("Pane_1_Slider_xPosText", new ActionInternalEditorWindowSliders());

        internalWindow.addLabel("Pane_1","Pane_1_Label_yPosText","Y position", 25,180,120,30);
        internalWindow.addSlider("Pane_1","Pane_1_Slider_yPosText",90, 180,250,30,-1*AppSettings.MAX_SLIDER_VALUE, AppSettings.MAX_SLIDER_VALUE, 0);
        internalWindow.setSliderEnable("Pane_1_Slider_yPosText", false);
        internalWindow.addSliderListener("Pane_1_Slider_yPosText", new ActionInternalEditorWindowSliders());

        internalWindow.addButton("Pane_1","Pane_1_Button_PrintLabel","Print", 170,230,70,30);
        /*String choosePrinterName = PrinterAppBaseWindow.getInstance().getSelectedPrinter();*/
        internalWindow.setButtonEnable("Pane_1_Button_PrintLabel", false);
        internalWindow.addButtonListener("Pane_1_Button_PrintLabel", new ActionInternalEditorWindowButtonPrint());

        internalWindow.addLabelAsImage("Pane_2","LabelImage_1",AppSettings.LABEL_PCX_TO_PNG_FOLDER + AppSettings.TEMPLATE_DEFAULT_NAME, 0,10,100,100);
        internalWindow.addSplitPain(AppSettings.baseWindowContentPosition, "Pane_1", "Pane_2", 350);
        internalWindow.addScrolPaneOneComponent( "ScrolPane_1", "Pane_2", true);

        internalWindow.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                clearInternalWindowInstance();
            }
        });
    }

    public void activateSaveButton(boolean enable){
        internalWindow.setButtonEnable("Pane_1_Button_Save", enable);
    }

    public BaseWindow getBaseWindow(){
        return  printerAppBaseWindow;
    }

    public Object getFileTemplateChooseComboBox(){
        return  internalWindow.getComboBoxSelectedItem("Pane_1_ComboBox_Files");
    }

    public Object getFontChooseComboBox(){
        return  internalWindow.getComboBoxSelectedItem("Pane_1_ComboBox_Fonts");
    }

    public int getXPosTextSliderValue(){
        return  internalWindow.getSliderValue("Pane_1_Slider_xPosText");
    }

    public int getYPosTextSliderValue(){
        return  internalWindow.getSliderValue("Pane_1_Slider_yPosText");
    }

    public String getTextLabel(){
        setTextLabelClassField();

        return sValue_Input_TextLabel;
    }

    public String getTextSizeLabel(){
        setTextSizeLabelClassField();

        return sValue_Input_TextSizeLabel;
    }

    public void setTextLabel(String textLabel){
        sValue_Input_TextLabel = textLabel;

        internalWindow.setTextFieldData("Pane_1_TextField_TextInput", sValue_Input_TextLabel);
    }

    public void setTextSizeLabel(String textSizeLabel){
        sValue_Input_TextSizeLabel = textSizeLabel;

        internalWindow.setTextFieldData("Pane_1_TextField_TextSize", sValue_Input_TextSizeLabel);
    }

    public void setPrintButtonEnable(boolean enable){
        internalWindow.setButtonEnable("Pane_1_Button_PrintLabel", enable);
    }

    public void setTextTextFieldEnable(boolean enable){
        internalWindow.setTextFieldEnable("Pane_1_TextField_TextInput", enable);
    }

    public void setXPosTextSliderEnable(boolean enable){
        internalWindow.setSliderEnable("Pane_1_Slider_xPosText", enable);
    }

    public void setXPosTextSliderValue(int value){
        internalWindow.setSliderValue("Pane_1_Slider_xPosText", value);
    }

    public void setYPosTextSliderEnable(boolean enable){
        internalWindow.setSliderEnable("Pane_1_Slider_yPosText", enable);
    }

    public void setYPosTextSliderValue(int value){
        internalWindow.setSliderValue("Pane_1_Slider_yPosText", value);
    }

    public void setTextSizeTextFieldEnable(boolean enable){
        internalWindow.setTextFieldEnable("Pane_1_TextField_TextSize", enable);
    }

    public void setFontComboBoxEnable(boolean enable){
        internalWindow.setComboBoxEnable("Pane_1_ComboBox_Fonts", enable);
    }

    public void updateImage(String imagePath){
        internalWindow.updateLabelImage("ScrolPane_1","LabelImage_1",imagePath);
    }

    public void updateComboBoxFileItem(){
        String[] filesName = ServiceFile.listFilesForFolder(AppSettings.TEMPLATE_FOLDER);
        internalWindow.updateComboBoxItems("Pane_1_ComboBox_Files", filesName);
    }

    public void chooseComboBoxObject(Object object){
        internalWindow.chooseComboBoxItem("Pane_1_ComboBox_Files", object);
    }

    public static PrinterAppInternalPrintTemplateWindow getInstance(BaseWindow baseWindow) {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new PrinterAppInternalPrintTemplateWindow(baseWindow);
        }
        return single_instance;
    }

    public static boolean isExistInstance() {
        return single_instance != null;
    }

    private void setTextLabelClassField(){
        sValue_Input_TextLabel = internalWindow.getTextFieldData("Pane_1_TextField_TextInput");
    }

    private void setTextSizeLabelClassField(){
        sValue_Input_TextSizeLabel = internalWindow.getTextFieldData("Pane_1_TextField_TextSize");
    }

    private void  clearInternalWindowInstance(){
        single_instance = null;
    }
}
