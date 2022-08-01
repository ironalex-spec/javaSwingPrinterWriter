package lib.service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Service {
    public static boolean isAllDataIntNumeric(ArrayList<Object> arrayList){

        for (int i = 0 ; i < arrayList.size(); i++) {
            if (!isDataIntNumeric((String) arrayList.get(i))){
                return false;
            }
        }

        return true;
    }

    public static boolean isDataIntNumeric(Object object){
        try {
            Integer.parseInt((String) object);
        } catch (NumberFormatException nfe) {
            return false;
        } catch (NullPointerException nullPointerException) {
            return false;
        }

        return true;
    }

    public static String[] listAvailableFonts(){
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        return fonts;
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

    public static boolean renameFile(String folderSource, String newFilename){
        Path yourFile = Paths.get(folderSource);

        try {
            Files.move(yourFile, yourFile.resolveSibling(newFilename), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean moveFileAnotherDirectory(String folderSource, String folderDest){
        try {
            Files.move(Paths.get(folderSource), Paths.get(folderDest), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
