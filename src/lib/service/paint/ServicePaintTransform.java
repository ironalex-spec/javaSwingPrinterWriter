package lib.service.paint;

import lib.repository.paint.RepositoryPicture;
import lib.settings.AppSettings;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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

    public static void scalePicture(String destPath, String sourcePath, double scaleWidth, double scaleHeight) {
        BufferedImage src = RepositoryPicture.getPicture(sourcePath);

        int newWidth = new Double(src.getWidth() * scaleWidth).intValue();
        int newHeight = new Double(src.getHeight() * scaleHeight).intValue();

        BufferedImage resized = new BufferedImage(newWidth, newHeight, src.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        /*g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);*/

        g.drawImage(src, 0, 0, newWidth, newHeight, 0, 0, src.getWidth(),
                src.getHeight(), null);
        g.dispose();

        RepositoryPicture.savePicturePNG(resized, destPath);
    }

    public static void scalePicture_v2(String destPath, String sourcePath, double scaleWidth, double scaleHeight) {
        BufferedImage src = RepositoryPicture.getPicture(sourcePath);

        int w = src.getWidth();
        int h = src.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scaleWidth, scaleHeight);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(src, after);

        RepositoryPicture.savePicturePNG(after, destPath);
    }

    public static void scalePicture_v3(String destPath, String sourcePath, double scaleWidth, double scaleHeight) {
        BufferedImage src = RepositoryPicture.getPicture(sourcePath);

        int w = src.getWidth();
        int h = src.getHeight();
        BufferedImage after = new BufferedImage((int)(w*scaleWidth), (int)(h*scaleHeight), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scaleWidth, scaleHeight);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(src, after);

        RepositoryPicture.savePicturePNG(after, destPath);
    }
}
