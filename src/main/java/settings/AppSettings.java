package main.java.settings;

import java.awt.*;

public class AppSettings {
    public static final double inchToCmValue = 2.54;
    public static final int PPI_INCH_Screen = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int PPI_CM_Screen = (int) (PPI_INCH_Screen / inchToCmValue);

    public static final String templateFolder = "src/main/resources/editor/img/";
}
