/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.MHadir;

/**
 *
 * @author Gilsur
 */
public class CKehadiran {
    
    MHadir h = new MHadir();
    
    public boolean cek_pegawai(MHadir karyawan) throws SQLException {
        
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
        
    
   public boolean cek_kehadiran(MHadir karyawan) throws SQLException {
        
        String cek = "SELECT * from tb_kehadiran where id_pegawai=? and tanggal=curdate()";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, karyawan.getNIP());
        ResultSet rs;
        rs = pst.executeQuery();
        
        if(rs.next()){
            return false;
        }
        return true;

    }
   
   public String cek_PIN(MHadir karyawan) throws SQLException {
        boolean validasi = false;
        String cek = "SELECT * from tb_pegawai where id_pegawai=?";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, karyawan.getNIP());
        ResultSet rs;
        rs = pst.executeQuery();
        
        if(rs.next()){
            return rs.getString("PIN");
        }
        return rs.getString("PIN");
    }

    public boolean cek_statusMasuk(MHadir karyawan) throws SQLException {
        
        String cek = "SELECT * from tb_kehadiran where id_pegawai=? AND tanggal=CURDATE()";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, karyawan.getNIP());
        
        ResultSet rs;
        rs = pst.executeQuery();
        
        if(rs.next()){
            if(rs.getString("status").equals("Telat (Sedang Bekerja)")){
                return false;
            }
        }
        return true;
    }
   
   public boolean cek_statusBlock(MHadir karyawan) throws SQLException {
        
        String cek = "SELECT * from tb_kehadiran where id_pegawai=? AND tanggal=CURDATE() AND (status=? or status=? or status=? or status=?)";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, karyawan.getNIP());
        pst.setString(2, "BLOCK (ABSEN DATANG)");
        pst.setString(3, "BLOCK + TELAT (ABSEN DATANG)");
        pst.setString(4, "BLOCK (ABSEN PULANG)");
        pst.setString(5, "BLOCK + TELAT (ABSEN PULANG)");
        
        ResultSet rs;
        rs = pst.executeQuery();
        
        if(rs.next()){
            return false;
        }
        return true;
    }
   
   public boolean cek_jamMasuk(MHadir karyawan) throws SQLException {
        
        String cek = "SELECT * from tb_pegawai where id_pegawai=?";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, karyawan.getNIP());
        ResultSet rs;
        rs = pst.executeQuery();
        
        if(rs.next()){
            return false;
        }
        return true;

    }
        
        
    public List<MHadir> ambil(MHadir karyawan) throws SQLException {
        
        String cek = " SELECT tb_pegawai.id_pegawai, tb_pegawai.PIN, tb_pegawai.nama, tb_jabatan.nama_jabatan, "
                + "tb_pegawai.id_waktu, tb_waktu.jam_kerja_mulai, tb_waktu.jam_kerja_selesai, tb_pegawai.foto, "
                + "tb_waktu.jam_masuk_awal, tb_waktu.jam_keluar_selesai "
                + "FROM tb_pegawai, tb_jabatan, tb_waktu WHERE tb_pegawai.id_pegawai = ? "
                + "and tb_waktu.id_waktu = tb_pegawai.id_waktu and tb_pegawai.jabatan = tb_jabatan.id_jabatan "
                + "group by id_pegawai";

        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, karyawan.getNIP());
        ResultSet rs;
        rs = pst.executeQuery();
        List<MHadir> list = new ArrayList<>();
        if(rs.next()){
            h.setPIN(rs.getString("PIN"));
            h.setNama(rs.getString("nama"));
            h.setFoto(rs.getString("foto"));
            h.setId_Jam(rs.getString("id_waktu"));
            h.setJabatan(rs.getString("nama_jabatan"));
            h.setMasukStart(rs.getTime("jam_masuk_awal"));
            h.setKerjaStart(rs.getTime("jam_kerja_mulai"));
            h.setKerjaEnd(rs.getTime("jam_kerja_selesai"));
            h.setKeluarEnd(rs.getTime("jam_keluar_selesai"));
            list.add(h);
        }        
        return list;
    }
    
    
    public void masuk(String nip, String status) throws SQLException {
                   
        String hadir = "INSERT INTO tb_kehadiran(id_pegawai, tanggal, jam_masuk, jam_keluar, id_waktu, status) VALUES( ?, CURDATE(), CURTIME(), ?, ?, ?)";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(hadir);
        pst.setString(1, nip);
        pst.setTime(2, h.getKeluarEnd());
        pst.setString(3, h.getId_Jam());
        pst.setString(4, status);
        pst.executeUpdate();     
        
    }
        
    public void keluar(String nip, String status) throws SQLException {
        
        String cek = "UPDATE tb_kehadiran SET jam_keluar=CURTIME(), status=? WHERE id_pegawai=? and tanggal=curdate()";
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(cek);
        pst.setString(1, status);
        pst.setString(2, nip);    
        pst.executeUpdate();       
        
    }

    public boolean cek_status(String nip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
