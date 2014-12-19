/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import model.MPegawai;
// import view.Admin;

/**
 *
 * @author Gilsur
 */
public class CManagePegawai {
    PreparedStatement pst;
    ResultSet rs;
    String sql;
    
    public void loadDataPegawai(DefaultTableModel namatabel, String modf) throws SQLException{
          
        namatabel.getDataVector().removeAllElements();
        namatabel.fireTableDataChanged();
        
        if(modf == null){
           sql = "SELECT * FROM tb_pegawai as p, tb_jabatan as j "
                    + "where p.jabatan = j.id_jabatan";
           }else{
            sql = "SELECT * FROM tb_pegawai as p, tb_jabatan as j "
                    + "where p.jabatan = j.id_jabatan or "
                    + "p.nama is like '%?%'";
            
            pst.setString(1, modf.toString());
            
        }
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        
        rs = pst.executeQuery(sql);
            
            while(rs.next()){
                //lakukan penelusuran baris
                Object[] o = new Object[6];
                o[0] = rs.getString("id_pegawai");
                o[1] = rs.getString("nama");
                o[2] = rs.getString("nama_jabatan");
                o[3] = rs.getString("foto");
                o[4] = rs.getString("id_waktu");
                o[5] = rs.getString("PIN");
 
                namatabel.addRow(o);
            }
            rs.close();
            pst.close();
        

    }
    public boolean cekPegawai(MPegawai modp) throws SQLException {
        sql = "SELECT * from tb_pegawai where id_pegawai = ?";
                pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
                pst.setString(1, modp.getIdpegawai());
                rs = pst.executeQuery();

                if(rs.next()){
                   return true;
                }
                else {
            return false;
        }
}
    
    public void updatePegawai(MPegawai modp) throws SQLException {
        
        sql = "UPDATE tb_pegawai SET nama = ?, jabatan = ?, foto=? , id_waktu=?, PIN=? "
                            + "WHERE id_pegawai=?";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        pst.setString(1, modp.getNama());
        pst.setString(2, modp.getJabatan());
        pst.setString(3, modp.getFoto());
        pst.setString(4, modp.getShift());
        pst.setString(5, modp.getPIN());
        pst.setString(6, modp.getIdpegawai());
        pst.executeUpdate();
        pst.close();
    
    }
    public void tambahPegawai(MPegawai modp) throws SQLException {
        sql = "INSERT INTO tb_pegawai values(?, ?, ?, ?, ?, ?)";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        
        pst.setString(1, modp.getIdpegawai());
        pst.setString(2, modp.getNama());
        pst.setString(3, modp.getJabatan());
        pst.setString(4, modp.getFoto());
        pst.setString(5, modp.getShift());
        pst.setString(6, modp.getPIN());
        
        pst.executeUpdate();
        pst.close();
        
    }
    
    public void hapusPegawai(MPegawai modp) throws SQLException {
    
        sql = "DELETE FROM tb_pegawai WHERE id_pegawai=? ";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        pst.setString(1, modp.getIdpegawai());
        
        pst.executeUpdate();
        pst.close();
    
    }
}
