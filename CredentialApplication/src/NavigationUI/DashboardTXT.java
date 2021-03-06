/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NavigationUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Random;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * 
 */
public class DashboardTXT extends LoginUI {
    
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 400;
    static JTable tableCredential;
    static int ID = 0;
    String user;
    final String userName = LoginUI.user;  
    File file = new File(userName);   
    File key = new File("key.txt");
    
    public void Dashboard4() {
        initComponents();
        
    }

    private void initComponents(){
        
        
        final JFrame frame = new JFrame();
        final JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel);
        buttonPanel.setLayout(null);

        // Set up Add button and its location
        final JButton buttonAdd = new JButton(" Add ");
        buttonAdd.setBounds(50, 325, 100, 20);
        buttonPanel.add(buttonAdd);
        ///* Set up Generate Random Password Button
        final JButton buttonRandom = new JButton("Generate Random Password");
        buttonRandom.setBounds(40, 200, 200, 20);
        buttonPanel.add(buttonRandom);        


        // Set up Exit button and its location
        final JButton buttonExit = new JButton("Exit");
        buttonExit.setBounds(200, 325, 100, 20);
        buttonPanel.add(buttonExit);
        
        
        // Set up of columns in the table
        String[] columns = { "ID", "Website", "Username", "Password" };
        // Set up of the table with the appropriate column headers
        final DefaultTableModel model = new DefaultTableModel(null, columns);
        final JTable credentialTable = new JTable();
        credentialTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(credentialTable);
        scrollPane.setBounds(300, 20, 550, 300);
        buttonPanel.add(scrollPane);        
        
        


        // Method for exit button
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        // Set up Save button and its location
        final JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(350, 325, 100, 20);
        buttonPanel.add(buttonSave);
                
        

        // Set up Save button method
        buttonSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            
                File filename = new File(userName + ".txt");
                try{
                    FileWriter fw = new FileWriter(filename, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (int i = 0 ; i < model.getRowCount(); i++) {
                        
                        for(int j = 0 ; j < model.getColumnCount();j++)
                        {
                            String b = (model.getValueAt(i, j) + " ");
                            bw.write(b + " ");
                        }
                        bw.newLine();
                    }
                    bw.close();
                }
                catch(Exception ex){
                    
                    ex.printStackTrace();                    
                }
            }});            

        // Set up Load button and its location
        final JButton buttonLoad =  new JButton("Load");
        buttonLoad.setBounds(500, 325, 100, 20);
        buttonPanel.add(buttonLoad);

        // Method for load button
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line;
                BufferedReader reader;
                File fileName = new File(userName + ".txt");
                    try{
                        int key = -5;                     
                        reader = new BufferedReader(new FileReader(userName + ".txt"));
                        while((line = reader.readLine()) != null) 
                        {
                          
                           model.addRow(line.split(" ")); 
                        }
                        reader.close();
                     }
                    catch(Exception ex){

                ex.printStackTrace();

                    }

            }
        });
        // Set up Labels for name, hours, and pay rate
        final JLabel labelWebsite = new JLabel("Enter Website Name: ");
        labelWebsite.setBounds(20, 5, 150, 100);
        buttonPanel.add(labelWebsite);

        final JLabel labelUsername = new JLabel("Enter Username: ");
        labelUsername.setBounds(20, 60, 150, 100);
        buttonPanel.add(labelUsername);

        final JLabel labelPassword = new JLabel("Enter Password");
        labelPassword.setBounds(20, 115, 150, 100);
        buttonPanel.add(labelPassword);



        // Set up textboxes for all expected inputs
        final JTextField textWebsite = new JTextField();
        textWebsite.setBounds(180, 40, 100, 25);
        buttonPanel.add(textWebsite);

        final JTextField textUsername = new JTextField();
        textUsername.setBounds(180, 95, 100, 25);
        buttonPanel.add(textUsername);

        final JTextField textPassword = new JTextField();
        textPassword.setBounds(180, 150, 100, 25);
        buttonPanel.add(textPassword);



        // Save button methods, including validation checking
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textWebsite.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Error: no Website name");
                    return;
                }

                if (textUsername.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Error: no Username");
                    return;
                }


                if (textPassword.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Error: no password ");
                    return;
                }


                // Add an ID number to each entry and add the entry to the table
                ID++;
                model.addRow(new Object[] { String.valueOf(ID),
                        textWebsite.getText(), textUsername.getText(),
                        textPassword.getText(), });


                // Once entry is added to the table, the text fields are cleared for the next entry
                textUsername.setText("");
                textWebsite.setText("");
                textPassword.setText("");

            }
        });    
        
        
        buttonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
                StringBuilder generatedString = new StringBuilder();
                Random rnd = new Random();
                while (generatedString.length() < 12) {
                    int index = (int) (rnd.nextFloat() * CHARS.length());
                    generatedString.append(CHARS.charAt(index));
                }
                String generatedStringStr = generatedString.toString();
                textPassword.setText(generatedStringStr);
            }
        });
        
        
        

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frame.setLocationRelativeTo(null);
    }
    
    public static String encrypt(String text)   {
        
        String s = "";
        try {
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            String key = "Bar12345Bar12345";

            Key myAesKey = new SecretKeySpec(key.getBytes(), "AES");
                   
            Cipher aesCipher;
            aesCipher = Cipher.getInstance("AES");
            byte[] text2 = text.getBytes("UTF8");            
            
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
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
        
                