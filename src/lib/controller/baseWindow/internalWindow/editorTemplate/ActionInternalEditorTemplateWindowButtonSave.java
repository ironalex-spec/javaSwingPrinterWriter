package lib.controller.baseWindow.internalWindow.editorTemplate;


import lib.service.file.ServiceFile;
import lib.service.internal.templateEditor.ServiceInternalTemplateEditor;
import lib.service.paint.ServicePaintRoundedRectangleAsImage;
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

        String filename = ServiceFile.generateFilenameByPrm(width, height, fillet);

        ServicePaintRoundedRectangleAsImage.savePrintRectangleImage(AppSettings.TEMPLATE_FOLDER + filename,width,height, fillet,1);

        printerAppInternalTemplateEditorWindow.updateComboBoxFileItem();

        ServiceInternalTemplateEditor.updatePanelImage(AppSettings.TEMPLATE_FOLDER + filename);

        printerAppInternalTemplateEditorWindow.chooseComboBoxObject(filename);
    }
}
