package lib.repository.file;

import lib.service.file.ServiceFile;
import lib.settings.AppSettings;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ImageMagicAPI {
    private static ImageMagicAPI single_instance = null;
    private boolean isInstalled;

    private ImageMagicAPI(){
        isInstalled = consoleExecute(new String[]{AppSettings.IMAGE_MAGICK_API_FOLDER + "magick.exe"});
    }

    public boolean isAPIInstalled(){
        return isInstalled;
    }

    public boolean convertFolderPCX_To_PNG(String folderPCX, String folderPng){
        boolean isExecute = false;
        if(isInstalled){
            String[] filesFolderPCX = ServiceFile.listFilesForFolder(folderPCX);

            for(String file : filesFolderPCX){
                if (file.contains(".pcx")) {
                    isExecute = convertPCX_TO_PNG(folderPCX + file, folderPng + file.replace(".pcx", ".png"));
                    if(!isExecute) {
                        break;
                    }
                }
            }

        }
        return isExecute;
    }

    private boolean convertPCX_TO_PNG(String pathPCX, String pathPng){
        boolean isExecute = false;
        if(isInstalled){
            String[] commands = new String[]{AppSettings.IMAGE_MAGICK_API_FOLDER + "magick.exe", "convert",
                    pathPCX, "-negate", pathPng};

            isExecute = consoleExecute(commands);
        }

        return isExecute;
    }

    public static ImageMagicAPI getInstance() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new ImageMagicAPI();
        }
        return single_instance;
    }

    private static boolean consoleExecute(String[] commands){
        Runtime rt = Runtime.getRuntime();

        boolean isExecute = false;

        try {
            Process p = rt.exec(commands);
            String response = readProcessOutput(p);
            isExecute = true;
        }catch(Exception ex) {
            ex.printStackTrace();
            isExecute = false;
        }

        return isExecute;
    }

    /**
     * Reads the response from the command. Please note that this works only
     * if the process returns immediately.
     * @param p
     * @return
     * @throws Exception
     */
    private static String readProcessOutput(Process p) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String response = "";
        String line;
        while ((line = reader.readLine()) != null) {
            response += line+"\r\n";
        }
        reader.close();
        return response;
    }
}
