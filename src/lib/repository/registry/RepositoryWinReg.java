package lib.repository.registry;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RepositoryWinReg {
    /**
     * Success status code
     */
    public static final int REG_SUCCESS = 0;

    /**
     * Failure status code
     */
    public static final int REG_FAILURE = 1;



    /**
     * Implemented root-keys<br>
     * HKLM HKEY_LOCAL_MACHINE elevated privileges needed<br>
     * HKCU HKEY_CURRENT_USER <br>
     * HKCR HKEY_CLASSES_ROOT elevated privileges needed<br>
     * HKU HKEY_USER  <br>
     * HKCC HKEY_CURRENT_CONFIG elevated privileges needed<br>
     *
     */
    public enum WRKey {
        HKLM,  HKCU , HKCR , HKU , HKCC
    }


    /**
     * Registry data-types
     */
    public enum WRType {
        REG_SZ, REG_MULTI_SZ, REG_EXPAND_SZ,
        REG_DWORD, REG_QWORD, REG_BINARY, REG_NONE
    }

    /**
     * Creates a new string for the registry cli.
     *
     * @param hkey the  root key [ HKLM , HKCU ]
     * @param key the name of the key  SOFTWARE\WINDOWS
     * @param valueName the name of the value
     * @param data
     * @param type
     * @param force override an existing key ?
     *
     * @see WRKey
     * @see WRType
     * @return the string value to the registry cli
     */

    public String createRegString(WRKey hkey, String key, String valueName, String data,  WRType type, boolean force) {
        String keyString = "\"" + hkey+"\\" + key + "\"";
        String valueString = valueName!=null 	? " /v "+ valueName : "" ;
        String dataString =  data != null 		? (" " + ( data != null ? " /d " + "\"" + data + "\"" : "")):"";
        String typeString = type != null 		? " /t " + type : "";

        return keyString + valueString + dataString + typeString +  (force ? " /f" : "");
    }

    /**
     * Adds a new key to the windows registry
     *
     * @param hkey The root-key [ HKLM , HKCU ]
     * @param key the key name to create eg. SOFTWARE\TEST\ABCD
     * @return true on success
     * @throws IOException
     * @throws InterruptedException
     *
     */
    public boolean addKey(WRKey hkey, String key){
        try {
            Process proc = Runtime.getRuntime().exec("REG ADD "+hkey+"\\" + key + " /f");
            proc.waitFor();


            return proc.exitValue() == REG_SUCCESS;
        } catch(InterruptedException | IOException ioException ){
            ioException.printStackTrace();
        }
        return false;
    }

    /**
     * Adds a value to an existing registry key
     * @param hkey The root-key [ HKLM , HKCU ]
     * @param key the key name to create eg. SOFTWARE\TEST\ABCD
     * @param valueName the name of the value to add the data
     * @param data the data as a byte array
     * @param type the type of data [ REG_STRING ,REG_DWORD ]
     * @return true on success
     * @throws IOException
     * @throws InterruptedException
     *
     * @see WRKey
     * @see WRType
     */

    public boolean addValue(WRKey hkey, String key, String valueName, String data,  WRType type) {
        try {
            String regString = createRegString(hkey,key,valueName,data,type,true);
            Process proc = Runtime.getRuntime().exec("REG ADD " + regString);
            proc.waitFor();

            return proc.exitValue() == REG_SUCCESS;
        } catch(InterruptedException | IOException ioException ){
            ioException.printStackTrace();
        }
        return false;
    }

    /**
     * Shows a registry value
     * @param hkey The root-key [ HKLM , HKCU ]
     * @param key the key name to open eg. SOFTWARE\TEST\ABCD
     * @param valueName the name of the value
     * @return true on success
     * @throws IOException
     * @throws InterruptedException
     *
     * @see WRKey
     */
    public String showValue(WRKey hkey, String key, String valueName) {
        List<String> response = new ArrayList<String>();
        String valueResponse = null;

        try {
            String regString = createRegString(hkey, key, valueName, null, null, false);
            String execCmd = "REG QUERY " + regString;
            Process proc = Runtime.getRuntime().exec(execCmd);
            proc.waitFor();


            if(proc.exitValue()==REG_SUCCESS) {
                Scanner sc = new Scanner(proc.getInputStream());
                String str = "";

                do {
                    str = sc.nextLine();
                    response.add(str);
                }while(sc.hasNext() && str != null);
            }
            else {
                System.err.println("Query failure..");
            }

            if (proc.exitValue()==REG_SUCCESS) {
                valueResponse = parceRegestryResponseValue(response, valueName);
                return valueResponse;
            }

        } catch(InterruptedException | IOException ioException ){
            ioException.printStackTrace();
        }

        return null;
    }

    private String parceRegestryResponseValue(List<String> response, String valueName) {
        String valueResponse = null;
        for (int i = 0; i < response.size(); i++) {

            if (response.get(i).contains(valueName)) {

                String responseValueI = response.get(i);
                int indexValue = responseValueI.indexOf("REG_");

                if (indexValue > 0) {
                    indexValue += 6;

                    while (responseValueI.substring(indexValue, indexValue + 1).equals(" ")) {
                        indexValue++;

                        if(indexValue == responseValueI.length()){
                            break;
                        }
                    }

                    valueResponse = responseValueI.substring(indexValue);
                    /*if (indexValue < responseValueI.length()) {
                        valueResponse = "";
                        if (indexValue == responseValueI.length() - 1) {
                            valueResponse = split[index];
                        } else {
                            while (indexValue <= split.length - 1) {
                                valueResponse = valueResponse + " " + split[indexValue];
                                indexValue++;
                            }
                        }
                    } else {
                        valueResponse = null;
                    }*/
                } else {
                    valueResponse = null;
                }
            }
        }

        return valueResponse;
    }


    /**
     *
     * @param hkey The root-key [ HKLM , HKCU ]
     * @param key the key name to open eg. SOFTWARE\TEST\ABCD
     * @param valueName the name of the value
     * @param withChildren view all subdirectories
     * @return true on success
     * @throws IOException
     * @throws InterruptedException
     *
     * @see WRKey
     */
    public List<String> showAllValues(WRKey hkey, String key, String valueName, boolean withChildren){
        try {
            List<String> response = new ArrayList<String>();

            String regString = createRegString(hkey,key,valueName,null,null,false);
           /* String valuePath = valueName == null ?  "" : "\\" + valueName;
    */
            String execCmd = "REG QUERY " + regString + " " + (withChildren? " /s" :" ");
            Process proc = Runtime.getRuntime().exec(execCmd);
            proc.waitFor();


            if(proc.exitValue()==REG_SUCCESS) {

                Scanner sc = new Scanner(proc.getInputStream());

                String str = "";
                do {
                    str = sc.nextLine();
                    response.add(str);
                }while(sc.hasNext() && str != null);
                if(sc!=null) {
                    sc.close();
                }
            }
            else {
                System.err.println("Query failure..\n" + regString);
            }


            if (proc.exitValue()==REG_SUCCESS) {

                return response;
            }

        } catch(InterruptedException | IOException ioException ){
            ioException.printStackTrace();
        }

        return null;
    }


    /**
     * Sample program to access the windows registry
     *
     * <br>
     * NOTE: To create, modify or delete entries on HKEY_LOCAL_MACHINE you'll need elevated privileges
     *
     * @param args
     */
}