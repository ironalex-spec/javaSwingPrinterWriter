package test.java;

import main.java.service.print.ServicePrintRoundedRectangleAsImage;
import main.java.service.print.ServicePrintTextAsImage;
import main.java.settings.AppSettings;

public class TestPrintingImages {
    public static void main(String[] args) {

        testRoundedRectangle();
    }

    public static void testRoundedRectangle(){
        ServicePrintRoundedRectangleAsImage.savePrintRectangleImage("src/main/resources/editor/img/save.png", 300,100,20,1);
    }

    public static void testPrintText(){
        ServicePrintTextAsImage.savePrintTextAsImage("save.png", 5000,400, 0, 400,"Hello My Dear friend",400,"Serif");
    }

    public static void testRoundedRectangleWithText(){
        ServicePrintRoundedRectangleAsImage.savePrintRectangleImage("rounded.png", 1000,500,300,2);

        ServicePrintTextAsImage.addImgText("rounded.png", "rounded.png", 1004,504,"Hello My Dear friend",100,"Serif");

    }
}
