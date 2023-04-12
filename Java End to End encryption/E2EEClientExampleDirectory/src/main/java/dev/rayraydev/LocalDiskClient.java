package dev.rayraydev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class LocalDiskClient {

    private static final String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";
    private static final int KEY_SIZE = 32;
    private static final int IV_SIZE = 16;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory path: ");
        String inputDirPath = scanner.nextLine();
        File inputDir = new File(inputDirPath);
        File keyFile = new File(inputDir, "key.bin");

        boolean running = true;
        while (running) {
            List<File> filesToDeleteAfterEncryption = new ArrayList<>();
            List<File> filesToDeleteAfterDecryption = new ArrayList<>();
            System.out.println("\nChoose an action:");
            System.out.println("1. Encrypt files");
            System.out.println("2. Decrypt files");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    for (File inputFile : inputDir.listFiles()) {
                        if (inputFile.isFile() && !inputFile.getName().endsWith(".enc") && !inputFile.equals(keyFile)) {
                            File encryptedFile = new File(inputFile.getParent(), inputFile.getName() + ".ra");
                            encryptFile(inputFile, encryptedFile, keyFile);
                            filesToDeleteAfterEncryption.add(inputFile);
                        }
                    }

                    for (File file : filesToDeleteAfterEncryption) {
                        try {
                            Files.delete(file.toPath());
                            System.out.println("Original file deleted successfully: " + file.getName());
                        } catch (IOException e) {
                            System.err.println("Failed to delete the original file: " + file.getName());
                            e.printStackTrace();
                        }
                    }
                    break;

                case 2:
                    for (File encryptedFile : inputDir.listFiles()) {
                        if (encryptedFile.isFile() && encryptedFile.getName().endsWith(".ra")) {
                            String originalFileName = encryptedFile.getName().replace(".ra", "");
                            File decryptedFile = new File(encryptedFile.getParent(), originalFileName);
                            decryptFile(encryptedFile, decryptedFile, keyFile);
                            filesToDeleteAfterDecryption.add(encryptedFile);
                        }
                    }

                    for (File file : filesToDeleteAfterDecryption) {
                        try {
                            Files.delete(file.toPath());
                            System.out.println("Encrypted file deleted successfully: " + file.getName());
                        } catch (IOException e) {
                            System.err.println("Failed to delete the encrypted file: " + file.getName());
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }



    public static void encryptFile(File inputFile, File encryptedFile, File keyFile) throws Exception, IOException {
        byte[] key;
        byte[] iv;

        if (keyFile.exists()) {
            byte[] keyAndIV = Files.readAllBytes(keyFile.toPath());
            key = Arrays.copyOfRange(keyAndIV, 0, KEY_SIZE);
            iv = Arrays.copyOfRange(keyAndIV, KEY_SIZE, KEY_SIZE + IV_SIZE);
        } else {
            key = generateRandomBytes(KEY_SIZE);
            iv = generateRandomBytes(IV_SIZE);
            FileOutputStream keyOutputStream = new FileOutputStream(keyFile);
            keyOutputStream.write(key);
            keyOutputStream.write(iv);
            keyOutputStream.close();
            System.out.println("Key and IV saved to file.");
        }

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
        try {
            Files.delete(inputFile.toPath());
            System.out.println("Original file deleted successfully.");
        } catch (IOException e) {
            System.err.println("Failed to delete the original file.");
            e.printStackTrace();
        }
        inputStream.close();
        System.out.println("File encrypted successfully.");
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
        try {
            Files.delete(encryptedFile.toPath());
            System.out.println("Encrypted file deleted successfully.");
        } catch (IOException e) {
            System.err.println("Failed to delete the encrypted file.");
            e.printStackTrace();
        }

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

