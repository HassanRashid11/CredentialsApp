/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NavigationUI;



import java.io.*;

/**
 *
 *
 */
public class LoginTextFile extends Encryption2 {
        
    int key = 15;
    String user;
    
    public void setUsername(String username)    {
        user = username;
    }
    
    public String getUsername()   {
        return user;
    }
       
    public boolean authenticateTextFile (String username, String password)  {
        
        String fileName = "userlist.txt";
        String line = null;

        try {
            
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            
            while((line = br.readLine()) !=null)   {
                
                //for (int i = 0; i < line.length(); i++ )   {
                    
                    String dec = (decrypt(line, key));                  
                    
                //}  
                if (dec.contains(username + password))   {
                    return true;
                }
            }  return false;
        } catch (Exception e)   {
        } 
        return false;
    }
       
        private static String decrypt(String decrypt, int key) {
            String decrypted = "";
            for(int i = 0; i < decrypt.length(); i++ )
                decrypted +=(char) (decrypt.charAt(i) + key);
            return decrypted;
        }
}
