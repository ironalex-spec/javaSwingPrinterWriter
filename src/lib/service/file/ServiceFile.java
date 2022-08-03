package lib.service.file;

import lib.service.Service;
import lib.settings.AppSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServiceFile {
    public static String generateFilenameByPrm(int width, int height, int fillet){
        String filename = "_" + width + "_" + height + "_" + fillet + "_.png";

        return filename;
    }

    public static void clearAllGeneratedFiles(){
        String[] files = Service.listFilesForFolder(AppSettings.TEMPLATE_FOLDER);
        for(String s : files){
            if (isValidFilename(s)){
                try {
                    Files.delete(Paths.get(AppSettings.TEMPLATE_FOLDER + s));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void clearFileByFilename(String filename){
        if (isValidFilename(filename)){
            try {
                Files.delete(Paths.get(AppSettings.TEMPLATE_FOLDER + filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Integer getWidthFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[1]);
        } else {
            return null;
        }
    }

    public static Integer getHeightFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[2]);
        } else {
            return null;
        }
    }

    public static Integer getFilletFromFilename(String filename){
        String[] filesData = getDataFromFilename(filename);

        if (isValidFilename(filename)) {
            return Integer.parseInt(filesData[3]);
        } else {
            return null;
        }
    }

    private static String[] getDataFromFilename(String filename){
        String[] split = filename.split("_");

        if(isValidFilename(filename)){
            return split;
        } else {
            return null;
        }
    }

    private static boolean isValidFilename(String filename){
        String[] split = filename.split("_");

        if(split.length < 5){
            return false;
        } else {
            return true;
        }
    }
}
