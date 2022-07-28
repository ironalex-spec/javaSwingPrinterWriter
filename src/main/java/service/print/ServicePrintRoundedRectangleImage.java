package main.java.service.print;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ServicePrintRoundedRectangleImage {

    public static void savePrintRectangleImage(String path, int widthPx, int heightPx, int radiusPx, int borderWidthPx){
        try {
            File outputfile = new File(path);

            BufferedImage bi = printRoundedRectangleImage(widthPx,  heightPx, radiusPx, borderWidthPx);

            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static BufferedImage printRoundedRectangleImage(int widthPx, int heightPx, int radiusPx, int borderWidthPx){
            BufferedImage bi = new BufferedImage(widthPx + 2*borderWidthPx,heightPx + 2*borderWidthPx,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.setComposite(AlphaComposite.Clear);


            // Keep this until I figured out if it's painted on load or not.
            g2d.setComposite(AlphaComposite.Src);
            g2d.setColor(new Color(255, 255, 255));
            g2d.fillRoundRect(borderWidthPx,borderWidthPx,widthPx, heightPx, radiusPx, radiusPx);
            g2d.setColor(new Color(0, 0, 0));

            g2d.setStroke(new BasicStroke(borderWidthPx));
            g2d.drawRoundRect(borderWidthPx,borderWidthPx,widthPx, heightPx, radiusPx, radiusPx);

            g2d.dispose();

            return bi;
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

}
