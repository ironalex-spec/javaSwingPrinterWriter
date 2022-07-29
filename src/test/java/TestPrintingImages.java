package test.java;

import main.java.service.print.ServicePrintRoundedRectangleAsImage;
import main.java.service.print.ServicePrintTextAsImage;
import main.java.settings.AppSettings;

public class TestPrintingImages {
    public static void main(String[] args) {

        testRoundedRectangleWithText();
    }

    public static void testRoundedRectangle(){
        ServicePrintRoundedRectangleAsImage.savePrintRectangleImage("src/main/resources/editor/img/save.png", 300,100,20,1);
    }

    public static void testPrintText(){
        ServicePrintTextAsImage.savePrintTextAsImage("src/main/resources/editor/img/save.png", 100,10, 0, 0,"Hello My Dear friend",10,"Serif");
    }

    public static void testRoundedRectangleWithText(){
        ServicePrintRoundedRectangleAsImage.savePrintRectangleImage("src/main/resources/editor/img/rounded.png", 500,100,50,1);

        ServicePrintTextAsImage.addImgText("src/main/resources/editor/img/rounded.png", "src/main/resources/editor/img/rounded.png", 500,100,0,0,"Hello My Dear friend",40,"Serif");
    }
}
