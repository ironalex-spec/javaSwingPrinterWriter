package lib.repository.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RepositoryConsole {
    public static boolean consoleExecute(String[] commands){
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
