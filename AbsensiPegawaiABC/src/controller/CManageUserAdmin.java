/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MLogin;
/**
 *
 * @author Gilsur
 */
public class CManageUserAdmin {
    /**
     *
     * @param usr
     * @retursn
     * @throws SQLException
     */
    public boolean cekLogin(MLogin usr) throws SQLException {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        pst.setString(1, usr.getUsername());
        pst.setString(2, usr.getPassword());
        ResultSet rs;
        rs = pst.executeQuery();
        if (rs.next()) { 
            return true;
        }
        return false;
    }
}
