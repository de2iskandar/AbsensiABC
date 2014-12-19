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

/**
 *
 * @author Gilsur
 */
public class CManageDasbor {
    PreparedStatement pst;
    ResultSet rs;
    String sql;
    
    public void loadDataDasbor(DefaultTableModel namatabel) throws SQLException{

        namatabel.getDataVector().removeAllElements();
        namatabel.fireTableDataChanged();
            
        sql = "SELECT tb_kehadiran.tanggal, tb_pegawai.nama, tb_kehadiran.status, tb_jabatan.nama_jabatan, tb_kehadiran.jam_masuk, tb_kehadiran.jam_keluar "
                    + "FROM tb_pegawai "
                    + "INNER JOIN tb_jabatan ON tb_pegawai.jabatan=tb_jabatan.id_jabatan "
                    + "INNER JOIN tb_kehadiran ON tb_pegawai.id_pegawai=tb_kehadiran.id_pegawai "
                    + "GROUP BY tb_pegawai.id_pegawai "
                    + "ORDER BY tb_kehadiran.tanggal desc;";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        
        rs = pst.executeQuery(sql);
            
            while(rs.next()){
                //lakukan penelusuran baris
                Object[] o = new Object[6];
                o[0] = rs.getDate("tanggal");
                o[1] = rs.getString("nama");
                o[2] = rs.getString("nama_jabatan");
                o[3] = rs.getTime("jam_masuk");
                o[4] = rs.getTime("jam_keluar");
                o[5] = rs.getString("status");
                
                namatabel.addRow(o);
            }
            rs.close();
            pst.close();

     } 
    
    public int getJumlahPegawai() throws SQLException{
        sql = "SELECT Count(*) as jumlah_pegawai "
                + "from tb_pegawai;";
        
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        rs = pst.executeQuery(sql);
        
        int jumlah;
            while(rs.next()){
                jumlah = rs.getInt("jumlah_pegawai");
                return jumlah;
            }
            return 0;
    }
    
    public int getTotalHadir() throws SQLException{
        sql = "SELECT count(*) as total_hadir "
                + "from tb_kehadiran;";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        rs = pst.executeQuery(sql);
        
        int jumlah_hadir;
            while(rs.next()){
                jumlah_hadir = rs.getInt("total_hadir");
                return jumlah_hadir;
            }
            return 0;
    }
    public int getTotalHari() throws SQLException{
        sql = "SELECT tanggal as tanggal_kerja "
                + "from tb_kehadiran "
                + "group by tanggal;";
        pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        rs = pst.executeQuery(sql);
        
        int jumlah_hari = 0;
            while(rs.next()){
                jumlah_hari +=1;                   
            }
            return jumlah_hari; 
    }
}
