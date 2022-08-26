package lib.service.internal.templateEditor;

import lib.app.ThreadCounter;
import lib.repository.file.ImageMagicAPI;
import lib.service.Service;
import lib.service.file.ServiceFile;
import lib.service.internal.label.ServiceInternalLabelEditor;
import lib.service.paint.ServicePaintText;
import lib.service.paint.ServicePaintTransform;
import lib.settings.AppSettings;
import lib.ui.screens.PrinterAppBaseWindow;
import lib.ui.screens.internal.PrinterAppInternalTemplateEditorWindow;
import lib.ui.screens.internal.print.PrinterAppInternalPrintLabelWindow;
import lib.ui.templates.BaseWindow;

import javax.swing.*;

public class ServiceInternalTemplateEditor {
    public static void updatePanelImage(String pathImage){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.updateImage(pathImage);
    }

    public static void enableComponentsControl(boolean enable){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.setSaveButtonEnable(enable);
        printerAppInternalTemplateEditorWindow.setClearButtonEnable(enable);
        printerAppInternalTemplateEditorWindow.setTextTextFieldEnable(enable);
        printerAppInternalTemplateEditorWindow.setXPosTextSliderEnable(enable);
        printerAppInternalTemplateEditorWindow.setYPosTextSliderEnable(enable);
        printerAppInternalTemplateEditorWindow.setFontComboBoxEnable(enable);
        printerAppInternalTemplateEditorWindow.setTextSizeTextFieldEnable(enable);
    }

    public static void refreshTemplateWithTextParameters(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        Object textSize = printerAppInternalTemplateEditorWindow.getTextSizeLabel();
        if(Service.isDataIntNumeric(textSize)) {
            try {
                Integer height = Integer.parseInt(printerAppInternalTemplateEditorWindow.getHeight());
                Integer width = Integer.parseInt(printerAppInternalTemplateEditorWindow.getWidth());

                String textValue = printerAppInternalTemplateEditorWindow.getTextLabel();
                printerAppInternalTemplateEditorWindow.setApplyButtonEnable(!textValue.equals(""));

                Object fontValue = printerAppInternalTemplateEditorWindow.getFontChooseComboBox();

                int xPosSlider = printerAppInternalTemplateEditorWindow.getXPosTextSliderValue();
                float xOffcet = Service.sliderToFloatRecalc(xPosSlider);
                int xOffcet_mm = (int) (xOffcet * (float) width.intValue() / 2);

                int yPosSlider = printerAppInternalTemplateEditorWindow.getYPosTextSliderValue();
                float yOffcet = Service.sliderToFloatRecalc(yPosSlider);
                int yOffcet_mm = (int) (yOffcet * (float) height.intValue() / 2);

                Object selectedFile = printerAppInternalTemplateEditorWindow.getFileTemplateChooseComboBox();

                ServicePaintText.addImgText(AppSettings.TEMPLATE_FOLDER + selectedFile,
                        AppSettings.TEMPLATE_TEMP_FOLDER + AppSettings.TEMPLATE_TEMP_DEFAULT_NAME,
                        width.intValue(), height.intValue(), xOffcet_mm, yOffcet_mm, textValue,
                        Integer.parseInt((String) textSize), (String) fontValue);

                ServiceInternalTemplateEditor.updatePanelImage(AppSettings.TEMPLATE_TEMP_FOLDER + AppSettings.TEMPLATE_TEMP_DEFAULT_NAME);
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
    }

    public static void setDefaultControlTextLabelTemplate(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        PrinterAppInternalTemplateEditorWindow printerAppInternalTemplateEditorWindow = PrinterAppInternalTemplateEditorWindow.getInstance(baseWindow);

        printerAppInternalTemplateEditorWindow.setTextLabel("");
        printerAppInternalTemplateEditorWindow.setApplyButtonEnable(false);
        printerAppInternalTemplateEditorWindow.setXPosTextSliderValue(0);
        printerAppInternalTemplateEditorWindow.setYPosTextSliderValue(0);

    }

    public static void clearAllGeneratedLabelPNGFiles(){
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();

        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        ServiceFile.clearAllGeneratedLabelPNGFiles();

        printerAppInternalPrintLabelWindow.updateComboBoxLabelItem();

        printerAppInternalPrintLabelWindow.chooseComboBoxLabelObject(AppSettings.TEMPLATE_DEFAULT_NAME);

        ServiceInternalLabelEditor.updatePanelImage(AppSettings.LABEL_PCX_TO_PNG_FOLDER + AppSettings.TEMPLATE_DEFAULT_NAME);

    }

    public static boolean convertPCX_To_Png() {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        final PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

        final String[] filesFolderPCX = ServiceFile.listFilesForFolder(AppSettings.LABEL_EXTERNAL_PCX_FOLDER);

        final boolean isExecute = false;

        Thread processFileThread = new Thread(new Runnable() {
            public void run() {
                int i= 1;
                final int numPcx = getPCXFolderFileNum(AppSettings.LABEL_EXTERNAL_PCX_FOLDER);
                ThreadCounter.setCountThread(numPcx);
                for (String file : filesFolderPCX) {
                    if (file.contains(".pcx")) {
                        ImageMagicAPI.getInstance().convertPCX_TO_PNG(AppSettings.LABEL_EXTERNAL_PCX_FOLDER + file, AppSettings.LABEL_PCX_TO_PNG_FOLDER + file.replace(".pcx", ".png"));
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
                            convertPCX_To_Png_Finish();
                        }
                    }
                }
            }
        });

        processFileThread.start();

        return isExecute;
    }

    private static void convertPCX_To_Png_Finish() {
        BaseWindow baseWindow = PrinterAppBaseWindow.getInstance().getBaseWindow();
        PrinterAppInternalPrintLabelWindow printerAppInternalPrintLabelWindow = PrinterAppInternalPrintLabelWindow.getInstance(baseWindow);

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
