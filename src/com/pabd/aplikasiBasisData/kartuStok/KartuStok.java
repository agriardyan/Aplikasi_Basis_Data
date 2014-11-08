/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pabd.aplikasiBasisData.kartuStok;

import com.pabd.aplikasiBasisData.DBConn.DataBase;
import com.pabd.aplikasiBasisData.barang.Barang;
import com.pabd.aplikasiBasisData.kategori.Kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    
}
