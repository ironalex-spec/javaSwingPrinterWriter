package lib.controller.baseWindow;


import lib.service.paint.ServicePaintTransform;
import lib.service.print.ServicePrint;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionBaseWindowTestButtonPrint implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
     /*   ServicePaintTransform.rotateClockwise90(AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME_ROTATE_90, AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
*/      ServicePaintTransform.scalePicture_v3(AppSettings.TEMPLATE_PRINTING_FOLDER + "scale.png", AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME, 1/2.54,1/2.54);
      /*  ServicePrint.printSelectedPngFile("Microsoft Print to PDF", AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
*/        ServicePrint.printSelectedPngFile("TSC TTP-244CE ETHERNET", AppSettings.TEMPLATE_PRINTING_FOLDER + AppSettings.TEMPLATE_PRINTING_NAME);
    }
}
