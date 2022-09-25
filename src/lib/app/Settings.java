package lib.app;

public class Settings {
    public static final double inchToCmValue = 2.54;
    public static int PPI_INCH_Screen /*= Toolkit.getDefaultToolkit().getScreenResolution()*/;
    public static int PPI_CM_Screen /*= (int) (PPI_INCH_Screen / inchToCmValue)*/;

    public static String TEMPLATE_FOLDER /*= "src/resources/editor/img/template/"*/;
    public static String TEMPLATE_DEFAULT_NAME /*= "printer__.png"*/;

    public static String TEMPLATE_TEMP_FOLDER /*= "src/resources/editor/img/temp/"*/;
    public static String TEMPLATE_TEMP_DEFAULT_NAME /*= "tempText.png"*/;

    public static String TEMPLATE_PRINTING_FOLDER /*= "src/resources/editor/img/printing/"*/;
    public static String TEMPLATE_PRINTING_NAME /*= "printer_.png"*/;

    public static String IMAGE_MAGICK_API_FOLDER /*= "C:/Program Files/ImageMagick-7.1.0-Q16-HDRI/"*/;

    public static String LABEL_EXTERNAL_PCX_FOLDER /*= "src/resources/label/"*/;
    public static String LABEL_PCX_TO_PNG_FOLDER /*= "src/resources/editor/img/label/"*/;

    public static final String WINDOWS_PRINTUI_DLL_FOLDER = "C:/Windows/System32/";

    public static final String TEMPLATE_PRINTING_NAME_ROTATE_90 = "printer90.png";

    public static int baseWindowHeight = 100;
    public static int baseWindowWidth = 100;
    public static String baseWindowPosition = "CENTER_CENTER";

    public static int typePrinting = 0;

    public static int typePCXConversion = 0;

    public static int baseWindowContentPosition = 0;

    public static final int MAX_SLIDER_VALUE = 1000;

    public static final int PRINTER_RESOLUTION_DPI_X = 203;
    public static final int PRINTER_RESOLUTION_DPI_Y = 203;
    public static final int PRINTER_PAPER_WIDTH_MM = 100; /*800 pixel*/
    public static final int PRINTER_PAPER_HEIGHT_MM = 40; /*320 pixel*/

    public static double PRINTER_PAPER_PADDING_TOP_MM = 2.0;
    public static double PRINTER_PAPER_PADDING_LEFT_MM = 2.0;

    /*USING STANDART PRINTER WITH PAPER MAX RESOLUTION*/
    /*printer PRINT 100x40 by resolution 204x94*/
}
