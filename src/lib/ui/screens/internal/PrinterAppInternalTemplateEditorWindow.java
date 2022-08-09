package lib.ui.screens.internal;

import lib.controller.baseWindow.internalWindow.editorTemplate.*;
import lib.controller.baseWindow.internalWindow.editorTemplate.TextLabel.*;
import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.settings.AppSettings;
import lib.ui.templates.BaseWindow;
import lib.ui.templates.InternalWindow;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.util.ArrayList;

public class PrinterAppInternalTemplateEditorWindow {
    private static PrinterAppInternalTemplateEditorWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;
    private InternalWindow internalWindow;

    private String sValue_Input_Width = null;
    private String sValue_Input_Height = null;
    private String sValue_Input_Fillet = null;
    private String sValue_Input_TextLabel = null;
    private String sValue_Input_TextSizeLabel = null;


    private PrinterAppInternalTemplateEditorWindow(BaseWindow baseWindow){
        printerAppBaseWindow =  baseWindow;

        internalWindow = new InternalWindow(baseWindow, "Template editor", 0,0,baseWindow.getWidth() - 10,baseWindow.getHeight()-60);
        try {
            internalWindow.setMaximum(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        internalWindow.addLabel("Pane_1","Pane_1_Label_Files","Template", 5,30,80,30);

        String[] filesName = ServiceFile.listFilesForFolder(AppSettings.TEMPLATE_FOLDER);
        internalWindow.addComboBox("Pane_1","Pane_1_ComboBox_Files", filesName, 90,30,150,30);
        internalWindow.addComboBoxActionListener("Pane_1_ComboBox_Files", new ActionInternalEditorTemplateWindowComboBoxFiles());

        internalWindow.addButton("Pane_1","Pane_1_Button_ClearAll","Clear All", 250,30,90,30);
   /*     internalWindow.setButtonEnable("Pane_1_Button_ClearAll", true);*/
        internalWindow.addButtonListener("Pane_1_Button_ClearAll", new ActionInternalEditorTemplateWindowButtonClearAll());

        internalWindow.addButton("Pane_1","Pane_1_Button_Save","Save", 90,80,70,30);
        internalWindow.setButtonEnable("Pane_1_Button_Save", false);
        internalWindow.addButtonListener("Pane_1_Button_Save", new ActionInternalEditorTemplateWindowButtonSave());

        internalWindow.addButton("Pane_1","Pane_1_Button_Clear","Clear", 170,80,70,30);
        internalWindow.setButtonEnable("Pane_1_Button_Clear", false);
        internalWindow.addButtonListener("Pane_1_Button_Clear", new ActionInternalEditorTemplateWindowButtonClear());

        internalWindow.addLabel("Pane_1","Pane_1_Label_Width","Width, mm", 5,130,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_Width","", 90,130,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_Width", (byte) 0);
        internalWindow.addTextFieldListener("Pane_1_TextField_Width", new ActionInternalEditorTemplateWindowTextFields());
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_Width", new ActionInternalEditorTemplateWindowTextFields());

        internalWindow.addLabel("Pane_1","Pane_1_Label_Height","Height, mm", 5,180,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_Height","", 90,180,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_Height", (byte) 0);
        internalWindow.addTextFieldListener("Pane_1_TextField_Height", new ActionInternalEditorTemplateWindowTextFields());
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_Height", new ActionInternalEditorTemplateWindowTextFields());

        internalWindow.addLabel("Pane_1","Pane_1_Label_Fillet","Fillet, mm", 5,230,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_Fillet","", 90,230,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_Fillet", (byte) 0);
        internalWindow.addTextFieldListener("Pane_1_TextField_Fillet", new ActionInternalEditorTemplateWindowTextFields());
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_Fillet", new ActionInternalEditorTemplateWindowTextFields());

        internalWindow.addLabel("Pane_1","Pane_1_Label_TextInput","Text", 25,280,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_TextInput","", 90,280,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_TextInput", (byte) 0);
        internalWindow.setTextFieldEnable("Pane_1_TextField_TextInput", false);
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_TextInput", new ActionInternalEditorTemplateWindowTextFieldTextLabel());

        String[] fontNames = Service.listAvailableFonts();
        internalWindow.addLabel("Pane_1","Pane_1_Label_Fonts","Font", 230,250,80,30);
        internalWindow.addComboBox("Pane_1","Pane_1_ComboBox_Fonts", fontNames, 200,280,100,30);
        internalWindow.setComboBoxEnable("Pane_1_ComboBox_Fonts", false);
        internalWindow.addComboBoxActionListener("Pane_1_ComboBox_Fonts", new ActionInternalEditorTemplateWindowComboBoxFonts());

        internalWindow.addLabel("Pane_1","Pane_1_Label_TextSize","Size", 310,250,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_TextSize","2", 310,280,30,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_TextSize", (byte) 0);
        internalWindow.setTextFieldEnable("Pane_1_TextField_TextSize", false);
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_TextSize", new ActionInternalEditorTemplateWindowTextSizeLabel());

        internalWindow.addLabel("Pane_1","Pane_1_Label_xPosText","X position", 25,330,120,30);
        internalWindow.addSlider("Pane_1","Pane_1_Slider_xPosText",90, 330,250,30,-1*AppSettings.MAX_SLIDER_VALUE, AppSettings.MAX_SLIDER_VALUE, 0);
        internalWindow.setSliderEnable("Pane_1_Slider_xPosText", false);
        internalWindow.addSliderListener("Pane_1_Slider_xPosText", new ActionInternalEditorTemplateWindowSliders());

        internalWindow.addLabel("Pane_1","Pane_1_Label_yPosText","Y position", 25,380,120,30);
        internalWindow.addSlider("Pane_1","Pane_1_Slider_yPosText",90, 380,250,30,-1*AppSettings.MAX_SLIDER_VALUE, AppSettings.MAX_SLIDER_VALUE, 0);
        internalWindow.setSliderEnable("Pane_1_Slider_yPosText", false);
        internalWindow.addSliderListener("Pane_1_Slider_yPosText", new ActionInternalEditorTemplateWindowSliders());

        internalWindow.addButton("Pane_1","Pane_1_Button_ApplyText","Apply", 170,430,70,30);
        internalWindow.setButtonEnable("Pane_1_Button_ApplyText", false);
        internalWindow.addButtonListener("Pane_1_Button_ApplyText", new ActionInternalEditorTemplateWindowApplyTextLabel());

        internalWindow.addLabelAsImage("Pane_2","LabelImage_1",AppSettings.TEMPLATE_FOLDER + AppSettings.TEMPLATE_DEFAULT_NAME, 0,10,100,100);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane_1", "Pane_2", 350);
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

    public ArrayList<Object> getTextFieldsValues(){
        ArrayList<Object> arrayList = new ArrayList<>();

        updateClassFields();

        arrayList.add(sValue_Input_Width);
        arrayList.add(sValue_Input_Height);
        arrayList.add(sValue_Input_Fillet);

        return  arrayList;
    }

    public String getHeight(){
        setHeightClassField();

        return sValue_Input_Height;
    }

    public String getWidth(){
        setWidthClassField();

        return sValue_Input_Width;
    }

    public String getFillet(){
        setWidthClassField();

        return sValue_Input_Fillet;
    }

    public String getTextLabel(){
        setTextLabelClassField();

        return sValue_Input_TextLabel;
    }

    public String getTextSizeLabel(){
        setTextSizeLabelClassField();

        return sValue_Input_TextSizeLabel;
    }

    public void setFillet(String fillet){
        sValue_Input_Fillet = fillet;

        internalWindow.setTextFieldData("Pane_1_TextField_Fillet", sValue_Input_Fillet);
    }

    public void setWidth(String width){
        sValue_Input_Width = width;

        internalWindow.setTextFieldData("Pane_1_TextField_Width", sValue_Input_Width);
    }

    public void setHeight(String height){
        sValue_Input_Height = height;

        internalWindow.setTextFieldData("Pane_1_TextField_Height", sValue_Input_Height);
    }

    public void setTextLabel(String textLabel){
        sValue_Input_TextLabel = textLabel;

        internalWindow.setTextFieldData("Pane_1_TextField_TextInput", sValue_Input_TextLabel);
    }

    public void setTextSizeLabel(String textSizeLabel){
        sValue_Input_TextSizeLabel = textSizeLabel;

        internalWindow.setTextFieldData("Pane_1_TextField_TextSize", sValue_Input_TextSizeLabel);
    }

    public void setSaveButtonEnable(boolean enable){
        internalWindow.setButtonEnable("Pane_1_Button_Save", enable);
    }

    public void setClearButtonEnable(boolean enable){
        internalWindow.setButtonEnable("Pane_1_Button_Clear", enable);
    }

    public void setApplyButtonEnable(boolean enable){
        internalWindow.setButtonEnable("Pane_1_Button_ApplyText", enable);
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

    public static PrinterAppInternalTemplateEditorWindow getInstance(BaseWindow baseWindow) {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new PrinterAppInternalTemplateEditorWindow(baseWindow);
        }
        return single_instance;
    }

    private void updateClassFields(){
        setWidthClassField();
        setFilletClassField();
        setHeightClassField();
    }

    private void setWidthClassField(){
        sValue_Input_Width = internalWindow.getTextFieldData("Pane_1_TextField_Width");
    }

    private void setFilletClassField(){
        sValue_Input_Fillet = internalWindow.getTextFieldData("Pane_1_TextField_Fillet");
    }

    private void setHeightClassField(){
        sValue_Input_Height = internalWindow.getTextFieldData("Pane_1_TextField_Height");
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
