package lib.service.paint;

import lib.settings.AppSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ServicePaintRoundedRectangleAsImage {

    public static void savePrintRectangleImage(String path, int width_mm, int height_mm, int radius_mm, int borderWidthPx){
        try {
            File outputfile = new File(path);

            int widthPx = AppSettings.PPI_CM_Screen * width_mm / 10;
            int heightPx = AppSettings.PPI_CM_Screen * height_mm / 10;
            int radiusPx = AppSettings.PPI_CM_Screen * radius_mm / 10;

            BufferedImage bi = printRoundedRectangleImage(widthPx,  heightPx, radiusPx, borderWidthPx);

            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static BufferedImage printRoundedRectangleImage(int widthPx, int heightPx, int radiusPx, int borderWidthPx){
            radiusPx = radiusPx * 2;

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


}
