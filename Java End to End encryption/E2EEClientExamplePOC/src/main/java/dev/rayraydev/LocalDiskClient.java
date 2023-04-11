package dev.rayraydev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.io.FileUtils;

public class LocalDiskClient {

    private static final String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";
    private static final int KEY_SIZE = 32;
    private static final int IV_SIZE = 16;

    public static void main(String[] args) throws Exception {
        // Encrypt a file and save it to disk
        File inputFile = new File("C://encryption//input_file.png");
        File encryptedFile = new File("C://encryption//encrypted.enc");
        File keyFile = new File("C://encryption//key.bin");
        encryptFile(inputFile, encryptedFile, keyFile);

        // Decrypt the encrypted file and save it to disk
        File decryptedFile = new File("C://encryption//decrypted.png");
        decryptFile(encryptedFile, decryptedFile, keyFile);

        // Verify that the decrypted file matches the original file
        boolean filesMatch = FileUtils.contentEquals(inputFile, decryptedFile);
        System.out.println("Files match: " + filesMatch);
    }


    public static void encryptFile(File inputFile, File encryptedFile, File keyFile) throws Exception {
        byte[] key = generateRandomBytes(KEY_SIZE);
        byte[] iv = generateRandomBytes(IV_SIZE);
        SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        FileInputStream inputStream = new FileInputStream(inputFile);
        CipherOutputStream outputStream = new CipherOutputStream(new FileOutputStream(encryptedFile), cipher);
        int len;
        byte[] buf = new byte[8192];
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();
        inputStream.close();
        System.out.println("File encrypted successfully.");
        FileOutputStream keyOutputStream = new FileOutputStream(keyFile);
        keyOutputStream.write(key);
        keyOutputStream.write(iv);
        keyOutputStream.close();
        System.out.println("Key and IV saved to file.");
    }



    public static void decryptFile(File encryptedFile, File decryptedFile, File keyFile) throws Exception {
        byte[] keyAndIV = Files.readAllBytes(keyFile.toPath());
        byte[] key = Arrays.copyOfRange(keyAndIV, 0, KEY_SIZE);
        byte[] iv = Arrays.copyOfRange(keyAndIV, KEY_SIZE, KEY_SIZE + IV_SIZE);
        SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        FileInputStream inputStream = new FileInputStream(encryptedFile);
        CipherInputStream outputStream = new CipherInputStream(new FileInputStream(encryptedFile), cipher);
        FileOutputStream fileOutputStream = new FileOutputStream(decryptedFile);
        int len;
        byte[] buf = new byte[8192];
        while ((len = outputStream.read(buf)) != -1) {
            fileOutputStream.write(buf, 0, len);
        }
        outputStream.close();
        inputStream.close();
        fileOutputStream.close();
        System.out.println("File decrypted successfully.");
    }



    public static byte[] generateRandomBytes(int size) {
        byte[] bytes = new byte[size];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }


    public static String bytesToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static byte[] hexStringToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return bytes;
    }


}

