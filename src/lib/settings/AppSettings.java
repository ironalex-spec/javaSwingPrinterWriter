package lib.settings;

import java.awt.*;

public class AppSettings {
    public static final double inchToCmValue = 2.54;
    public static final int PPI_INCH_Screen = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int PPI_CM_Screen = (int) (PPI_INCH_Screen / inchToCmValue);

    public static final String TEMPLATE_FOLDER = "D:/java/MyPrinterApp/resources/editor/img/template/";
    public static final String TEMPLATE_DEFAULT_NAME = "default.jpg";

    public static final String TEMPLATE_TEMP_FOLDER = "D:/java/MyPrinterApp/resources/editor/img/temp/";
    public static final String TEMPLATE_TEMP_DEFAULT_NAME = "tempText.png";

    public static final String TEMPLATE_PRINTING_FOLDER = "D:/java/MyPrinterApp/resources/editor/img/printing/";
    public static final String TEMPLATE_PRINTING_NAME = "printer.png";

    public static final int MAX_SLIDER_VALUE = 1000;
}
