package lib.service.file;

import lib.repository.file.RepositoryFile;
import lib.settings.AppSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServiceFile {
    public static String generateFilenameByPrm(int width, int height, int fillet){
        String filename = "_" + width + "_" + height + "_" + fillet + "_.png";

        return filename;
    }

    public static void clearAllGeneratedLabelPNGFiles(){
        RepositoryFile.clearAllGeneratedLabelPNGFiles();
    }

    public static void clearAllGeneratedTemplateFiles(){
        RepositoryFile.clearAllGeneratedTemplateFiles();
    }

    public static void clearFileByFilename(String filename){
        RepositoryFile.clearFileByFilename(filename);
    }

    public static Integer getWidthFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[1]);
        } else {
            return null;
        }
    }

    public static Integer getImageWidthMMFromPixelSize(String filepath) {
        Integer width = RepositoryFile.getImageWidthMMFromPixelSize(filepath);

        return width;
    }

    public static Integer getHeightFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[2]);
        } else {
            return null;
        }
    }

    public static Integer getImageHeightMMFromPixelSize(String filepath) {
        Integer height = RepositoryFile.getImageHeightMMFromPixelSize(filepath);

        return height;
    }

    public static Integer getFilletFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[3]);
        } else {
            return null;
        }
    }

    public static String[] listFilesForFolder(String filesFolder) {
        String[] objects = RepositoryFile.listFilesForFolder(filesFolder);

        return objects;
    }

    private static String[] getDataFromFilename(String filename){
        String[] split = filename.split("_");

        if(isValidFilename(filename)){
            return split;
        } else {
            return null;
        }
    }

    public static boolean isValidFilename(String filename){
        String[] split = filename.split("_");

        if(split.length < 5){
            return false;
        } else {
            return true;
        }
    }
}
