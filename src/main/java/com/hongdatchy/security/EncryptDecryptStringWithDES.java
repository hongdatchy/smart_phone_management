package com.hongdatchy.security;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class EncryptDecryptStringWithDES {
    private Cipher ecipher;
    private Cipher dcipher;

    private SecretKey key;
    EncryptDecryptStringWithDES() {
        try {
            File keyFile = new File("file.txt");// create file in apache tomcat/../bin
            if(!keyFile.exists()){
                // generate secret key using DES algorithm
                key = KeyGenerator.getInstance("DES").generateKey();

                keyFile.createNewFile(); // create file in webapp/projectNameWar/../class
                byte[] rawData = key.getEncoded();
                String encodedKey = Base64.getEncoder().encodeToString(rawData);

                FileWriter myWriter = new FileWriter(keyFile);
                myWriter.write(encodedKey);
                myWriter.close();
            }

            SecretKey keyFromFile = null;

            Scanner myReader = new Scanner(keyFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                byte[] decodedKey = Base64.getDecoder().decode(data);
                keyFromFile = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
            }
            myReader.close();

            // initialize the ciphers with the given key
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, keyFromFile);
            dcipher.init(Cipher.DECRYPT_MODE, keyFromFile);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public  void testDes() {
        try {
            File keyFile = new File("file.txt");// create file in apache tomcat/../bin
            System.out.println(keyFile.exists());
            if(!keyFile.exists()){
                // generate secret key using DES algorithm
                key = KeyGenerator.getInstance("DES").generateKey();

                keyFile.createNewFile(); // create file in webapp/projectNameWar/../class
                byte[] rawData = key.getEncoded();
                String encodedKey = Base64.getEncoder().encodeToString(rawData);

                FileWriter myWriter = new FileWriter(keyFile);
                myWriter.write(encodedKey);
                myWriter.close();
            }


            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key

            SecretKey keyFromFile = null;

            Scanner myReader = new Scanner(keyFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                byte[] decodedKey = Base64.getDecoder().decode(data);
                keyFromFile = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
            }
            myReader.close();

            ecipher.init(Cipher.ENCRYPT_MODE, keyFromFile);
            dcipher.init(Cipher.DECRYPT_MODE, keyFromFile);

            String ciphertext = encrypt("This is a classified message!");

            String plaintext = decrypt(ciphertext);
            System.out.println("ciphertext: " + ciphertext);

            System.out.println("plaintext: " + plaintext);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public  String encrypt(String str) {

        try {
            // encode the string into a sequence of bytes using the named charset

            // storing the result into a new byte array.

            byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);

            byte[] enc = ecipher.doFinal(utf8);

            // encode to base64

            enc = BASE64EncoderStream.encode(enc);

            return new String(enc);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public  String decrypt(String str) {

        try {

            // decode with base64 to get bytes

            byte[] dec = BASE64DecoderStream.decode(str.getBytes());

            byte[] utf8 = dcipher.doFinal(dec);

            // create new string based on the specified charset

            return new String(utf8, "UTF8");

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}
