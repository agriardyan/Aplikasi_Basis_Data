/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pabd.aplikasiBasisData.kategori;

import com.pabd.aplikasiBasisData.DBConn.DataBase;
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
public class Kategori {
    
    private String mKodeKategori;
    
    private String mNamaKategori;

    public Kategori() {
    }

    public Kategori(String mKodeKategori, String mNamaKategori) {
        this.mKodeKategori = mKodeKategori;
        this.mNamaKategori = mNamaKategori;
    }

    public String getmKodeKategori() {
        return mKodeKategori;
    }

    public void setmKodeKategori(String mKodeKategori) {
        this.mKodeKategori = mKodeKategori;
    }

    public String getmNamaKategori() {
        return mNamaKategori;
    }

    public void setmNamaKategori(String mNamaKategori) {
        this.mNamaKategori = mNamaKategori;
    }
    
    public static void simpanKategori(Kategori pKategori)
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO kategori VALUES(?, ?)";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, String.valueOf(pKategori.getmKodeKategori()));
            prepStatement.setString(2, pKategori.getmNamaKategori());
            prepStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Berhasil disimpan!");
        } catch (SQLException ex) {
            Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal menyimpan!");
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void updateKategori(String pKodeAwal, Kategori pKategori)
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "UPDATE kategori SET kode_kategori = ? , nama_kategori = ? WHERE kode_kategori = ?";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, String.valueOf(pKategori.getmKodeKategori()));
            prepStatement.setString(2, pKategori.getmNamaKategori());
            prepStatement.setString(3, pKodeAwal);
            prepStatement.executeUpdate();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Berhasil diupdate!");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal update!");
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void deleteKategori(Kategori pKategori)
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "DELETE FROM kategori WHERE kode_kategori = ?";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, String.valueOf(pKategori.getmKodeKategori()));
            prepStatement.executeQuery();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Berhasil hapus!");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal hapus!");
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String cariBerdasarKode(Kategori pKategori)
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet rs = null;
        String namaKategori = null;
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            String query = "SELECT nama_kategori FROM kategori WHERE kode_kategori = ?";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, String.valueOf(pKategori.getmKodeKategori()));
            rs = prepStatement.executeQuery();
            connection.commit();
            
            while (rs.next()) {
                namaKategori = rs.getString("nama_kategori");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return namaKategori;
    }
    
    public static List<Kategori> lihatKategori() 
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Kategori> kategoriList = new ArrayList<Kategori>();
        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT kode_kategori, nama_kategori FROM kategori ORDER BY kode_kategori");
            result = statement.executeQuery();
            while (result.next()) {
                Kategori kategori = new Kategori();
                kategori.setmKodeKategori(result.getString("kode_kategori"));
                kategori.setmNamaKategori(result.getString("nama_kategori"));
                kategoriList.add(kategori);
            }
            connection.commit();
        } catch (SQLException exception) {
            Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, exception);
        } finally {
            try {
                connection.setAutoCommit(false);
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exception) {
                Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return kategoriList;
    }
    
}
