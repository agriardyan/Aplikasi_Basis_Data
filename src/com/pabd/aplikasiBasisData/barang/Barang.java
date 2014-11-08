/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pabd.aplikasiBasisData.barang;

import com.pabd.aplikasiBasisData.DBConn.DataBase;
import com.pabd.aplikasiBasisData.kategori.Kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author root
 */
public class Barang {

    private String mKodeBarang;
    private String mNamaBarang;
    private String mSatuan;
    private String mQtyMax;
    private String mQtyMin;
    private String mRitelsetelah;
    private String mRitelsebelum;
    private String mBelisetelah;
    private String mBelisebelum;

    public Barang() {
    }

    public Barang(String mKodeBarang, String mNamaBarang, String mSatuan, String mQtyMax, String mQtyMin, String mRitelsetelah, String mRitelsebelum, String mBelisetelah, String mBelisebelum) {
        this.mKodeBarang = mKodeBarang;
        this.mNamaBarang = mNamaBarang;
        this.mSatuan = mSatuan;
        this.mQtyMax = mQtyMax;
        this.mQtyMin = mQtyMin;
        this.mRitelsetelah = mRitelsetelah;
        this.mRitelsebelum = mRitelsebelum;
        this.mBelisetelah = mBelisetelah;
        this.mBelisebelum = mBelisebelum;
    }

    public String getmKodeBarang() {
        return mKodeBarang;
    }

    public void setmKodeBarang(String mKodeBarang) {
        this.mKodeBarang = mKodeBarang;
    }

    public String getmNamaBarang() {
        return mNamaBarang;
    }

    public void setmNamaBarang(String mNamaBarang) {
        this.mNamaBarang = mNamaBarang;
    }

    public String getmSatuan() {
        return mSatuan;
    }

    public void setmSatuan(String mSatuan) {
        this.mSatuan = mSatuan;
    }

    public String getmQtyMax() {
        return mQtyMax;
    }

    public void setmQtyMax(String mQtyMax) {
        this.mQtyMax = mQtyMax;
    }

    public String getmQtyMin() {
        return mQtyMin;
    }

    public void setmQtyMin(String mQtyMin) {
        this.mQtyMin = mQtyMin;
    }

    public String getmRitelsetelah() {
        return mRitelsetelah;
    }

    public void setmRitelsetelah(String mRitelsetelah) {
        this.mRitelsetelah = mRitelsetelah;
    }

    public String getmRitelsebelum() {
        return mRitelsebelum;
    }

    public void setmRitelsebelum(String mRitelsebelum) {
        this.mRitelsebelum = mRitelsebelum;
    }

    public String getmBelisetelah() {
        return mBelisetelah;
    }

    public void setmBelisetelah(String mBelisetelah) {
        this.mBelisetelah = mBelisetelah;
    }

    public String getmBelisebelum() {
        return mBelisebelum;
    }

    public void setmBelisebelum(String mBelisebelum) {
        this.mBelisebelum = mBelisebelum;
    }

