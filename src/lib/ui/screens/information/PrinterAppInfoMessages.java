package lib.ui.screens.information;

import lib.app.BuildVersion;

public class PrinterAppInfoMessages {
    public static final String MESSAGE_ERROR_CONFIG = "<html><h2>Bad application config!!!</h2>"
            + "<p>\u2709 mail: zhelezonsany@gmail.com</p>"
            + "<p>\u2709 mail: zhelezon-3@mail.ru</p>"
            + "<p>\u2706 phone: +375-44-750-88-09</p>";
    public static final String MESSAGE_ERROR_LICENSE = "<html><h2>Wrong license application error!!!</h2>"
                                                        + "<p>\u2709 mail: zhelezonsany@gmail.com</p>"
                                                        + "<p>\u2709 mail: zhelezon-3@mail.ru</p>"
                                                        + "<p>\u2706 phone: +375-44-750-88-09</p>";

    public static final String MESSAGE_APPLICATION_ABOUT = "<html><h2>LabelPrint Application</h2>"
                                                                + "<p>version: " + BuildVersion.getBuildVersion() + "</p>"
                                                                + "<h4>Contact data</h4>"
                                                                + "<p>\u2709 mail: zhelezonsany@gmail.com</p>"
                                                                + "<p>\u2709 mail: zhelezon-3@mail.ru</p>"
                                                                + "<p>\u2706 phone: +375-44-750-88-09</p>";
}
