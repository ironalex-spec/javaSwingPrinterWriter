package lib.repository.cipher;

import lib.repository.file.ImageMagicAPI;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class RepositoryCipher {
    private final String key = "AlexZheleznev196";
    private final Key aesKey = new SecretKeySpec(key.getBytes(), "AES");

    private static RepositoryCipher single_instance;

    private Cipher cipher = null;

    RepositoryCipher()  {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException algException){
            algException.printStackTrace();
        }
    }


    public byte[] encryptText(byte[] text) {
        try{
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text);
            return encrypted;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException algException){
            algException.printStackTrace();
        }
        return null;
    }

    public byte[] decryptText(byte[] encrypText) {
        try{
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decrypted = cipher.doFinal(encrypText);
            return decrypted;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException algException){
            algException.printStackTrace();
        }
        return null;
    }

    public static RepositoryCipher getInstance() {
        if (single_instance == null) {
            single_instance = new RepositoryCipher();
        }
        return single_instance;
    }

    public String byteArrToString(byte[] sourceByte) {
        String out = "";
        for (byte b: sourceByte) {
            char c;

            if (b<0){
                c = (char) (-1*b);
            } else {
                c = (char) (b);
            }

            if (c == ' '){
                c='z';
            } else if (c == '\r') {
                c='r';
            } else if (c == '\n') {
                c='n';
            } else if (c == '\t') {
                c='t';
            }

            out += Character.toString(c);
        }
        return out;
    }
}