    public static void simpanBarang(Barang pBarang) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO barang VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, pBarang.getmKodeBarang());
            prepStatement.setString(2, pBarang.getmNamaBarang());
            prepStatement.setString(3, pBarang.getmSatuan());
            prepStatement.setString(4, pBarang.getmQtyMax());
            prepStatement.setString(5, pBarang.getmQtyMin());
            prepStatement.setString(6, pBarang.getmRitelsetelah());
            prepStatement.setString(7, pBarang.getmRitelsebelum());
            prepStatement.setString(8, pBarang.getmBelisetelah());
            prepStatement.setString(9, pBarang.getmBelisebelum());
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
    
    public static void updateBarang(Barang pBarang) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = ""
                    + "UPDATE barang "
                    + "SET "
                    + "nama_barang = ?, "
                    + "satuan_barang = ?, "
                    + "qty_max_barang = ?, "
                    + "qty_min_barang = ?, "
                    + "ritel_setelah_ppn = ?, "
                    + "ritel_sebelum_ppn = ?, "
                    + "beli_setelah_ppn = ?, "
                    + "beli_sebelum_ppn = ? "
                    + "WHERE kode_barang = ?";
            
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, pBarang.getmNamaBarang());
            prepStatement.setString(2, pBarang.getmSatuan());
            prepStatement.setString(3, pBarang.getmQtyMax());
            prepStatement.setString(4, pBarang.getmQtyMin());
            prepStatement.setString(5, pBarang.getmRitelsetelah());
            prepStatement.setString(6, pBarang.getmRitelsebelum());
            prepStatement.setString(7, pBarang.getmBelisetelah());
            prepStatement.setString(8, pBarang.getmBelisebelum());
            prepStatement.setString(9, pBarang.getmKodeBarang());
            prepStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Berhasil diupdate!");
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal mengupdate!");
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Membuat kode baru dengan cara mencari kode terbesar dalam kategori
     * tertentu, lalu ditambahkan +1, e.g. kode kategori 101, kode barang ada
     * 101001 101002 101003, maka akan dibuat kode barang baru berupa 101004
     *
     * @param kodeKategori kode dari kategori terkait
     * @return maxCode kode terbesar + 1 dalam kategori terkait
     */
    public static String cariMaxKode(String kodeKategori) {
        Connection connection = null;
        Statement statemen = null;
        ResultSet rs = null;
        String maxCode = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "SELECT max(kode_barang)+1 as kode_barang FROM barang WHERE kode_barang LIKE '" + kodeKategori + "%'";
            statemen = connection.createStatement();
            rs = statemen.executeQuery(query);
            connection.commit();

            if (rs == null) {
                return kodeKategori + "001";
            }

            while (rs.next()) {
                maxCode = rs.getString("kode_barang");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statemen.close();
            } catch (SQLException ex) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return maxCode;
    }

    /**
     * Membuat list berisi KESELURUHAN daftar barang
     *
     * @return list - list barang dalam database
     */
    public static List<Barang> lihatBarang() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Barang> barangList = new ArrayList<Barang>();

        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM barang ORDER BY kode_barang");
            result = statement.executeQuery();
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
        } catch (SQLException exception) {
            List<Kategori> kategoriList = new ArrayList<Kategori>();
        } finally {
            try {
                connection.setAutoCommit(false);
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return barangList;
    }

    /**
     * Membuat list berisi sebagian daftar barang yang ditentukan
     *
     * @param namaBarang nama barang yang dicari
     * @return list - list barang dalam database yang ditentukan
     */
    public static List<Barang> lihatBarang(String namaBarang) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "SELECT * FROM barang WHERE "
                    + "nama_barang LIKE '" + namaBarang + "%' OR "
                    + "nama_barang LIKE '%" + namaBarang + "' OR "
                    + "nama_barang LIKE '%" + namaBarang + "%' ORDER BY kode_barang ASC ";
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            List<Barang> barangList = new ArrayList<Barang>();
            while (result.next()) {
                Barang barang = new Barang();
                barang.setmKodeBarang(result.getString("KODE_BARANG"));
                barang.setmNamaBarang(result.getString("NAMA_BARANG"));
                barang.setmSatuan(result.getString("SATUAN_BARANG"));
                barang.setmQtyMax(result.getString("QTY_MAX_BARANG"));
                barang.setmQtyMin(result.getString("QTY_MIN_BARANG"));
                barang.setmRitelsetelah(result.getString("RITEL_SETELAH_PPN"));
                barang.setmRitelsebelum(result.getString("RITEL_SEBELUM_PPN"));
                barang.setmBelisetelah(result.getString("BELI_SETELAH_PPN"));
                barang.setmBelisebelum(result.getString("BELI_SEBELUM_PPN"));
                barangList.add(barang);
            }

            connection.commit();
            return barangList;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tidak ditemukan data!");
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public static List<Barang> lihatBarang(String kodeAwal, String kodeAkhir) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet result = null;
        List<Barang> barangList = new ArrayList<Barang>();

        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            prepStatement = connection.prepareStatement("SELECT * FROM barang "
                    + "WHERE kode_barang BETWEEN ? AND ? "
                    + "ORDER BY kode_barang");
            prepStatement.setString(1, kodeAwal);
            prepStatement.setString(2, kodeAkhir);
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
        } catch (SQLException exception) {
            List<Kategori> kategoriList = new ArrayList<Kategori>();
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
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return barangList;
    }

    public static void hapusBarang(String kode) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "DELETE FROM barang WHERE kode_barang = ?";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, kode);
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

    /**
     * Method ini digunakan untuk mencetak report tanpa rentang kode barang,
     * jadi seluruh barang di database akan dicetak
     */
    public static void cetakDaftarMenu() {
        Connection connection = null;
        connection = DataBase.getConnection();

        String reportSource = "./report/daftarBarang.jasper";

        Map<String, Object> params = new HashMap<String, Object>();

        JasperPrint jasperPrint = null;

        try {
            jasperPrint = JasperFillManager.fillReport(reportSource, params, connection);
        } catch (JRException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer.viewReport(jasperPrint, false);
        
    }

    /**
     * Method ini digunakan untuk mencetak report dengan rentang kode barang
     * yang ditentukan, jadi tidak seluruh barang dicetak
     *
     * @param kodeAwal batas bawah rentang kode barang
     * @param kodeAkhir batas atas rentang kode barang
     */
    public static void cetakDaftarMenu(String kodeAwal, String kodeAkhir) {
        Connection connection = null;
        connection = DataBase.getConnection();

        String reportSource = "./report/daftarBarangDenganParameter2.jasper";

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("kodeAwal", kodeAwal);
        params.put("kodeAkhir", kodeAkhir);

        JasperPrint jasperPrint = null;

        try {
            jasperPrint = JasperFillManager.fillReport(reportSource, params, connection);
        } catch (JRException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer.viewReport(jasperPrint, false);
        
    }

}
