package lib.repository.file;

import lib.service.file.ServiceFile;
import lib.service.paint.ServicePaintTransform;
import lib.service.print.ServicePrint;
import lib.settings.AppSettings;


public class ImageMagicAPI {
    private static ImageMagicAPI single_instance = null;
    private boolean isInstalled;

    private ImageMagicAPI(){
        isInstalled = RepositoryConsole.getInstance().consoleExecute(new String[]{AppSettings.IMAGE_MAGICK_API_FOLDER + "magick.exe"}, true);
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

                    /*Scale PCX to format*/
      /*              float scale = ServicePrint.getScaleImageForTargetPrinter(folderPng + file.replace(".pcx", ".png"));
    */                ServicePaintTransform.scalePicture_v3(folderPng + file.replace(".pcx", ".png"), folderPng + file.replace(".pcx", ".png") , 1, 1);

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

            isExecute = RepositoryConsole.getInstance().consoleExecute(commands, true);
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
}
