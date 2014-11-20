/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pabd.aplikasiBasisData.kartuStok;

import com.pabd.aplikasiBasisData.barang.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class BarangTableModel extends AbstractTableModel {

    private List<Barang> barangList = new ArrayList<>();

    public BarangTableModel(List<Barang> barangList) {
        this.barangList = barangList;
    }

    public void deleteKategori(int row) {
        barangList.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateKategori(int row, Barang barang) {
        barangList.set(row, barang);
        fireTableRowsUpdated(row, row);
    }

    public void addKategori(Barang barang) {
        barangList.add(barang);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public int getRowCount() {
        return barangList.size();
    }

    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Barang b = barangList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getmKodeBarang();
            case 1:
                return b.getmNamaBarang();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "KODE_BARANG";
            case 1:
                return "NAMA_BARANG";
            default:
                return "";
        }
    }
}
