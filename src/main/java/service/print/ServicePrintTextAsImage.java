package main.java.service.print;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ServicePrintTextAsImage {

    public static void addImgText(String pathBaseImg, String pathWithText, int widthPx, int heightPx, String text, int textSize, String fontName){
        try {
            BufferedImage baseImg = ImageIO.read(new File(pathBaseImg));

            BufferedImage bi =  printTextAnotherImage(baseImg, widthPx, heightPx, text, textSize, fontName);

            File outputfile = new File(pathWithText);
            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static BufferedImage printTextAnotherImage(BufferedImage bufferedImage, int widthPx, int heightPx, String text, int textSize, String fontName) {

        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setPaint(Color.red);
        g2d.setFont(new Font(fontName, Font.BOLD, textSize));

        FontMetrics fm = g2d.getFontMetrics();
        int x = (widthPx - fm.stringWidth(text))/2;
        int y = (heightPx - fm.getHeight())/2;
        g2d.drawString(text, x, y);
        g2d.dispose();

        return bufferedImage;
    }

    public static void savePrintTextAsImage(String path, int widthPx, int heightPx, int xPx, int yPx, String text, int textSize, String fontName){
        try {
            File outputfile = new File(path);

            BufferedImage bi =  printTextAsImage(widthPx, heightPx, xPx, yPx,  text, textSize, fontName);

            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static BufferedImage printTextAsImage(int widthPx, int heightPx, int xPx,int yPx, String text, int textSize, String fontName) {
        BufferedImage img = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        g2d.setPaint(Color.red);
        g2d.setFont(new Font(fontName, Font.BOLD, textSize));

        g2d.drawString(text, xPx, yPx);
        g2d.dispose();

        return img;
    }
}
