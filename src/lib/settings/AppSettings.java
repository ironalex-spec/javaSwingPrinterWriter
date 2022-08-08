package lib.settings;

import java.awt.*;

public class AppSettings {
    public static final double inchToCmValue = 2.54;
    public static final int PPI_INCH_Screen = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int PPI_CM_Screen = (int) (PPI_INCH_Screen / inchToCmValue);

    public static final String TEMPLATE_FOLDER = "src/resources/editor/img/template/";
    public static final String TEMPLATE_DEFAULT_NAME = "default.jpg";

    public static final String TEMPLATE_TEMP_FOLDER = "src/resources/editor/img/temp/";
    public static final String TEMPLATE_TEMP_DEFAULT_NAME = "tempText.png";

    public static final String TEMPLATE_PRINTING_FOLDER = "src/resources/editor/img/printing/";
    public static final String TEMPLATE_PRINTING_NAME = "printer.png";

    public static final String IMAGE_MAGICK_API_FOLDER = "C:/Program Files/ImageMagick-7.1.0-Q16-HDRI/";

    public static final String LABEL_EXTERNAL_PCX_FOLDER = "src/resources/label/";
    public static final String LABEL_PCX_TO_PNG_FOLDER = "src/resources/label/PNG/";

    public static final int MAX_SLIDER_VALUE = 1000;
}
