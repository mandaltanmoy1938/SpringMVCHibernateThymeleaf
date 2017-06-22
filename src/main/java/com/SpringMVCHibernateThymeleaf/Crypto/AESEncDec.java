package com.SpringMVCHibernateThymeleaf.Crypto;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tanmoy on 5/9/2017.
 * Updated by Tanmoy on 5/9/2017.
 */
@Service
public class AESEncDec {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    //            //previous
    public static void encrypt(String key, File inputFile, File encryptedFile)
//            //======end=====
//    public static void encrypt(String key, byte[] inputBytes, File encryptedFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, encryptedFile);
    }

    public static void decrypt(String key, File encryptedFile, File decryptedFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, encryptedFile, decryptedFile);
    }

    //            //previous
    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws CryptoException {
//            //======end=====

//    private static void doCrypto(int cipherMode, String key, byte[] inputBytes, File outputFile) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
//            //previous
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
//            //======end=====
            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
//            //previous
            inputStream.close();
//            //====end===
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }

}
