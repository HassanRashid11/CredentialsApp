/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NavigationUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class LoginUI extends LoginXml {
    
    
    JTextField passwordText; 
    JTextField userText;
    public static String user;
    JFrame loginUI;

    public void buildUI()   {
        
        loginUI = new JFrame("Login");
        loginUI.setSize(400, 150);
        loginUI.setLocationRelativeTo(null);
        loginUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        loginUI.add(panel);
        LoginUI(panel);

        loginUI.setVisible(true); 
        loginUI.setResizable(false);
    }    
    
       

    public void LoginUI(JPanel panel) {
        
        panel.setLayout(null);
        
        JLabel userLabel = new JLabel("User Login");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
        
        userText = new JTextField(20);
        userText.setBounds(200, 10, 160, 25);
        panel.add(userText);
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);
        
        passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 40, 160, 25);
        panel.add(passwordText);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 100, 25);
        panel.add(loginButton);

        
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(280, 80, 100, 25);
        panel.add(registerButton);
        
        loginButton.addActionListener(new ActionListener()   {
        
            @Override
            public void actionPerformed(ActionEvent e)  {
                user = userText.getText().trim();
                String pass = passwordText.getText().trim();
                LoginXml login = new LoginXml();
                login.setUsername(user);
               
                if (login.authenticate2(user, pass))   {
                    DashboardXML dashboard = new DashboardXML();
                    dashboard.Dashboard4();
                    loginUI.dispose();
                    
                    //dashboard.buildUI();
                    //new Dashboard4().setVisible(true);
                } else {                                                        
                    JOptionPane.showMessageDialog(null, "incorrect credentials");
                }
            }
        });
        
        registerButton.addActionListener(new ActionListener()   {
            @Override
            public void actionPerformed(ActionEvent z) {
                RegisterUI reg= new RegisterUI();
                reg.buildRegistry();
            }
            
            
        });
        
    }

}
    