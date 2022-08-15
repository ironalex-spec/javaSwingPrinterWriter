package lib.repository.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RepositoryConsole {
    //in one thread
    public static boolean consoleExecute(String[] commands, boolean isWaitReply){


        Runtime rt = Runtime.getRuntime();

        boolean isExecute = false;

        try {
            Process p = rt.exec(commands);
            if(isWaitReply) {
                String response = readProcessOutput(p);
            }
            isExecute = true;
        }catch(Exception ex) {
            ex.printStackTrace();
            isExecute = false;
        }

        return isExecute;
    }

    //each window in own thread
    public static boolean consoleExecuteOtherThread(final String[] commands){
        boolean isExecute = false;

        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        //use ProcessBuilder here to make the process
                        Process p = Runtime.getRuntime().exec(commands);

                        String response = readProcessOutput(p);
                        boolean test = true;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
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
