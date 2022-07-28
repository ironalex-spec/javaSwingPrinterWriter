package test.java;

import main.java.controller.HomeController;
import main.java.service.print.ServicePrintRoundedRectangleImage;

public class TestPrintingImages {
    public static void main(String[] args) {

        testPrintText();
    }

    public static void testRoundedRectangle(){
        ServicePrintRoundedRectangleImage.savePrintRectangleImage("save.png", 1000,500,300,50);
    }

    public static void testPrintText(){
        ServicePrintRoundedRectangleImage.savePrintTextAsImage("save.png", 1000,400, 0, 400,"Hello",400,"Serif");
    }

    public static void testRoundedRectangleWithText(){
        ServicePrintRoundedRectangleImage.savePrintRectangleImage("rounded.png", 1000,500,300,2);

        ServicePrintRoundedRectangleImage.addImgText("rounded.png", "rounded.png", 1004,504,"Hello",100,"Serif");

    }
}
