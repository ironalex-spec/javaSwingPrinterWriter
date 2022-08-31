package lib.service.internal.print.label;

import lib.app.ThreadCounter;
import lib.repository.file.ImageMagicAPI;
import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.paint.ServicePaintText;
import lib.app.Settings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.templates.BaseWindow;

import javax.swing.*;

public class ServiceInternalLabel {
    private static ServiceInternalLabel single_instance;
    private PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow;

    public static ServiceInternalLabel getInstance() {
        if (single_instance == null) {
            single_instance = new ServiceInternalLabel();
        }
        return single_instance;
    }

    public static void clearInstance(){
        single_instance = null;
    }

    ServiceInternalLabel(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);
    }

    public void updatePanelImage(String pathImage){
        printerAppInternalPrintLabelWindow.updateImage(pathImage);
    }

    public void enableComponentsControl(boolean enable){
        printerAppInternalPrintLabelWindow.setTextTextFieldEnable(enable);
        printerAppInternalPrintLabelWindow.setXPosTextSliderEnable(enable);
        printerAppInternalPrintLabelWindow.setYPosTextSliderEnable(enable);
        printerAppInternalPrintLabelWindow.setFontComboBoxEnable(enable);
        printerAppInternalPrintLabelWindow.setTextSizeTextFieldEnable(enable);
    }

    public boolean isEnableComponentTextControl(){
        Object selectedItem = printerAppInternalPrintLabelWindow.getFileLabelChooseComboBox();

        boolean enableTextControl = !((String) selectedItem).equals(Settings.TEMPLATE_DEFAULT_NAME);

        return enableTextControl;
    }

    public void refreshTemplateWithTextParameters(){
        Object textSize = printerAppInternalPrintLabelWindow.getTextSizeLabel();
        Object selectedFile = printerAppInternalPrintLabelWindow.getFileLabelChooseComboBox();

        if(Service.isDataIntNumeric(textSize)) {
            try {
                Integer height = ServiceFile.getImageHeightMMFromPixelSize(Settings.LABEL_PCX_TO_PNG_FOLDER + selectedFile);
                Integer width = ServiceFile.getImageWidthMMFromPixelSize(Settings.LABEL_PCX_TO_PNG_FOLDER + selectedFile);

                String textValue = printerAppInternalPrintLabelWindow.getTextLabel();

                Object fontValue = printerAppInternalPrintLabelWindow.getFontChooseComboBox();

                int xPosSlider = printerAppInternalPrintLabelWindow.getXPosTextSliderValue();
                float xOffcet = Service.sliderToFloatRecalc(xPosSlider);
                int xOffcet_mm = (int) (xOffcet * (float) width.intValue() / 2);

                int yPosSlider = printerAppInternalPrintLabelWindow.getYPosTextSliderValue();
                float yOffcet = Service.sliderToFloatRecalc(yPosSlider);
                int yOffcet_mm = (int) (yOffcet * (float) height.intValue() / 2);

                ServicePaintText.addImgText(Settings.LABEL_PCX_TO_PNG_FOLDER + selectedFile,
                        Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                updatePanelImage(Settings.TEMPLATE_PRINTING_FOLDER + Settings.TEMPLATE_PRINTING_NAME);
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }

    public void setDefaultControlTextLabelTemplate(){
        printerAppInternalPrintLabelWindow.setTextLabel("");
        printerAppInternalPrintLabelWindow.setXPosTextSliderValue(0);
        printerAppInternalPrintLabelWindow.setYPosTextSliderValue(0);

    }

    public void clearAllGeneratedLabelPNGFiles(){
        ServiceFile.clearAllGeneratedLabelPNGFiles();

        printerAppInternalPrintLabelWindow.updateComboBoxLabelItem();

        printerAppInternalPrintLabelWindow.chooseComboBoxLabelObject(Settings.TEMPLATE_DEFAULT_NAME);

        updatePanelImage(Settings.LABEL_PCX_TO_PNG_FOLDER + Settings.TEMPLATE_DEFAULT_NAME);
    }

    public boolean convertPCX_To_Png() {
        final String[] filesFolderPCX = ServiceFile.listFilesForFolder(Settings.LABEL_EXTERNAL_PCX_FOLDER);

        final boolean isExecute = false;

        Thread processFileThread = new Thread(new Runnable() {
            public void run() {
                int i= 1;
                final int numPcx = getPCXFolderFileNum(Settings.LABEL_EXTERNAL_PCX_FOLDER);
                ThreadCounter.setCountThread(numPcx);
                for (String file : filesFolderPCX) {
                    if (file.contains(".pcx")) {
                        ImageMagicAPI.getInstance().convertPCX_TO_PNG(Settings.LABEL_EXTERNAL_PCX_FOLDER + file, Settings.LABEL_PCX_TO_PNG_FOLDER + file.replace(".pcx", ".png"));
                        /*ServicePaintTransform.scalePicture_v3(AppSettings.LABEL_PCX_TO_PNG_FOLDER + file.replace(".pcx", ".png"), AppSettings.LABEL_PCX_TO_PNG_FOLDER + file.replace(".pcx", ".png"), 1, 1);
                         */
                        // Runs inside of the Swing UI thread
                        final int finalI = i;
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                printerAppInternalPrintLabelWindow.setProgressBarProcessMaxValue(numPcx);
                                printerAppInternalPrintLabelWindow.setProgressBarProcessValue(finalI);
                            }
                        });

                        try {
                            java.lang.Thread.sleep(100);
                        } catch (Exception e) {
                        }


                        i++;
                        ThreadCounter.decCountThread();
                        if (ThreadCounter.isCountFinish()){
                            ServiceInternalLabel.getInstance().convertPCX_To_Png_Finish();
                        }
                    }
                }
            }
        });

        processFileThread.start();

        return isExecute;
    }

    private void convertPCX_To_Png_Finish() {
        printerAppInternalPrintLabelWindow.updateComboBoxLabelItem();
    }

    public static int getPCXFolderFileNum(String folder){
        final String[] filesFolderPCX = ServiceFile.listFilesForFolder(folder);
        int iNumPcx = 0;
        for (String file : filesFolderPCX) {
            if (file.contains(".pcx")) {
                iNumPcx++;
            }
        }
        return iNumPcx;
    }

    private void sampleProgressBar(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        final PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <= 100; i++) {

                    // Runs inside of the Swing UI thread
                    final int finalI = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            printerAppInternalPrintLabelWindow.setProgressBarProcessValue(finalI);
                        }
                    });

                    try {
                        java.lang.Thread.sleep(100);
                    }
                    catch(Exception e) { }
                }
            }
        }).start();
    }
}
