package lib.repository.paint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class RepositoryPicture {
    public static void savePicturePNG(BufferedImage bi, String path){
        try {
            File outputfile = new File(path);

            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getPicture(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
