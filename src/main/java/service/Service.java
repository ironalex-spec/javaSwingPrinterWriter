package main.java.service;

import java.io.File;
import java.util.ArrayList;

public class Service {
    public static boolean isAllDataNumericInteger(ArrayList<Object> arrayList){

        for (int i = 0 ; i < arrayList.size(); i++) {
            try {
                Integer.parseInt((String) arrayList.get(i));
            } catch (NumberFormatException nfe) {
                return false;
            } catch (NullPointerException nullPointerException) {
                return false;
            }
        }

        return true;
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
