/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import model.MAbsensi;
import model.MPegawai;

/**
 *
 * @author Gilsur
 */
public class CManageAbsensi {
    PreparedStatement pst;
    ResultSet rs;
    String sql;
    
    public void loadDataAbsen(DefaultTableModel namatabel) throws SQLException{
        
        namatabel.getDataVector().removeAllElements();
        namatabel.fireTableDataChanged();
    
        sql = "SELECT tb_absen.no, tb_absen.tanggal, tb_absen.id_pegawai, tb_pegawai.nama, tb_pegawai.jabatan, tb_absen.izin, tb_absen.keterangan, tb_pegawai.foto "
                + "FROM tb_absen INNER JOIN tb_pegawai where tb_absen.id_pegawai=tb_pegawai.id_pegawai "
                + "ORDER BY tb_absen.no desc;";
        
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        rs = pst.executeQuery(sql);
            
            while(rs.next()){
                //lakukan penelusuran baris
                Object[] o = new Object[8];
                o[0] = rs.getInt("no");
                o[1] = rs.getDate("tanggal");
                o[2] = rs.getString("id_pegawai");
                o[3] = rs.getString("nama");
                o[4] = rs.getString("jabatan");
                o[5] = rs.getString("izin");
                o[6] = rs.getString("keterangan");
                o[7] = rs.getString("foto");
 
                namatabel.addRow(o);
            }
            rs.close();
            pst.close();

     }
    
    
    public boolean cekPegawai(MAbsensi moda) throws SQLException {
        sql = "SELECT * from tb_pegawai where id_pegawai = ?";
                pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
                pst.setString(1, moda.getNip());
                rs = pst.executeQuery();

                if(rs.next()){
                   return true;
                }
                else {
            return false;
        }
}
    
    public boolean cekDuplikasi(MAbsensi moda) throws SQLException {
        sql = "SELECT * from tb_absen where id_pegawai = ? and tanggal = ?";
                pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
                pst.setString(1, moda.getNip());
                pst.setString(2, moda.getTanggal());
                rs = pst.executeQuery();

                if(rs.next()){
                   return true;
                }
                else {
            return false;
        }
}
    
    public void ubahAbsen(MAbsensi moda) throws SQLException {
        
        sql = "UPDATE tb_absen SET id_pegawai = ?, tanggal = ?, izin=?, keterangan=?,  "
                            + "WHERE no=?";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        pst.setString(1, moda.getNip());
        pst.setDate(2, java.sql.Date.valueOf(moda.getTanggal()));
        pst.setString(3, moda.getAbsen());
        pst.setString(4, moda.getKeterangan());
        pst.setInt(5, moda.getNo());
        

        pst.executeUpdate();
        pst.close();
    
    }
    public void tambahAbsen(MAbsensi moda) throws SQLException {
        sql = "INSERT INTO tb_absen (id_pegawai, izin, tanggal, keterangan) VALUES( ?, ?, ?, ?)";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        pst.setString(1, moda.getNip());
        pst.setString(2, moda.getAbsen());
        pst.setDate(3, java.sql.Date.valueOf(moda.getTanggal()));
        pst.setString(4, moda.getKeterangan());

        pst.executeUpdate();
        pst.close();
        
    }
    
    public void hapusPegawai(MAbsensi moda) throws SQLException {
    
        sql = "DELETE FROM tb_absen where no=?";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        pst.setInt(1, moda.getNo());

        pst.executeUpdate();
        pst.close();
    }
}
