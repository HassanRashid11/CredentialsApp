/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NavigationUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 *
 */
public class DashboardXML extends LoginUI {
    
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 400;
    static JTable tableCredential;
    static int ID = 0;
    String user;
    final String userName = LoginUI.user;  
    private static byte[] key;
    private static SecretKeySpec secretKey;    
    
    public void Dashboard4() {
        initComponents();
        
    }
    
    
    public void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
    }    
    
    public String encryptS(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decryptS(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
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
            String key_static="Hassan99993421!@";   
            try {	
                String filepath = ("C:\\Users\\Hassan\\Documents\\GitHub\\credentialApp\\test.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(filepath);
                doc.getDocumentElement().normalize();

                NodeList n = doc.getElementsByTagName("Account");
                for (int i = 0; i < n.getLength(); i++)   {
                    Element x = (Element) n.item(i);
                    if (x.getAttribute("id").equals(userName))   {

                    for (int q = 0 ; q < model.getRowCount(); q++) {
                                               
                            String a = encryptS((model.getValueAt(q, 0) + " "), key_static);
                            String b = encryptS((model.getValueAt(q, 1) + " "), key_static);
                            String c = encryptS((model.getValueAt(q, 2) + " "), key_static);
                            String d = encryptS((model.getValueAt(q, 3) + " "), key_static);
                            //ID, website, username, password
                            Element credential = doc.createElement("Credential");
                            x.appendChild(credential);                           
                            Attr number = doc.createAttribute("Number");
                            number.setValue(a);
                            credential.setAttributeNode(number);
                            Attr website = doc.createAttribute("Website");
                            website.setValue(b);
                            credential.setAttributeNode(website);
                            Attr username = doc.createAttribute("Username");
                            username.setValue(c);
                            credential.setAttributeNode(username);
                            Attr password = doc.createAttribute("Password");
                            password.setValue(d);
                            credential.setAttributeNode(password);                                   
                    }
                        }
                }
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("test.xml"));
                transformer.transform(source, result);                   
                
            }catch(ParserConfigurationException | SAXException | IOException | DOMException | TransformerException ex) {
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
                    String key_static="Hassan99993421!@";
                    try{
                        String filepath = ("C:\\Users\\Hassan\\Documents\\GitHub\\credentialApp\\test.xml");
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(filepath);
                        doc.getDocumentElement().normalize();

                        NodeList n = doc.getElementsByTagName("Account");
                        for (int i = 0; i < n.getLength(); i++)   {
                            Element x = (Element) n.item(i);    
                            if (x.getAttribute("id").equals(userName))   {
                                NodeList two = x.getElementsByTagName("Credential");
                                for (i = 0; i < two.getLength(); i++)   {
                                    Element b = (Element) two.item(i);                                      
                                    String num = decryptS(b.getAttribute("Number"), key_static);
                                    String web = decryptS(b.getAttribute("Website"), key_static);
                                    String us = decryptS(b.getAttribute("Username"), key_static);
                                    String pa = decryptS(b.getAttribute("Password"), key_static);
                                    String loadedRow = num + web + us + pa;
                                    model.addRow(loadedRow.split(" "));
                                }    
                            }        
                        }                           
                    }
                    catch(ParserConfigurationException | SAXException | IOException ex)   {
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
                ID = model.getRowCount() + 1;
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
        frame.setResizable(false);
    }   

}
        
                