/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NavigationUI;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Hassan
 */

public class Encryption2 {
    
    public static void main(String[] args) {

        try{
            
            String key = "Bar12345Bar12345";

            Key myAesKey = new SecretKeySpec(key.getBytes(), "AES");
                   
            Cipher aesCipher;
            aesCipher = Cipher.getInstance("AES");

            byte[] text = "Hassan".getBytes("UTF8");

            aesCipher.init(Cipher.ENCRYPT_MODE, myAesKey);
            byte[] textEncrypted = aesCipher.doFinal(text);

            String s = new String(textEncrypted);
            System.out.println(s);

            aesCipher.init(Cipher.DECRYPT_MODE, myAesKey);
            byte[] textDecrypted = aesCipher.doFinal(textEncrypted);

            s = new String(textDecrypted);
            System.out.println(s);
        }catch(Exception e)
        {
            System.out.println("Exception");
        }
    }
    
    public static String encrypt(String text)   {
        
        String s = "";
        try {
            String key = "Bar12345Bar12345";

            Key myAesKey = new SecretKeySpec(key.getBytes(), "AES");
                   
            Cipher aesCipher;
            aesCipher = Cipher.getInstance("AES");
            byte[] text2 = text.getBytes("UTF8");            
            
            aesCipher.init(Cipher.ENCRYPT_MODE, myAesKey);
            byte[] textEncrypted = aesCipher.doFinal(text2);
            
            s = new String(textEncrypted); 
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Encryption2.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return s;   
    }
    
    
    public static String decrypt(String text, String key)   {
        
        String d = "";
        try {
                        
            Key myAesKey = new SecretKeySpec(key.getBytes(), "AES");
                   
            Cipher aesCipher;
            aesCipher = Cipher.getInstance("AES");

            byte[] text2 = text.getBytes("UTF8");   
            
            aesCipher.init(Cipher.DECRYPT_MODE, myAesKey);
            byte[] textDecrypted = aesCipher.doFinal(text2);           
            
            d = new String(textDecrypted);            

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Encryption2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
}            
       

    
    
    
    
    
   

