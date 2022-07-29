package main.java.ui.screens.internal;

import main.java.controller.baseWindow.internalWindow.editorTemplate.ActionInternalEditorTemplateWindowButtonSave;
import main.java.controller.baseWindow.internalWindow.editorTemplate.ActionInternalEditorTemplateWindowComboBoxFiles;
import main.java.controller.baseWindow.internalWindow.editorTemplate.ActionInternalEditorTemplateWindowTextFields;
import main.java.ui.templates.BaseWindow;
import main.java.ui.templates.InternalWindow;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.io.File;
import java.util.ArrayList;

public class PrinterAppInternalTemplateEditorWindow {
    private static PrinterAppInternalTemplateEditorWindow single_instance = null;
    private BaseWindow printerAppBaseWindow;
    private InternalWindow internalWindow;

    private String sValue_Input_Width = null;
    private String sValue_Input_Height = null;
    private String sValue_Input_Fillet = null;


    public PrinterAppInternalTemplateEditorWindow(BaseWindow baseWindow){
        printerAppBaseWindow =  baseWindow;

        internalWindow = new InternalWindow(baseWindow, "Template editor", 0,0,baseWindow.getWidth() - 10,baseWindow.getHeight()-60);
        try {
            internalWindow.setMaximum(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        internalWindow.addLabel("Pane_1","Pane_1_Label_Width","Width, mm", 5,30,80,30);

        String[] filesName = listFilesForFolder("src/main/resources/editor/img/");
        internalWindow.addComboBox("Pane_1","Pane_1_ComboBox_Files", filesName, 90,30,100,30);
        internalWindow.addComboBoxActionListener("Pane_1_ComboBox_Files", new ActionInternalEditorTemplateWindowComboBoxFiles());

        internalWindow.addLabel("Pane_1","Pane_1_Label_Width","Width, mm", 5,80,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_Width","", 90,80,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_Width", (byte) 0);
        internalWindow.addTextFieldListener("Pane_1_TextField_Width", new ActionInternalEditorTemplateWindowTextFields());
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_Width", new ActionInternalEditorTemplateWindowTextFields());

        internalWindow.addLabel("Pane_1","Pane_1_Label_Height","Height, mm", 5,130,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_Height","", 90,130,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_Height", (byte) 0);
        internalWindow.addTextFieldListener("Pane_1_TextField_Height", new ActionInternalEditorTemplateWindowTextFields());
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_Height", new ActionInternalEditorTemplateWindowTextFields());

        internalWindow.addLabel("Pane_1","Pane_1_Label_Fillet","Fillet, mm", 5,180,80,30);
        internalWindow.addTextField("Pane_1","Pane_1_TextField_Fillet","", 90,180,100,30);
        internalWindow.setTextFieldFormat("Pane_1_TextField_Fillet", (byte) 0);
        internalWindow.addTextFieldListener("Pane_1_TextField_Fillet", new ActionInternalEditorTemplateWindowTextFields());
        internalWindow.addTextFieldKeyListener("Pane_1_TextField_Fillet", new ActionInternalEditorTemplateWindowTextFields());

        internalWindow.addButton("Pane_1","Pane_1_Button_Save","Save", 90,230,100,30);
        internalWindow.setButtonEnable("Pane_1_Button_Save", false);
        internalWindow.addButtonListener("Pane_1_Button_Save", new ActionInternalEditorTemplateWindowButtonSave());


        internalWindow.addLabelAsImage("Pane_2","LabelImage_1",null, 0,10,100,100);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane_1", "Pane_2", 200);
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

    public Object getComboBoxSelectedItem(String keyComboBox){
        return  internalWindow.getComboBoxSelectedItem(keyComboBox);
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

    public void setSaveButtonEnable(boolean enable){
        internalWindow.setButtonEnable("Pane_1_Button_Save", enable);
    }

    public void updateImage(String imagePath){
        internalWindow.updateLabelImage("ScrolPane_1","LabelImage_1",imagePath);
    }

    public void updateComboBoxFileItem(){
        String[] filesName = listFilesForFolder("src/main/resources/editor/img/");
        internalWindow.updateComboBoxItems("Pane_1_ComboBox_Files", filesName);
    }

    public void chooseComboBoxObject(Object object){
        internalWindow.chooseComboBoxItem("Pane_1_ComboBox_Files", object);
    }

    public static PrinterAppInternalTemplateEditorWindow getInstance(BaseWindow baseWindow)
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new PrinterAppInternalTemplateEditorWindow(baseWindow);
        }
        return single_instance;
    }

    public static String[] listFilesForFolder(String filesFolder) {
        String[] objects = null;

        File folder = new File(filesFolder);

        int numObject = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry.getAbsolutePath());
            } else {
                numObject++;
            }
        }

        objects = new String[numObject];

        int i = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry.getAbsolutePath());
            } else {
                objects[i] = fileEntry.getName();
                i++;
            }
        }




        return objects;
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

    private void  clearInternalWindowInstance(){
        single_instance = null;
    }
}
