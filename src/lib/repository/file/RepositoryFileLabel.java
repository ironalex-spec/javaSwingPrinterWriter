package lib.repository.file;

import lib.service.file.ServiceFile;
import lib.settings.AppSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RepositoryFileLabel {
    private static RepositoryFileLabel single_instance = null;

    public void clearAllGeneratedLabelPNGFiles(){
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

    public void clearAllGeneratedTemplateFiles(){
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

    public void clearFileByFilename(String filename){
        if (ServiceFile.isValidFilename(filename)){
            try {
                Files.delete(Paths.get(AppSettings.TEMPLATE_FOLDER + filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Integer getImageWidthMMFromPixelSize(String filepath) {
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

    public Integer getImageHeightMMFromPixelSize(String filepath) {
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

    public String[] listFilesForFolder(String filesFolder) {
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

    public boolean replaceLineFile(String filepath, int lineNum, String newValue){
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(filepath));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            int i = 1;
            while ((line = file.readLine()) != null) {
                if(i==lineNum) {
                    inputBuffer.append(newValue);
                    inputBuffer.append('\n');
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
                i++;
            }
            file.close();
            String inputStr = inputBuffer.toString();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(filepath);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static RepositoryFileLabel getInstance() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new RepositoryFileLabel();
        }
        return single_instance;
    }
}
