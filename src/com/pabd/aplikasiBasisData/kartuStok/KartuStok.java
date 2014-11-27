/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pabd.aplikasiBasisData.kartuStok;

import com.pabd.aplikasiBasisData.DBConn.DataBase;
import com.pabd.aplikasiBasisData.barang.Barang;
import com.pabd.aplikasiBasisData.kategori.Kategori;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author root
 */
public class KartuStok {

    private Barang mBarang;
    private String mTanggal;
    private String mNomorBukti;
    private String mKeterangan;
    private double mMasuk;
    private double mKeluar;
    private double mSaldo;

    public KartuStok() {
        mBarang = new Barang();
    }

    public Barang getmBarang() {
        return mBarang;
    }

    public void setmBarang(Barang mBarang) {
        this.mBarang = mBarang;
    }

    public String getmTanggal() {
        return mTanggal;
    }

    public void setmTanggal(String mTanggal) {
        this.mTanggal = mTanggal;
    }

    public String getmNomorBukti() {
        return mNomorBukti;
    }

    public void setmNomorBukti(String mNomorBukti) {
        this.mNomorBukti = mNomorBukti;
    }

    public String getmKeterangan() {
        return mKeterangan;
    }

    public void setmKeterangan(String mKeterangan) {
        this.mKeterangan = mKeterangan;
    }

    public double getmMasuk() {
        return mMasuk;
    }

    public void setmMasuk(double mMasuk) {
        this.mMasuk = mMasuk;
    }

    public double getmKeluar() {
        return mKeluar;
    }

    public void setmKeluar(double mKeluar) {
        this.mKeluar = mKeluar;
    }

    public double getmSaldo() {
        return mSaldo;
    }

    public void setmSaldo(double mSaldo) {
        this.mSaldo = mSaldo;
    }

    public static void simpanData(KartuStok pKartuStok) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO kartu_stok VALUES(?, ?, ?, ?, ?, ?)";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, pKartuStok.getmBarang().getmKodeBarang());
            prepStatement.setString(2, pKartuStok.getmTanggal());
            prepStatement.setString(3, pKartuStok.getmNomorBukti());
            prepStatement.setString(4, pKartuStok.getmKeterangan());
            prepStatement.setDouble(5, pKartuStok.getmMasuk());
            prepStatement.setDouble(6, pKartuStok.getmKeluar());
            prepStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Berhasil disimpan!");
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal menyimpan!");
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void hapusData(String pNomorBukti) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "DELETE FROM kartu_stok WHERE nomor_bukti = ?";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, pNomorBukti);
            prepStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Berhasil dihapus!");
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal menghapus!");
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<Barang> lihatBarangBerdasarKode(String kode) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet result = null;
        List<Barang> barangList = new ArrayList<Barang>();

        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            prepStatement = connection.prepareStatement("SELECT b.kode_barang as kode_barang, "
                    + "k.nama_kategori as nama_kategori, "
                    + "b.nama_barang, b.satuan FROM barang b, kategori k"
                    + "WHERE b.kode_barang = ? AND b.kode_kategori = k.kode_kategori "
                    + "ORDER BY b.kode_barang");
            prepStatement.setString(1, kode);
            result = prepStatement.executeQuery();
            while (result.next()) {
                Barang barang = new Barang();
                barang.setmKodeBarang(result.getString("kode_barang"));
                barang.setmNamaBarang(result.getString("nama_barang"));
                barang.setmSatuan(result.getString("satuan_barang"));
                barang.setmQtyMax(result.getString("qty_max_barang"));
                barang.setmQtyMin(result.getString("qty_min_barang"));
                barang.setmRitelsetelah(result.getString("ritel_setelah_ppn"));
                barang.setmRitelsebelum(result.getString("ritel_sebelum_ppn"));
                barang.setmBelisetelah(result.getString("beli_setelah_ppn"));
                barang.setmBelisebelum(result.getString("beli_sebelum_ppn"));
                barangList.add(barang);
            }
            connection.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tidak ditemukan data");
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(false);
                if (result != null) {
                    result.close();
                }
                if (prepStatement != null) {
                    prepStatement.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(KartuStok.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return barangList;
    }

    public static List<KartuStok> lihatKartuStokForTableModel(String pKodeBarang) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet result = null;
        List<KartuStok> kartuStokList = new ArrayList<KartuStok>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");

        double saldoAwal = 0;
        int counter = 0;

        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            prepStatement = connection.prepareStatement("SELECT tanggal, nomor_bukti, "
                    + "keterangan, masuk, keluar FROM kartu_stok WHERE kode_barang = ? ORDER BY tanggal ASC");
            prepStatement.setString(1, pKodeBarang);
            result = prepStatement.executeQuery();
            while (result.next()) {
                KartuStok kartuStok = new KartuStok();
                String tgl = sdf.format(result.getDate("tanggal"));

                kartuStok.setmTanggal(tgl.toUpperCase());
                kartuStok.setmNomorBukti(result.getString("nomor_bukti"));
                kartuStok.setmKeterangan(result.getString("keterangan"));
                kartuStok.setmMasuk(result.getDouble("masuk"));
                kartuStok.setmKeluar(result.getDouble("keluar"));

                if (counter == 0) {
                    saldoAwal = result.getDouble("masuk") - result.getDouble("keluar");
                    kartuStok.setmSaldo(saldoAwal);
                    System.out.println(saldoAwal);
                } else {
                    saldoAwal += (result.getDouble("masuk") - result.getDouble("keluar"));
                    kartuStok.setmSaldo(saldoAwal);
                }

                counter++;
                kartuStokList.add(kartuStok);
            }
            connection.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tidak ditemukan data");
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(false);
                if (result != null) {
                    result.close();
                }
                if (prepStatement != null) {
                    prepStatement.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(KartuStok.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kartuStokList;
    }

    public static ResultSet isiInformasiBarang(String kode) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet result = null;
        List<Barang> barangList = new ArrayList<>();

        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT b.nama_barang, b.satuan_barang, k.nama_kategori FROM barang b, kategori k WHERE kode_barang = ? "
                    + "AND b.kode_kategori = k.kode_kategori";
            System.out.println(sql);
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, kode);
            result = prepStatement.executeQuery();
            connection.commit();

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Tidak ditemukan data");
            Logger.getLogger(KartuStok.class.getName()).log(Level.SEVERE, null, exception);
            return null;
        }
        return result;
    }
    
    public static void cetakDataPadaTabel(JTable pTable, String pIdBarang) {

        String reportSource = "./report/kartu_stok.jrxml";

        Map<String, Object> params = new HashMap<>();
        
        params.put("ID_BARANG", pIdBarang);

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRTableModelDataSource(pTable.getModel()));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}
