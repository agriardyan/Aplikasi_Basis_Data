/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pabd.aplikasiBasisData.kategori;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class KategoriTableModel extends AbstractTableModel {
    
    private List<Kategori> kategoris = new ArrayList<Kategori>();

    public KategoriTableModel(List<Kategori> kategoris) {
        this.kategoris = kategoris;
    }

    public void deleteKategori(int row){
        kategoris.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateKategori(int row, Kategori kategori){
        kategoris.set(row,kategori);
        fireTableRowsUpdated(row, row);
    }

    public void addKategori(Kategori kategori) {
        kategoris.add(kategori);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public int getRowCount() {
        return kategoris.size();
    }

    
    public int getColumnCount() {
        return 2;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kategori kategori = kategoris.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kategori.getmKodeKategori();
            case 1:
                return kategori.getmNamaKategori();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "KODE_KATEGORI";
            case 1:
                return "NAMA_KATEGORI";
            default:
                return "";
        }
    }
    
}
