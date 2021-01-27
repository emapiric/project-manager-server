/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import projectmanager.view.form.FrmSettings;

/**
 *
 * @author EMA
 */
public class SettingsController {
    FrmSettings frmSettings;
    
     public SettingsController(FrmSettings frmSettings) {
        this.frmSettings = frmSettings;
        addActionListeners();
    }

    public void openForm() {
        prepareView();
        frmSettings.setVisible(true);
    }

    private void addActionListeners() {
        frmSettings.btnSaveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = frmSettings.getTxtUrl().getText().trim();
                String password = frmSettings.getTxtPassword().getText().trim();
                String username = frmSettings.getTxtUsername().getText().trim();
                FileOutputStream out = null;
                try{
                    out = new FileOutputStream("config/dbconfig.properties");
                    FileInputStream in = new FileInputStream("config/dbconfig.properties");
                    Properties props = new Properties();
                    props.load(in);
                    in.close();
                    props.setProperty("url", url);
                    props.setProperty("username", username);
                    props.setProperty("password", password);
                    props.store(out, null);
                    out.close();
                    JOptionPane.showMessageDialog(frmSettings, "Database configuration added succeffully!");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(frmSettings, "Error: "+ex.getMessage());
                    try {
                        out.close();
                    } catch (IOException ex1) {
                        Logger.getLogger(FrmSettings.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void prepareView() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            frmSettings.getTxtUrl().setText(properties.getProperty("url"));
            frmSettings.getTxtUsername().setText(properties.getProperty("username"));
            frmSettings.getTxtPassword().setText(properties.getProperty("password"));
        } catch (Exception ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
