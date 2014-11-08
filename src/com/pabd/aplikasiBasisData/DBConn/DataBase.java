/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pabd.aplikasiBasisData.DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class DataBase {

    private static Connection mConnection;

    private String mUserName;
    private String mPassword;
    private String mURL;


    private static final DataBase INSTANCE = new DataBase();

    public DataBase() {
    }

    public void login(String username, String password, String url) {
        
        username = "mhs125314109";
        password = "mhs125314109";
        
        // Bagian ini disesuaikan url tiap mesin
//        String jdbcURL = "jdbc:oracle:thin:@"+url+":1521:xe";
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
        
        this.mURL = jdbcURL;
        this.mUserName = username;
        this.mPassword = password;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.mConnection = DriverManager.getConnection(jdbcURL, mUserName, mPassword);            
            JOptionPane.showMessageDialog(null, "Selamat datang "+username+"!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Logon denied!");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Invalid username/password!");
        }
    }

    public static Connection getConnection() {
        return mConnection;
    }

    public static DataBase getInstance() {
        return INSTANCE;
    }

}
