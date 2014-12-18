package view;


import controller.CKoneksiDB;
import controller.CManageAbsensi;
import controller.CManageDasbor;
import controller.CManagePegawai;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import model.MAbsensi;
import model.MFilter;
import model.MPegawai;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/** Jasper report 
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
**/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dendi
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    DefaultTableModel tabelpegawai;
    public void loadTblPegawai() throws SQLException{
        CManagePegawai mptl = new CManagePegawai();
        mptl.loadDataPegawai(tabelpegawai, null);
        
    }
    
    DefaultTableModel tabeldasbor;
    public void loadTblDasbor() throws SQLException{
        CManageDasbor mdtl = new CManageDasbor();
        mdtl.loadDataDasbor(tabeldasbor);
        try {
            int pegawai = mdtl.getJumlahPegawai();
            Dasbor_jumlah_pegawai.setText(String.valueOf(pegawai));
            
            int hadir = mdtl.getTotalHadir();
            int hari = mdtl.getTotalHari();
            
            double hadir1 = hadir;
            double hari1 = hari;
            double persentase = hadir1 / hari1;
            double persentase2 = persentase / pegawai;
            
            double persentase3 = persentase2 * 100;
            DecimalFormat df = new DecimalFormat("##,##");
                        
            Dasbor_persentase_kehadiran.setText(String.valueOf(df.format(persentase3))+" %");
                    
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    DefaultTableModel tabelabsen;
    public void loadTblAbsen() throws SQLException{
        CManageAbsensi matl = new CManageAbsensi();
        matl.loadDataAbsen(tabelabsen);
    }
    
    
    public Admin() throws SQLException {
        initComponents();
        //inisialisasi combo jabatan
        cmbJabatan.addItem("Pilih jabatan");
        
        //Agar memulai tampilan aplikasi berada di tengah layar
        Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
        Dimension frameSize = new Dimension ( 760, 513 );
        this.setBounds ( ss.width / 2 - frameSize.width / 2, 
                  ss.height / 2 - frameSize.height / 2,
                  frameSize.width, frameSize.height );

        //Membuat TableModel Pegawai
        tabelpegawai = new DefaultTableModel();
        //menambahkan TableModel ke Tabel pegawai
        jtabelpegawai.setModel(tabelpegawai);        
        tabelpegawai.addColumn("ID Pegawai");
        tabelpegawai.addColumn("Nama");
        tabelpegawai.addColumn("Jabatan");
        tabelpegawai.addColumn("Foto");
        tabelpegawai.addColumn("Shift");
        tabelpegawai.addColumn("PIN");
        
        //Membuat TableModel Dasbor
        tabeldasbor = new DefaultTableModel();
        
        //menambahkan TableModel ke Tabel
        jtabelDasbor.setModel(tabeldasbor);        
        tabeldasbor.addColumn("Tanggal");
        tabeldasbor.addColumn("Nama Pegawai");
        tabeldasbor.addColumn("Jabatan");
        tabeldasbor.addColumn("Masuk");
        tabeldasbor.addColumn("Keluar");
        tabeldasbor.addColumn("status");
        
        //Membuat TableModel Izin
        tabelabsen = new DefaultTableModel();
        
        //menambahkan TableModel ke Tabel
        jtabelabsen.setModel(tabelabsen);
        tabelabsen.addColumn("No");
        tabelabsen.addColumn("Tanggal");
        tabelabsen.addColumn("NIP");
        tabelabsen.addColumn("Nama");
        tabelabsen.addColumn("Jabatan");
        tabelabsen.addColumn("Izin/Cuti");
        tabelabsen.addColumn("Keterangan");
        tabelabsen.addColumn("Foto");
        
        
        //memanggil method tabel-tabel agar terisi dgn database
        loadTblPegawai();
        loadTblDasbor();
        loadJabatan();
        loadTblAbsen();
    }
    
    // Controller SQL mengambil langsung isi tabel jabatan di sql
    
    public void loadJabatan() throws SQLException {
        String sql = "SELECT * FROM tb_jabatan";    
        PreparedStatement pst = CKoneksiDB.getKoneksi().prepareStatement(sql);
        ResultSet rs;
        rs = pst.executeQuery(sql);
        while(rs.next()){           
            cmbJabatan.addItem(rs.getString("nama_jabatan"));
        }
    }

    public void runReportDefault(String sourcefilename, HashMap hash) {
        Connection con = CKoneksiDB.getKoneksi();
        try {
            InputStream report;
            report = getClass().getResourceAsStream(sourcefilename);
            JasperPrint jprint = JasperFillManager.fillReport(report,hash, con);
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jenis_absen = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panel_Laporan_pegawai = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Dasbor_jumlah_pegawai = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Dasbor_persentase_kehadiran = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabelDasbor = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_izin = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtabelabsen = new javax.swing.JTable();
        txtAbsen_keterangan = new javax.swing.JTextField();
        btnAbsen_simpan = new javax.swing.JButton();
        radAbsen_cuti = new javax.swing.JRadioButton();
        radAbsen_izin = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        btnAbsen_ubah = new javax.swing.JButton();
        btnAbsen_tambah = new javax.swing.JButton();
        btnAbsen_hapus = new javax.swing.JButton();
        txtAbsen_tanggal = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtAbsen_nip = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        img_foto_absen = new javax.swing.JLabel();
        txtAbsen_no = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        panel_Pegawai = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btn_tambahpegawai = new javax.swing.JButton();
        btn_ubahdata = new javax.swing.JButton();
        btn_hapusdata = new javax.swing.JButton();
        btn_simpandata = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtabelpegawai = new javax.swing.JTable();
        txt_IDPegawai = new javax.swing.JTextField();
        txt_AddNamaPegawai = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        cmbJabatan = new javax.swing.JComboBox();
        img_foto = new javax.swing.JLabel();
        panel_Pegawai1 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        btn_tambahpegawai1 = new javax.swing.JButton();
        btn_ubahdata1 = new javax.swing.JButton();
        btn_hapusdata1 = new javax.swing.JButton();
        btn_simpandata1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtabelpegawai1 = new javax.swing.JTable();
        txt_IDPegawai1 = new javax.swing.JTextField();
        txt_AddNamaPegawai1 = new javax.swing.JTextField();
        txt_foto = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        cmbJabatan1 = new javax.swing.JComboBox();
        img_foto1 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txt_PIN = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        Shift = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        panel_Laporan_Harian = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jcal_tanggalhadir = new com.toedter.calendar.JCalendar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrasi Pegawai - PT.ABC Bandung");
        setBackground(new java.awt.Color(204, 204, 204));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jumlah Karyawan");

        Dasbor_jumlah_pegawai.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        Dasbor_jumlah_pegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Dasbor_jumlah_pegawai.setText("2759");
        Dasbor_jumlah_pegawai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setText("Persentase Kehadiran");

        Dasbor_persentase_kehadiran.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        Dasbor_persentase_kehadiran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Dasbor_persentase_kehadiran.setText("100,00 %");
        Dasbor_persentase_kehadiran.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtabelDasbor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtabelDasbor);

        jLabel28.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel28.setText("Dashboard");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customers.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel2.setText("Orang");

        javax.swing.GroupLayout panel_Laporan_pegawaiLayout = new javax.swing.GroupLayout(panel_Laporan_pegawai);
        panel_Laporan_pegawai.setLayout(panel_Laporan_pegawaiLayout);
        panel_Laporan_pegawaiLayout.setHorizontalGroup(
            panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                                .addGroup(panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Dasbor_jumlah_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                                .addGroup(panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(Dasbor_persentase_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(jLabel2)))
                                .addGap(0, 24, Short.MAX_VALUE))))
                    .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addContainerGap(597, Short.MAX_VALUE))))
        );
        panel_Laporan_pegawaiLayout.setVerticalGroup(
            panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(panel_Laporan_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_Laporan_pegawaiLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Dasbor_persentase_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Dasbor_jumlah_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))))
        );

        jPanel1.add(panel_Laporan_pegawai, "card5");

        panel_izin.setBackground(new java.awt.Color(204, 204, 204));
        panel_izin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel_izin.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 108, -1, -1));

        jtabelabsen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "NIP", "Nama", "Jabatan", "Cuti/Izin", "Keterangan", "Foto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelabsen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabelabsenMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jtabelabsen);
        if (jtabelabsen.getColumnModel().getColumnCount() > 0) {
            jtabelabsen.getColumnModel().getColumn(0).setResizable(false);
            jtabelabsen.getColumnModel().getColumn(2).setResizable(false);
            jtabelabsen.getColumnModel().getColumn(3).setResizable(false);
            jtabelabsen.getColumnModel().getColumn(4).setResizable(false);
            jtabelabsen.getColumnModel().getColumn(5).setResizable(false);
            jtabelabsen.getColumnModel().getColumn(6).setResizable(false);
        }

        panel_izin.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 730, 230));

        txtAbsen_keterangan.setBackground(new java.awt.Color(249, 249, 249));
        txtAbsen_keterangan.setEnabled(false);
        txtAbsen_keterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbsen_keteranganActionPerformed(evt);
            }
        });
        panel_izin.add(txtAbsen_keterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 180, 36));

        btnAbsen_simpan.setText("Simpan");
        btnAbsen_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbsen_simpanActionPerformed(evt);
            }
        });
        panel_izin.add(btnAbsen_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 100, 40));

        radAbsen_cuti.setBackground(new java.awt.Color(204, 204, 204));
        jenis_absen.add(radAbsen_cuti);
        radAbsen_cuti.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        radAbsen_cuti.setText("Cuti");
        radAbsen_cuti.setEnabled(false);
        panel_izin.add(radAbsen_cuti, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        radAbsen_izin.setBackground(new java.awt.Color(204, 204, 204));
        jenis_absen.add(radAbsen_izin);
        radAbsen_izin.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        radAbsen_izin.setText("Izin");
        radAbsen_izin.setEnabled(false);
        radAbsen_izin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radAbsen_izinActionPerformed(evt);
            }
        });
        panel_izin.add(radAbsen_izin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));
        panel_izin.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 108, -1, -1));

        btnAbsen_ubah.setText("Ubah");
        btnAbsen_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbsen_ubahActionPerformed(evt);
            }
        });
        panel_izin.add(btnAbsen_ubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 70, -1));

        btnAbsen_tambah.setText("Tambah");
        btnAbsen_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbsen_tambahActionPerformed(evt);
            }
        });
        panel_izin.add(btnAbsen_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, -1));

        btnAbsen_hapus.setText("Hapus");
        btnAbsen_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbsen_hapusActionPerformed(evt);
            }
        });
        panel_izin.add(btnAbsen_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 70, -1));

        txtAbsen_tanggal.setBackground(new java.awt.Color(249, 249, 249));
        txtAbsen_tanggal.setToolTipText("");
        txtAbsen_tanggal.setDateFormatString("yyyy-MM-d");
        txtAbsen_tanggal.setEnabled(false);
        txtAbsen_tanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtAbsen_tanggalPropertyChange(evt);
            }
        });
        panel_izin.add(txtAbsen_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 180, -1));

        jLabel32.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel32.setText("Keterangan :");
        panel_izin.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, -1, 30));

        jLabel33.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel33.setText("NIP :");
        panel_izin.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, 30));

        jLabel34.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel34.setText("Tanggal :");
        panel_izin.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, 30));

        txtAbsen_nip.setBackground(new java.awt.Color(249, 249, 249));
        txtAbsen_nip.setEnabled(false);
        txtAbsen_nip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbsen_nipActionPerformed(evt);
            }
        });
        panel_izin.add(txtAbsen_nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 180, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel27.setText("Absensi");
        panel_izin.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel35.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel35.setText("Jenis :");
        panel_izin.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, 30));

        img_foto_absen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_foto_absen.setText("foto");
        panel_izin.add(img_foto_absen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 180, 160));

        txtAbsen_no.setBackground(new java.awt.Color(249, 249, 249));
        txtAbsen_no.setEnabled(false);
        txtAbsen_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbsen_noActionPerformed(evt);
            }
        });
        panel_izin.add(txtAbsen_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 40, 30));

        jLabel6.setText("No_Record");
        panel_izin.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, 20));

        jPanel1.add(panel_izin, "card11");

        panel_Pegawai.setBackground(new java.awt.Color(204, 204, 204));
        panel_Pegawai.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        panel_Pegawai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel20.setText("ID Pegawai");
        panel_Pegawai.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel7.setText("Manajemen Pegawai");
        panel_Pegawai.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel21.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel21.setText("Nama ");
        panel_Pegawai.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, 30));

        jLabel22.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel22.setText("Jabatan");
        panel_Pegawai.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, 30));

        btn_tambahpegawai.setBackground(new java.awt.Color(204, 204, 204));
        btn_tambahpegawai.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_tambahpegawai.setForeground(new java.awt.Color(51, 51, 51));
        btn_tambahpegawai.setText("Tambah Pegawai");
        btn_tambahpegawai.setToolTipText("Menambah Pegawai");
        btn_tambahpegawai.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_tambahpegawai.setContentAreaFilled(false);
        btn_tambahpegawai.setFocusPainted(false);
        btn_tambahpegawai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_tambahpegawai.setRequestFocusEnabled(false);
        btn_tambahpegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahpegawaiActionPerformed(evt);
            }
        });
        panel_Pegawai.add(btn_tambahpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 140, -1));

        btn_ubahdata.setBackground(new java.awt.Color(204, 204, 204));
        btn_ubahdata.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_ubahdata.setForeground(new java.awt.Color(51, 51, 51));
        btn_ubahdata.setText("Ubah Data");
        btn_ubahdata.setToolTipText("Mengubah data yang terseleksi");
        btn_ubahdata.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_ubahdata.setContentAreaFilled(false);
        btn_ubahdata.setFocusPainted(false);
        btn_ubahdata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_ubahdata.setRequestFocusEnabled(false);
        btn_ubahdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahdataActionPerformed(evt);
            }
        });
        panel_Pegawai.add(btn_ubahdata, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 140, -1));

        btn_hapusdata.setBackground(new java.awt.Color(204, 204, 204));
        btn_hapusdata.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_hapusdata.setForeground(new java.awt.Color(51, 51, 51));
        btn_hapusdata.setText("Hapus Data");
        btn_hapusdata.setToolTipText("Menghapus data yang terseleksi");
        btn_hapusdata.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_hapusdata.setContentAreaFilled(false);
        btn_hapusdata.setFocusPainted(false);
        btn_hapusdata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_hapusdata.setRequestFocusEnabled(false);
        btn_hapusdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusdataActionPerformed(evt);
            }
        });
        panel_Pegawai.add(btn_hapusdata, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 140, -1));

        btn_simpandata.setBackground(new java.awt.Color(204, 204, 204));
        btn_simpandata.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_simpandata.setForeground(new java.awt.Color(51, 51, 51));
        btn_simpandata.setText("Simpan Data");
        btn_simpandata.setToolTipText("Menyimpan data baru atau perubahan");
        btn_simpandata.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_simpandata.setContentAreaFilled(false);
        btn_simpandata.setFocusPainted(false);
        btn_simpandata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_simpandata.setRequestFocusEnabled(false);
        btn_simpandata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpandataActionPerformed(evt);
            }
        });
        panel_Pegawai.add(btn_simpandata, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 140, -1));

        jtabelpegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pegawai", "Nama", "Jabatan", "Foto", "PIN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelpegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabelpegawaiMouseClicked(evt);
            }
        });
        jtabelpegawai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtabelpegawaiFocusGained(evt);
            }
        });
        jScrollPane7.setViewportView(jtabelpegawai);

        panel_Pegawai.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 307, 730, 150));

        txt_IDPegawai.setBackground(new java.awt.Color(249, 249, 249));
        txt_IDPegawai.setEnabled(false);
        txt_IDPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IDPegawaiActionPerformed(evt);
            }
        });
        panel_Pegawai.add(txt_IDPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 180, 30));

        txt_AddNamaPegawai.setBackground(new java.awt.Color(249, 249, 249));
        txt_AddNamaPegawai.setEnabled(false);
        panel_Pegawai.add(txt_AddNamaPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 230, 30));
        panel_Pegawai.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cmbJabatan.setEnabled(false);
        cmbJabatan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJabatanItemStateChanged(evt);
            }
        });
        panel_Pegawai.add(cmbJabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 180, 30));

        img_foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_foto.setText("foto");
        panel_Pegawai.add(img_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 160));

        panel_Pegawai1.setBackground(new java.awt.Color(204, 204, 204));
        panel_Pegawai1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        panel_Pegawai1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel37.setText("ID Pegawai");
        panel_Pegawai1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel8.setText("Manajemen Pegawai");
        panel_Pegawai1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel38.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel38.setText("Nama ");
        panel_Pegawai1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, 30));

        jLabel39.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel39.setText("Jabatan");
        panel_Pegawai1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, 30));

        btn_tambahpegawai1.setBackground(new java.awt.Color(204, 204, 204));
        btn_tambahpegawai1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_tambahpegawai1.setForeground(new java.awt.Color(51, 51, 51));
        btn_tambahpegawai1.setText("Tambah Pegawai");
        btn_tambahpegawai1.setToolTipText("Menambah Pegawai");
        btn_tambahpegawai1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_tambahpegawai1.setContentAreaFilled(false);
        btn_tambahpegawai1.setFocusPainted(false);
        btn_tambahpegawai1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_tambahpegawai1.setRequestFocusEnabled(false);
        btn_tambahpegawai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahpegawai1ActionPerformed(evt);
            }
        });
        panel_Pegawai1.add(btn_tambahpegawai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 140, -1));

        btn_ubahdata1.setBackground(new java.awt.Color(204, 204, 204));
        btn_ubahdata1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_ubahdata1.setForeground(new java.awt.Color(51, 51, 51));
        btn_ubahdata1.setText("Ubah Data");
        btn_ubahdata1.setToolTipText("Mengubah data yang terseleksi");
        btn_ubahdata1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_ubahdata1.setContentAreaFilled(false);
        btn_ubahdata1.setFocusPainted(false);
        btn_ubahdata1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_ubahdata1.setRequestFocusEnabled(false);
        btn_ubahdata1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahdata1ActionPerformed(evt);
            }
        });
        panel_Pegawai1.add(btn_ubahdata1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 140, -1));

        btn_hapusdata1.setBackground(new java.awt.Color(204, 204, 204));
        btn_hapusdata1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_hapusdata1.setForeground(new java.awt.Color(51, 51, 51));
        btn_hapusdata1.setText("Hapus Data");
        btn_hapusdata1.setToolTipText("Menghapus data yang terseleksi");
        btn_hapusdata1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_hapusdata1.setContentAreaFilled(false);
        btn_hapusdata1.setFocusPainted(false);
        btn_hapusdata1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_hapusdata1.setRequestFocusEnabled(false);
        btn_hapusdata1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusdata1ActionPerformed(evt);
            }
        });
        panel_Pegawai1.add(btn_hapusdata1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 140, -1));

        btn_simpandata1.setBackground(new java.awt.Color(204, 204, 204));
        btn_simpandata1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btn_simpandata1.setForeground(new java.awt.Color(51, 51, 51));
        btn_simpandata1.setText("Simpan Data");
        btn_simpandata1.setToolTipText("Menyimpan data baru atau perubahan");
        btn_simpandata1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.darkGray));
        btn_simpandata1.setContentAreaFilled(false);
        btn_simpandata1.setFocusPainted(false);
        btn_simpandata1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_simpandata1.setRequestFocusEnabled(false);
        btn_simpandata1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpandata1ActionPerformed(evt);
            }
        });
        panel_Pegawai1.add(btn_simpandata1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 140, -1));

        jtabelpegawai1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pegawai", "Nama", "Jabatan", "Foto", "PIN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelpegawai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabelpegawai1MouseClicked(evt);
            }
        });
        jtabelpegawai1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtabelpegawai1FocusGained(evt);
            }
        });
        jScrollPane8.setViewportView(jtabelpegawai1);

        panel_Pegawai1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 307, 730, 150));

        txt_IDPegawai1.setBackground(new java.awt.Color(249, 249, 249));
        txt_IDPegawai1.setEnabled(false);
        txt_IDPegawai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IDPegawai1ActionPerformed(evt);
            }
        });
        panel_Pegawai1.add(txt_IDPegawai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 180, 30));

        txt_AddNamaPegawai1.setBackground(new java.awt.Color(249, 249, 249));
        txt_AddNamaPegawai1.setEnabled(false);
        panel_Pegawai1.add(txt_AddNamaPegawai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 230, 30));

        txt_foto.setBackground(new java.awt.Color(249, 249, 249));
        txt_foto.setEnabled(false);
        txt_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fotoActionPerformed(evt);
            }
        });
        panel_Pegawai1.add(txt_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 190, 30));
        panel_Pegawai1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cmbJabatan1.setEnabled(false);
        cmbJabatan1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJabatan1ItemStateChanged(evt);
            }
        });
        panel_Pegawai1.add(cmbJabatan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 180, 30));

        img_foto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_foto1.setText("foto");
        panel_Pegawai1.add(img_foto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 160));

        jLabel42.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel42.setText("Foto");
        panel_Pegawai1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, -1, 30));

        txt_PIN.setBackground(new java.awt.Color(249, 249, 249));
        txt_PIN.setEnabled(false);
        panel_Pegawai1.add(txt_PIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 190, 30));

        jLabel36.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel36.setText("PIN");
        panel_Pegawai1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, -1, 30));

        Shift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "D" }));
        Shift.setEnabled(false);
        Shift.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShiftItemStateChanged(evt);
            }
        });
        Shift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShiftActionPerformed(evt);
            }
        });
        panel_Pegawai1.add(Shift, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 50, 30));

        jLabel23.setFont(new java.awt.Font("Lucida G", 0, 13)); // NOI18N
        jLabel23.setText("Jam Kerja");
        panel_Pegawai1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, 30));

        panel_Pegawai.add(panel_Pegawai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(panel_Pegawai, "card10");

        panel_Laporan_Harian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel30.setText("Data Absensi Pegawai Harian");
        panel_Laporan_Harian.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 24, -1, -1));
        panel_Laporan_Harian.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 24, -1, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton4.setText("Buka Laporan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panel_Laporan_Harian.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 181, 31));
        panel_Laporan_Harian.add(jcal_tanggalhadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 85, -1, -1));

        jPanel1.add(panel_Laporan_Harian, "card2");

        jMenu1.setText("File");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Pegawai");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Keluar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Absensi");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Izin/Cuti");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Laporan");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Keseluruhan");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Harian");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        
        
        
        //Pengaturan panel yang tampil
        panel_Laporan_pegawai.setVisible(true);
        panel_Laporan_Harian.setVisible(false);
        panel_Pegawai.setVisible(false);
        panel_izin.setVisible(false);
        
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //Pengaturan panel yang tampil
        panel_Laporan_Harian.setVisible(true);
        panel_Laporan_pegawai.setVisible(false);
        panel_Pegawai.setVisible(false);
        panel_izin.setVisible(false);
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        
        //Pengaturan panel yang tampil
        panel_Pegawai.setVisible(true);
        panel_Laporan_pegawai.setVisible(false);
        panel_Laporan_Harian.setVisible(false);
        panel_izin.setVisible(false);
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cmbJabatanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJabatanItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbJabatanItemStateChanged

    private void txt_IDPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IDPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IDPegawaiActionPerformed

    private void jtabelpegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabelpegawaiMouseClicked
        // TODO add your handling code here:

        //ubah keadaan field-field menjadi tidak berfungsi
        txt_IDPegawai.setEnabled(false);
        txt_AddNamaPegawai.setEnabled(false);
        cmbJabatan.setEnabled(false);
        txt_PIN.setEnabled(false);
        txt_foto.setEnabled(false);
        Shift.setEnabled(false);
       
        
        //cek apakah salah satu baris sudah diklik
        int i = jtabelpegawai.getSelectedRow();
        if(i== -1){
            //tidak melakukan apa-apa
            return;
        }
        
        /*
          mengambil isi dari tabel yang di klik, lalu menampilkannya
          * di field-field
        */
        String nip = (String) tabelpegawai.getValueAt(i, 0);
        txt_IDPegawai.setText(nip);

        String nama = (String) tabelpegawai.getValueAt(i, 1);
        txt_AddNamaPegawai.setText(nama);

        Object jabatan = (Object) tabelpegawai.getValueAt(i, 2);
        cmbJabatan.setSelectedItem(jabatan);

        String foto = (String) tabelpegawai.getValueAt(i, 3);
        txt_foto.setText(foto);
        img_foto.setIcon((new javax.swing.ImageIcon("../foto/"+foto)));
        img_foto.setText("");
        
        String PIN = (String) tabelpegawai.getValueAt(i, 5);
        txt_PIN.setText(PIN);
        
        Object shift = (Object) tabelpegawai.getValueAt(i, 4);
        Shift.setSelectedItem(shift);
        

    }//GEN-LAST:event_jtabelpegawaiMouseClicked

    private void btn_simpandataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpandataActionPerformed
        // TODO add your handling code here:
        
        //inisialisasi variabel
        String id_pegawai = txt_IDPegawai.getText();
        String nama_pegawai = txt_AddNamaPegawai.getText();
        String PIN = txt_PIN.getText();
        String jabatan_pegawai = "";

        switch(cmbJabatan.getSelectedIndex()){
            case 1: jabatan_pegawai = "B01"; break;
            case 2: jabatan_pegawai = "B02"; break;
            case 3: jabatan_pegawai = "B03"; break;
            case 4: jabatan_pegawai = "C01"; break;
            case 5: jabatan_pegawai = "C02"; break;
            case 6: jabatan_pegawai = "C03"; break;
            case 7: jabatan_pegawai = "C04"; break;
            case 8: jabatan_pegawai = "D01"; break;
            case 9: jabatan_pegawai = "D02"; break;
            default:jabatan_pegawai = "";break;
        }

        String foto_pegawai = txt_foto.getText();
        String shift_pegawai = "";
        switch(Shift.getSelectedIndex()){
           case 0: shift_pegawai = "1"; break;
           case 1: shift_pegawai = "2"; break;
           case 2: shift_pegawai = "3"; break;
           case 3: shift_pegawai = "D"; break; 
            
        }
        
        //logika agar semua field terisi
        if(id_pegawai.equals("") | nama_pegawai.equals("") | jabatan_pegawai.equals("")
            | foto_pegawai.equals("") ){
            JOptionPane.showMessageDialog(rootPane, "Harap isi semua field");
        }
        else{
            //panggil model, set model sesuai dgn variabel
            MPegawai modp = new MPegawai();
            modp.setIdpegawai(id_pegawai);
            modp.setNama(nama_pegawai);
            modp.setJabatan(jabatan_pegawai);
            modp.setFoto(foto_pegawai);
            modp.setPIN(PIN);
            modp.setShift(shift_pegawai);
            
            //memulai mengkoneksikan menginputkan ke database
            try{
                CManagePegawai manp = new CManagePegawai();
                
                //Cek apakah pegawai tersebut ada di database
                //Jika NIP pegawai tersebut sudah ada di data, maka..
                if(manp.cekPegawai(modp) == true){
                    
                    //tampilkan peringatan bahwa data sudah data, dan mau diubah?
                    int konfirmasiUbah = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin akan mengubah data Pegawai NIP "+modp.getIdpegawai()+" menjadi \nNama : "+modp.getNama()+" \nJabatan : "+modp.getJabatan()+" \nFoto : "+modp.getFoto()+" \nPIN : "+modp.getPIN()+" \nYakin ubah data pegawai ? ","Ubah Data Pegawai", JOptionPane.YES_NO_OPTION);
                    //jika user klik yes
                    if(konfirmasiUbah == JOptionPane.YES_OPTION){
                        //lakukan mekanisme update informasi kedatabase 
                        try{                            
                            manp.updatePegawai(modp);
                            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah", "Data berhasil diubah",JOptionPane.INFORMATION_MESSAGE);
                        }
                        catch(SQLException ex){
                            JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan mengubah data","Kesalahan Mengubah Data",JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        finally{
                            try {
                                loadTblPegawai();

                                txt_IDPegawai.setEnabled(false);
                                txt_IDPegawai.setText("");

                                txt_AddNamaPegawai.setEnabled(false);
                                txt_AddNamaPegawai.setText("");

                                cmbJabatan.setEnabled(false);
                                cmbJabatan.setSelectedIndex(0);

                                txt_PIN.setEnabled(false);
                                txt_PIN.setText("");
                                
                                Shift.setEnabled(false);
                                Shift.setSelectedIndex(0);

                            } catch (SQLException ex) {
                                
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                else{
                    //jika nip belum ada di database, maka 
                    //lakukan mekanisme menambah data baru di database
                    try{
                        manp.tambahPegawai(modp);
                        JOptionPane.showMessageDialog(rootPane, "Data berhasil ditambahkan", "Berhasil Menambahkan Dambahkan",JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(SQLException ex){
                        JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan memasukan data pegawai baru","Kesalahan Menambah Data",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{
                        try {
                            loadTblPegawai();

                            txt_IDPegawai.setEnabled(false);
                            txt_IDPegawai.setText("");

                            txt_AddNamaPegawai.setEnabled(false);
                            txt_AddNamaPegawai.setText("");

                            cmbJabatan.setEnabled(false);
                            cmbJabatan.setSelectedIndex(0);

                            txt_PIN.setEnabled(false);
                            txt_PIN.setText("");
                            
                            txt_PIN.setEnabled(false);
                                txt_PIN.setText("");
                            
                            Shift.setEnabled(false);
                            Shift.setSelectedIndex(0);

                        } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
            }catch(SQLException ex){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btn_simpandataActionPerformed

    private void btn_hapusdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusdataActionPerformed
        // TODO add your handling code here:
        int i = jtabelpegawai.getSelectedRow();
        if(i== -1){
            return;
        }
        String nip = (String) tabelpegawai.getValueAt(i, 0);
        String nama = (String) tabelpegawai.getValueAt(i, 1);
        
        MPegawai modp = new MPegawai();
        modp.setIdpegawai(nip);
        modp.setNama(nama);

        int konfirmasiHapus = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin akan menghapus data pegawai "
            + "dengan \n NIP "+nip+" yang bernama "+nama+"? ", "Hapus Pegawai", JOptionPane.YES_NO_OPTION);
        if(konfirmasiHapus == JOptionPane.YES_OPTION){
            try{
                CManagePegawai manp = new CManagePegawai();
                manp.hapusPegawai(modp);
                JOptionPane.showMessageDialog(rootPane, "Data berhasil dihapus", "Berhasil Menghapus Data",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan menghapus data","Kesalahan Menghapus Data",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    loadTblPegawai();
                    
                    txt_IDPegawai.setEnabled(false);
                    txt_IDPegawai.setText("");

                    txt_AddNamaPegawai.setEnabled(false);
                    txt_AddNamaPegawai.setText("");

                    cmbJabatan.setEnabled(false);
                    cmbJabatan.setSelectedIndex(0);

                    txt_PIN.setEnabled(false);
                    txt_PIN.setText("");
                    
                    txt_foto.setEnabled(false);
                    txt_foto.setText("");

                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btn_hapusdataActionPerformed

    private void btn_ubahdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahdataActionPerformed
        // TODO add your handling code here:
        int i = jtabelpegawai.getSelectedRow();
        if(i== -1){
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data pegawai yang dipilih");
        }

        txt_IDPegawai.setEnabled(false);
        txt_AddNamaPegawai.setEnabled(true);
        cmbJabatan.setEnabled(true);
        txt_foto.setEnabled(true);
        txt_PIN.setEnabled(true);
        Shift.setEnabled(true);

        String nip = (String) tabelpegawai.getValueAt(i, 0);
        txt_IDPegawai.setText(nip);

        String nama = (String) tabelpegawai.getValueAt(i, 1);
        txt_AddNamaPegawai.setText(nama);

        Object jabatan = (Object) tabelpegawai.getValueAt(i, 2);
        cmbJabatan.setSelectedItem(jabatan);

        String foto = (String) tabelpegawai.getValueAt(i, 3);
        txt_PIN.setText(foto);
        
        String PIN = (String) tabelpegawai.getValueAt(i, 5);
        txt_PIN.setText(PIN);
        
        Object shift = (Object) tabelpegawai.getValueAt(i, 4);
        Shift.setSelectedItem(shift);

    }//GEN-LAST:event_btn_ubahdataActionPerformed

    private void btn_tambahpegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahpegawaiActionPerformed
        // TODO add your handling code here:

        txt_IDPegawai.setEnabled(true);
        txt_IDPegawai.setText("");
        txt_IDPegawai.requestFocus();

        txt_AddNamaPegawai.setEnabled(true);
        txt_AddNamaPegawai.setText("");

        cmbJabatan.setEnabled(true);
        cmbJabatan.setSelectedIndex(0);

        txt_foto.setEnabled(true);
        txt_foto.setText("");
        
        txt_PIN.setEnabled(true);
        txt_PIN.setText("");
        
        img_foto.setIcon(null);
        
        Shift.setEnabled(true);
        

    }//GEN-LAST:event_btn_tambahpegawaiActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        panel_izin.setVisible(true);     
        panel_Laporan_pegawai.setVisible(false);
        panel_Laporan_Harian.setVisible(false);
        panel_Pegawai.setVisible(false);
        
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void radAbsen_izinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAbsen_izinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radAbsen_izinActionPerformed

    private void txtAbsen_keteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbsen_keteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbsen_keteranganActionPerformed

    private void jtabelpegawaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtabelpegawaiFocusGained
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jtabelpegawaiFocusGained

    private void btnAbsen_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbsen_simpanActionPerformed
        // TODO add your handling code here:
        
        String absen_nip = txtAbsen_nip.getText();
        String absen_tanggal = "";
        if(txtAbsen_tanggal.getDate() != null){
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
        absen_tanggal = Format.format(txtAbsen_tanggal.getDate());
        }
        String absen_izin = "";
        
        if(jenis_absen.getSelection().equals(radAbsen_cuti.getModel())){
            absen_izin = "Cuti";
        }
        else if(jenis_absen.getSelection().equals(radAbsen_izin.getModel())){
            absen_izin = "Izin";
        }else{
            JOptionPane.showMessageDialog(rootPane, "Harap isi semua field");  
        }
        
        String absen_keterangan = txtAbsen_keterangan.getText();
        
        
        
        if( "".equals(absen_nip) | txtAbsen_tanggal.getDate()== null | jenis_absen.getSelection() == null | "".equals(absen_keterangan) ){
            JOptionPane.showMessageDialog(rootPane, "Harap isi semua field");               
        }else{
            
            int confirmSimpan = JOptionPane.showConfirmDialog(rootPane,"Apakah anda data tersebut sudah benar?","Mengimput data",JOptionPane.YES_NO_OPTION );
            if (confirmSimpan == JOptionPane.YES_OPTION){
            
            MAbsensi moda = new MAbsensi();
            
            moda.setNip(absen_nip);
            moda.setTanggal(absen_tanggal);
            moda.setAbsen(absen_izin);
            moda.setKeterangan(absen_keterangan);
            
            try{
                CManageAbsensi mana = new CManageAbsensi();
                if(mana.cekPegawai(moda) == true){
                    
                    if(mana.cekDuplikasi(moda) == true){
                        int ubahData = JOptionPane.showConfirmDialog(this, 
                                "Data tersebut sudah ada di database. \n"
                                + "apakah anda yakin mau merubahnya?", "Ubah data absen", 
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if(ubahData == JOptionPane.YES_OPTION){
                            try{
                                int no = Integer.parseInt(txtAbsen_no.getText());
                                moda.setNo(no);
                                mana.ubahAbsen(moda);   
                                JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah", "Data berhasil diubah",JOptionPane.INFORMATION_MESSAGE);
                            }catch(SQLException ex){
                                JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan mengubah data","Kesalahan Mengubah data",JOptionPane.ERROR_MESSAGE);
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }finally{
                                loadTblAbsen();                               
                                
                            }
                            
                        }else if(ubahData == JOptionPane.NO_OPTION){
                            
                            txtAbsen_nip.setEnabled(false);

                            txtAbsen_tanggal.setEnabled(false);

                            radAbsen_izin.setEnabled(false);
                            radAbsen_cuti.setEnabled(false);

                            txtAbsen_keterangan.setEnabled(false);
                        }
                        
                    }else{
                        try{
                            mana.tambahAbsen(moda);
                            JOptionPane.showMessageDialog(rootPane, "Data berhasil ditambah", "Data berhasil ditambah",JOptionPane.INFORMATION_MESSAGE);
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan menambahkan data","Kesalahan Menambah Data",JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }finally{
                            loadTblAbsen();
                            
                            txtAbsen_no.setText("");
                            
                            txtAbsen_nip.setEnabled(false);
                            txtAbsen_nip.setText("");
                            txtAbsen_nip.requestFocus();

                            txtAbsen_tanggal.setEnabled(false);
                            txtAbsen_tanggal.setDate(null);

                            radAbsen_izin.setEnabled(false);
                            radAbsen_cuti.setEnabled(false);
                            radAbsen_izin.setSelected(false);
                            radAbsen_cuti.setSelected(false);


                            txtAbsen_keterangan.setEnabled(false);
                            txtAbsen_keterangan.setText("");
                        }
                    }
                          
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Pegawai dengan nip "+moda.getNip()+" tidak ditemukan","Tidak ditemukan",JOptionPane.ERROR_MESSAGE);
                }
            }catch(SQLException ex){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }//GEN-LAST:event_btnAbsen_simpanActionPerformed

    private void txtAbsen_tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtAbsen_tanggalPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtAbsen_tanggalPropertyChange

    private void txtAbsen_nipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbsen_nipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbsen_nipActionPerformed

    private void btnAbsen_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbsen_tambahActionPerformed
        // TODO add your handling code here:
        
        txtAbsen_no.setText("");
        txtAbsen_nip.setEnabled(true);
        txtAbsen_nip.setText("");
        txtAbsen_nip.requestFocus();
        
        txtAbsen_tanggal.setEnabled(true);
        txtAbsen_tanggal.setDate(null);
        
        radAbsen_izin.setEnabled(true);
        radAbsen_cuti.setEnabled(true);
        radAbsen_izin.setSelected(false);
        radAbsen_cuti.setSelected(false);
        
        
        txtAbsen_keterangan.setEnabled(true);
        txtAbsen_keterangan.setText("");
        img_foto_absen.setText("foto");
        img_foto_absen.setIcon(null);
        
        
    }//GEN-LAST:event_btnAbsen_tambahActionPerformed

    private void btnAbsen_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbsen_ubahActionPerformed
        // TODO add your handling code here:
        
        int i = jtabelabsen.getSelectedRow();
        if(i== -1 | "".equals(txtAbsen_no.getText())){
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data absen yang dipilih");
            
            txtAbsen_no.setText("");
            
        }
        
        txtAbsen_nip.setEnabled(true);
        
        txtAbsen_tanggal.setEnabled(true);
        
        radAbsen_izin.setEnabled(true);
        radAbsen_cuti.setEnabled(true);
 
        txtAbsen_keterangan.setEnabled(true);
    }//GEN-LAST:event_btnAbsen_ubahActionPerformed

    private void btnAbsen_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbsen_hapusActionPerformed
        // TODO add your handling code here:
        int i = jtabelabsen.getSelectedRow();
        if(i== -1){
            return;
        }
        
        int no = Integer.parseInt(txtAbsen_no.getText());
        MAbsensi moda = new MAbsensi();
        moda.setNo(no);

        int konfirmasiHapus = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin akan menghapus data absensi tersebut?", "Hapus Pegawai", JOptionPane.YES_NO_OPTION);
        if(konfirmasiHapus == JOptionPane.YES_OPTION){
            try{
                CManageAbsensi mana = new CManageAbsensi();
                mana.hapusPegawai(moda);
                JOptionPane.showMessageDialog(rootPane, "Data berhasil dihapus", "Berhasil Menghapus Data",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan menghapus data","Kesalahan Menghapus Data",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    loadTblAbsen();
                    
                    txtAbsen_no.setText("");
                    txtAbsen_nip.setEnabled(false);
                    txtAbsen_nip.setText("");
        
                    txtAbsen_tanggal.setEnabled(false);
                    txtAbsen_tanggal.setDate(null);
                    
                    radAbsen_izin.setEnabled(false);
                    radAbsen_cuti.setEnabled(false);
                    radAbsen_izin.setSelected(false);
                    radAbsen_cuti.setSelected(false);
                    
                    txtAbsen_keterangan.setEnabled(true);
                    txtAbsen_keterangan.setText("");

                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnAbsen_hapusActionPerformed

    private void jtabelabsenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabelabsenMouseClicked
        // TODO add your handling code here:
        
        //ubah keadaan field-field menjadi tidak berfungsi
        txtAbsen_nip.setEnabled(false);      
        txtAbsen_tanggal.setEnabled(false);        
        radAbsen_izin.setEnabled(false);
        radAbsen_cuti.setEnabled(false); 
        txtAbsen_keterangan.setEnabled(false);
        
        
        //cek apakah salah satu baris sudah diklik
        int i = jtabelabsen.getSelectedRow();
        if(i== -1){
            //tidak melakukan apa-apa
            return;
        }
        
        /*
          mengambil isi dari tabel yang di klik, lalu menampilkannya
          * di field-field
        */
        
        String No_rec_absen = (tabelabsen.getValueAt(i, 0)).toString();
        txtAbsen_no.setText(No_rec_absen);
        
        Date tanggal = ( Date )tabelabsen.getValueAt(i, 1);
        txtAbsen_tanggal.setDate(tanggal);
        
        String nip = (String) tabelabsen.getValueAt(i, 2);
        txtAbsen_nip.setText(nip);

        String cuti_izin = (String) tabelabsen.getValueAt(i, 5);
        if("Cuti".equals(cuti_izin)){
            radAbsen_cuti.setSelected(true);
        }else{
            radAbsen_izin.setSelected(true);
        }
        
        String keterangan = (String) tabelabsen.getValueAt(i, 6);
        txtAbsen_keterangan.setText(keterangan);
        
        String foto = (String) tabelabsen.getValueAt(i, 7);
        
        img_foto_absen.setIcon((new javax.swing.ImageIcon("../foto/"+foto)));
        
    }//GEN-LAST:event_jtabelabsenMouseClicked

    private void txtAbsen_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbsen_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbsen_noActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = sdf.format(jcal_tanggalhadir.getDate());

        
        Connection con = CKoneksiDB.getKoneksi();
            String NamaFile = "/report/reportharian.jasper";
            HashMap hash = new HashMap();
        try {
            hash.put("tanggalhadir", tanggal);
            runReportDefault(NamaFile, hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        

    }//GEN-LAST:event_jButton4ActionPerformed

    private void ShiftItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShiftItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ShiftItemStateChanged

    private void ShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShiftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShiftActionPerformed

    private void btn_tambahpegawai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahpegawai1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambahpegawai1ActionPerformed

    private void btn_ubahdata1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahdata1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ubahdata1ActionPerformed

    private void btn_hapusdata1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusdata1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusdata1ActionPerformed

    private void btn_simpandata1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpandata1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpandata1ActionPerformed

    private void jtabelpegawai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabelpegawai1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabelpegawai1MouseClicked

    private void jtabelpegawai1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtabelpegawai1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabelpegawai1FocusGained

    private void txt_IDPegawai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IDPegawai1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IDPegawai1ActionPerformed

    private void cmbJabatan1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJabatan1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJabatan1ItemStateChanged

    private void txt_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fotoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Admin().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dasbor_jumlah_pegawai;
    private javax.swing.JLabel Dasbor_persentase_kehadiran;
    private javax.swing.JComboBox Shift;
    private javax.swing.JButton btnAbsen_hapus;
    private javax.swing.JButton btnAbsen_simpan;
    private javax.swing.JButton btnAbsen_tambah;
    private javax.swing.JButton btnAbsen_ubah;
    private javax.swing.JButton btn_hapusdata;
    private javax.swing.JButton btn_hapusdata1;
    private javax.swing.JButton btn_simpandata;
    private javax.swing.JButton btn_simpandata1;
    private javax.swing.JButton btn_tambahpegawai;
    private javax.swing.JButton btn_tambahpegawai1;
    private javax.swing.JButton btn_ubahdata;
    private javax.swing.JButton btn_ubahdata1;
    private javax.swing.JComboBox cmbJabatan;
    private javax.swing.JComboBox cmbJabatan1;
    private javax.swing.JLabel img_foto;
    private javax.swing.JLabel img_foto1;
    private javax.swing.JLabel img_foto_absen;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private com.toedter.calendar.JCalendar jcal_tanggalhadir;
    private javax.swing.ButtonGroup jenis_absen;
    private javax.swing.JTable jtabelDasbor;
    private javax.swing.JTable jtabelabsen;
    private javax.swing.JTable jtabelpegawai;
    private javax.swing.JTable jtabelpegawai1;
    private javax.swing.JPanel panel_Laporan_Harian;
    private javax.swing.JPanel panel_Laporan_pegawai;
    private javax.swing.JPanel panel_Pegawai;
    private javax.swing.JPanel panel_Pegawai1;
    private javax.swing.JPanel panel_izin;
    private javax.swing.JRadioButton radAbsen_cuti;
    private javax.swing.JRadioButton radAbsen_izin;
    private javax.swing.JTextField txtAbsen_keterangan;
    private javax.swing.JTextField txtAbsen_nip;
    private javax.swing.JTextField txtAbsen_no;
    private com.toedter.calendar.JDateChooser txtAbsen_tanggal;
    private javax.swing.JTextField txt_AddNamaPegawai;
    private javax.swing.JTextField txt_AddNamaPegawai1;
    private javax.swing.JTextField txt_IDPegawai;
    private javax.swing.JTextField txt_IDPegawai1;
    private javax.swing.JTextField txt_PIN;
    private javax.swing.JTextField txt_foto;
    // End of variables declaration//GEN-END:variables
}
