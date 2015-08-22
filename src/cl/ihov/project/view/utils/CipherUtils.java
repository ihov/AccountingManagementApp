package cl.ihov.project.view.utils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.spec.KeySpec;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.Serializable;

public class CipherUtils implements Serializable {

    private Cipher ecipher;
    private Cipher dcipher;
    private final String passPhrase = "*1h0v.,321;*";

    public CipherUtils() {
        byte[] salt = {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03
        };

        int iterationCount = 19;

        try {

            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("EXCEPTION: InvalidAlgorithmParameterException");
        } catch (InvalidKeySpecException e) {
            System.out.println("EXCEPTION: InvalidKeySpecException");
        } catch (NoSuchPaddingException e) {
            System.out.println("EXCEPTION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("EXCEPTION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            System.out.println("EXCEPTION: InvalidKeyException");
        }
    }

    public String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return new String(Base64.encodeBase64(enc));
        } catch (UnsupportedEncodingException ex) {
        } catch (IllegalBlockSizeException ex) {
        } catch (BadPaddingException ex) {
        }
        return null;
    }

    public String decrypt(String str) {
        try {
            byte[] dec = Base64.decodeBase64(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (IOException ex) {
        } catch (IllegalBlockSizeException ex) {
        } catch (BadPaddingException ex) {
        }
        return null;
    }
    private static final long serialVersionUID = 1L;
}
