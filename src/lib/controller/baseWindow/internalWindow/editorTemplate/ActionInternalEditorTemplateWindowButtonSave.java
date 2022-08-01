package lib.controller.baseWindow.internalWindow.editorTemplate;


import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;
import lib.service.print.ServicePrintRoundedRectangleAsImage;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.templates.BaseWindow;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionInternalEditorTemplateWindowButtonSave implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        int height = Integer.parseInt(printerAppInternalTemplateEditorWindow.getHeight());
        int width = Integer.parseInt(printerAppInternalTemplateEditorWindow.getWidth());
        int fillet = Integer.parseInt(printerAppInternalTemplateEditorWindow.getFillet());

        String filename = ServiceInternalTemplateEditor.generateFilenameByPrm(width, height, fillet);

        ServicePrintRoundedRectangleAsImage.savePrintRectangleImage(AppSettings.templateFolder + filename,width,height, fillet,1);

        printerAppInternalTemplateEditorWindow.updateComboBoxFileItem();

        ServiceInternalTemplateEditor.updatePanelImage(AppSettings.templateFolder + filename);

        printerAppInternalTemplateEditorWindow.chooseComboBoxObject(filename);
    }
}
