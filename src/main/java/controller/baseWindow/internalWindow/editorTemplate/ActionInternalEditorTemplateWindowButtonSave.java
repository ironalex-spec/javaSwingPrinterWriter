package main.java.controller.baseWindow.internalWindow.editorTemplate;


import main.java.service.internal.templateEditor.ServiceInternalTemplateEditor;
import main.java.service.print.ServicePrintRoundedRectangleAsImage;
import main.java.settings.AppSettings;
import main.java.ui.screens.PrinterAppBaseWindow;
import main.java.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import main.java.ui.templates.BaseWindow;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


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
