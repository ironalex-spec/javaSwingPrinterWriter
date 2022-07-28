package main.java.service.baseWindow.internalWindow.editorTemplate;

import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;

public class ServiceInternalEditorTemplate {
    private static ServiceInternalEditorTemplate single_instance = null;

    public void saveDataAsPicture() {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalTemplateEditorWindow.getComboBoxSelectedItem("Pane_1_ComboBox_Files");

        printerAppInternalTemplateEditorWindow.updateImage("src/main/resources/editor/img/"+selectedItem);
    }

    public void setSaveButtonEnable(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);
        printerAppInternalTemplateEditorWindow.activateSaveButton(printerAppInternalTemplateEditorWindow.isTextFieldDataValid());

        System.out.println(printerAppInternalTemplateEditorWindow.isTextFieldDataValid());
    }

    public void updatePanelImageComboBoxSelected(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        Object selectedItem = printerAppInternalTemplateEditorWindow.getComboBoxSelectedItem("Pane_1_ComboBox_Files");

        updatePanelImage("src/main/resources/editor/img/" + selectedItem);
    }

    public void updatePanelImage(String pathImage){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.updateImage(pathImage);

    }

    public static ServiceInternalEditorTemplate getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new ServiceInternalEditorTemplate();
        }
        return single_instance;
    }
}
