/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Gilsur
 */
public class CKoneksiDB {
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        //cek apakak koneksi null
        if(koneksi == null){
            
            try {
                String url;
                url = "jdbc:mysql://localhost:3306/db_absen_abc";
                String user = "root";
                String password = "";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                
                koneksi = DriverManager.getConnection(url,user,password);
            } catch (SQLException t){
                JOptionPane.showMessageDialog(null, "Eror Membuat Koneksi");
            }
        }
        
        return koneksi;
                
    }

    static Object getConnection() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
