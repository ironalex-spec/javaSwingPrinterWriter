package lib.service.paint;

import lib.repository.paint.RepositoryPicture;
import lib.app.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ServicePaintText {

    public static void addImgText(String pathBaseImg, String pathWithText, int width_mm, int height_mm, int x_offcet_mm, int y_offcet_mm, String text, int textSize, String fontName){
        BufferedImage baseImg = RepositoryPicture.getPicture(pathBaseImg);

        int widthPx = Settings.PPI_CM_Screen * width_mm / 10;
        int heightPx = Settings.PPI_CM_Screen * height_mm / 10;
        int textSize_ = Settings.PPI_CM_Screen * textSize / 10;
        int x_offcet_Px = Settings.PPI_CM_Screen * x_offcet_mm / 10;
        int y_offcet_Px = Settings.PPI_CM_Screen * y_offcet_mm / 10;

        BufferedImage bi =  printTextAnotherImage(baseImg, widthPx, heightPx, x_offcet_Px, y_offcet_Px, text, textSize_, fontName);

        RepositoryPicture.savePicturePNG(bi, pathWithText);
     }

    public static void savePrintTextAsImage(String path, int width_mm, int height_mm, int x_offcet_mm, int y_offcet_mm, String text, int textSize, String fontName){
        int widthPx = Settings.PPI_CM_Screen * width_mm / 10;
        int heightPx = Settings.PPI_CM_Screen * height_mm / 10;
        int x_offcet_Px = Settings.PPI_CM_Screen * x_offcet_mm / 10;
        int y_offcet_Px = Settings.PPI_CM_Screen * y_offcet_mm / 10;
        int textSize_ = Settings.PPI_CM_Screen * textSize / 10;

        BufferedImage bi =  printTextAsImage(widthPx, heightPx, x_offcet_Px, y_offcet_Px,  text, textSize_, fontName);

        RepositoryPicture.savePicturePNG(bi, path);
    }

    private static BufferedImage printTextAnotherImage(BufferedImage bufferedImage, int widthPx, int heightPx,int x_offcet_Px, int y_offcet_Px, String text, int textSize, String fontName) {
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setPaint(Color.black);
        g2d.setFont(new Font(fontName, Font.BOLD, textSize));

        FontMetrics fm = g2d.getFontMetrics();

        int fontHeight = (fm.getAscent() + fm.getDescent()) / 2;

        //Calculation text int center of box
        int x_center = (widthPx - fm.stringWidth(text))/2;
        int y_center = ((heightPx - fontHeight)/2 + fontHeight);

        g2d.drawString(text, x_center + x_offcet_Px, y_center + y_offcet_Px);
        g2d.dispose();

        return bufferedImage;
    }

    private static BufferedImage printTextAsImage(int widthPx, int heightPx, int x_offsetPx,int y_offsetPx, String text, int textSize, String fontName) {
        BufferedImage img = new BufferedImage(widthPx + x_offsetPx, heightPx + y_offsetPx, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        g2d.setPaint(Color.black);
        g2d.setFont(new Font(fontName, Font.BOLD, textSize));

        FontMetrics fm = g2d.getFontMetrics();

        int fontHeight = (fm.getAscent() + fm.getDescent()) / 2;

        //Calculation text int center of box
        int x_center = (widthPx - fm.stringWidth(text))/2;
        int y_center = ((heightPx - fontHeight)/2 + fontHeight);

        g2d.drawString(text, x_center + x_offsetPx, y_center + y_offsetPx);
        g2d.dispose();

        return img;
    }
}
