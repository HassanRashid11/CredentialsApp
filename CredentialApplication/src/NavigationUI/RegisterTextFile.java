/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NavigationUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hassan
 */
public class RegisterTextFile extends Encryption2 {
    
    File inputFile = new File("test.xml");
    int key = 15;

    
    
    
    public void textFile (String username, String password) {
        
        File filename = new File("userlist.txt"); 
        
        if (filename.exists())   {
                FileWriter fw = null;
            try {

                fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(encrypt(username, key));
                bw.write(encrypt(password, key));
                //bw.write(username);
                //bw.write(password);                
                bw.newLine();
                bw.close();
            } catch (Exception ex) {
                Logger.getLogger(RegisterTextFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(RegisterTextFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else  {            
            try {            
                                
                FileWriter fw = new FileWriter(filename);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(encrypt(username, key));
                bw.write(encrypt(password, key));
                //bw.write(username);
                //bw.write(password);
                bw.newLine();

                bw.close();

                System.out.println("Done");                
        } catch (Exception e)   {
        }            
        }        
    }

        private static String encrypt(String encrypt, int key) {
            String encrypted = "";
            for(int i = 0; i < encrypt.length(); i++ )
                encrypted +=(char) (encrypt.charAt(i) - key);
            return encrypted;
        }    
    
}
