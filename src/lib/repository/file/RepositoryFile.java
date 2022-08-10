package lib.repository.file;

import lib.service.file.ServiceFile;
import lib.settings.AppSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RepositoryFile {
    public static void clearAllGeneratedLabelPNGFiles(){
        String[] files = listFilesForFolder(AppSettings.LABEL_PCX_TO_PNG_FOLDER);
        for(String s : files){
            if (!s.equals(AppSettings.TEMPLATE_DEFAULT_NAME)){
                try {
                    Files.delete(Paths.get(AppSettings.LABEL_PCX_TO_PNG_FOLDER + s));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void clearAllGeneratedTemplateFiles(){
        String[] files = listFilesForFolder(AppSettings.TEMPLATE_FOLDER);
        for(String s : files){
            if (ServiceFile.isValidFilename(s)){
                try {
                    Files.delete(Paths.get(AppSettings.TEMPLATE_FOLDER + s));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void clearFileByFilename(String filename){
        if (ServiceFile.isValidFilename(filename)){
            try {
                Files.delete(Paths.get(AppSettings.TEMPLATE_FOLDER + filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static Integer getImageWidthMMFromPixelSize(String filepath) {
        Integer width = null;

        try {
            BufferedImage bimg = ImageIO.read(new File(filepath));

            int widthPx          = bimg.getWidth();

            width = widthPx * 10 / AppSettings.PPI_CM_Screen;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return width;
    }

    public static Integer getImageHeightMMFromPixelSize(String filepath) {
        Integer height = null;

        try {
            BufferedImage bimg = ImageIO.read(new File(filepath));

            int heightPx         = bimg.getHeight();

            height = heightPx * 10 / AppSettings.PPI_CM_Screen;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return height;
    }

    public static String[] listFilesForFolder(String filesFolder) {
        String[] objects = null;

        File folder = new File(filesFolder);

        int numObject = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry.getAbsolutePath());
            } else {
                numObject++;
            }
        }

        objects = new String[numObject];

        int i = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry.getAbsolutePath());
            } else {
                objects[i] = fileEntry.getName();
                i++;
            }
        }
        return objects;
    }
}
