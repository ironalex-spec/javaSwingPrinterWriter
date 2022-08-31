package lib.repository.file;

import lib.service.file.ServiceFile;
import lib.service.paint.ServicePaintTransform;
import lib.app.Settings;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ImageMagicAPI {
    private static ImageMagicAPI single_instance = null;
    private boolean isInstalled;
    private ExecutorService executor = null;

    private ImageMagicAPI(){
        isInstalled = RepositoryConsole.getInstance().consoleExecute(new String[]{Settings.IMAGE_MAGICK_API_FOLDER + "magick.exe"}, true);
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

    public boolean convertPCX_TO_PNG(String pathPCX, String pathPng){
        boolean isExecute = false;
        if(isInstalled){
            String[] commands = new String[]{Settings.IMAGE_MAGICK_API_FOLDER + "magick.exe", "convert",
                    pathPCX, "-negate", pathPng};

            isExecute = RepositoryConsole.getInstance().consoleExecute(commands, true);
        }

        return isExecute;
    }

    public boolean convertPCX_TO_PNG_Thread(final String pathPCX, final String pathPng){
        boolean isExecute = false;

        executor = Executors.newFixedThreadPool(2);
        try {
            executor.execute(new Runnable() {
                public void run() {
                    String[] commands = new String[]{Settings.IMAGE_MAGICK_API_FOLDER + "magick.exe", "convert",
                            pathPCX, "-negate", pathPng};

                    RepositoryConsole.getInstance().consoleExecute(commands, true);

                }
            });

            isExecute = true;
        }catch(Exception ex) {
            ex.printStackTrace();
            isExecute = false;
        }

        executor.shutdownNow();

        return isExecute;
    }

    public boolean isThreadTerminated(){
        if(executor == null){
            return false;
        }
        return executor.isTerminated();
    }

    public static ImageMagicAPI getInstance() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new ImageMagicAPI();
        }
        return single_instance;
    }
}
