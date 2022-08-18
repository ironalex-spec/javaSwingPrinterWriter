package lib.service.license;

import lib.repository.registry.RepositoryWinReg;
import lib.service.cipher.ServiceCipher;

import java.nio.charset.StandardCharsets;

public class ServiceAppLicense {
    private static RepositoryWinReg reg = new RepositoryWinReg();

    public static boolean checkAppLicense(){
        String systemEncrypted = getSystemLicenseValueEncryped();

        String registryEncryptedReg = getAppLicenseValueEncryped();

        return registryEncryptedReg.equals(systemEncrypted);
    }

    private static String getSystemLicenseValueEncryped(){
        String response = getSystemLicenseValue();

        if(response != null) {
            byte[] encryptedByte = ServiceCipher.getInstance().encryptText(response.getBytes());

            String encrypted = ServiceCipher.getInstance().byteArrToString(encryptedByte);

            return encrypted;
        }
        return null;
    }

    private static String getAppLicenseValueEncryped(){
        String registryEncryptedReg = reg.showValue(RepositoryWinReg.WRKey.HKCU, "SOFTWARE\\LabelPrintingApp", "encrypted");

        return registryEncryptedReg;
    }

    private static String getSystemLicenseValue(){
        String response = reg.showValue(RepositoryWinReg.WRKey.HKLM, "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion", "ProductId");

        return response;
    }
}
