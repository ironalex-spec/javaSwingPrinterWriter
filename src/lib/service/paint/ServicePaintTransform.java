package lib.service.paint;

import lib.repository.paint.RepositoryPicture;
import lib.settings.AppSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ServicePaintTransform {
    public static void rotateClockwise90(String destPath, String sourcePath) {
        BufferedImage src = RepositoryPicture.getPicture(sourcePath);

        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(height, width, src.getType());

        Graphics2D graphics2D = dest.createGraphics();
        graphics2D.translate((height - width) / 2, (height - width) / 2);
        graphics2D.rotate(Math.PI / 2, height / 2, width / 2);
        graphics2D.drawRenderedImage(src, null);

        RepositoryPicture.savePicturePNG(dest, destPath);
    }
}
