/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MPresensi;

/**
 *
 * @author Gilsur
 */
public class CManagePresensi {
    public boolean cek_pegawai(MPresensi karyawan) throws SQLException {
        
        String cek = "SELECT * from tb_pegawai where id_pegawai = ?";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, karyawan.getNIP());
        ResultSet rs;
        rs = pst.executeQuery();
        
        if(rs.next()){
            return true;
        }
        return false;
        
    }
    
        public void masuk(String nip) throws SQLException {
        
            
        String hadir = "INSERT INTO tb_kehadiran VALUES( ?, CURDATE(), CURTIME(), 0)";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(hadir);
        pst.setString(1, nip);
        pst.executeUpdate();     
        
    }
        
        public void keluar(String nip) throws SQLException {
        
        String cek = "UPDATE tb_kehadiran SET jam_keluar=CURTIME() WHERE id_pegawai=? AND tanggal=CURDATE()";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, nip);       
        pst.executeUpdate();       
        
    }
}
