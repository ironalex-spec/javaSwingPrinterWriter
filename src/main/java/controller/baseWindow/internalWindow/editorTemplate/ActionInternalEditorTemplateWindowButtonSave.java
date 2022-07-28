package main.java.controller.baseWindow.internalWindow.editorTemplate;


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

        testSaveImage();
    }

    public void testSaveImage(){
        try {
            // retrieve image
            BufferedImage bi = new BufferedImage(600,400,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.setComposite(AlphaComposite.Clear);


            // Keep this until I figured out if it's painted on load or not.
            g2d.setComposite(AlphaComposite.Src);
            g2d.setColor(new Color(255, 255, 255));
            g2d.fillRoundRect(250,100,100, 200, 10, 10);
            g2d.setColor(new Color(0, 0, 0));

            int borderWidth = 20;
            g2d.setStroke(new BasicStroke(borderWidth));
            g2d.drawRoundRect(250,100,100, 200, 10, 10);

            g2d.dispose();

            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
