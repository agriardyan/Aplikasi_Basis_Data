/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pabd.aplikasiBasisData.barang;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class BarangTableModel extends AbstractTableModel {

    private List<Barang> barangList = new ArrayList<Barang>();

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
        return 9;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Barang b = barangList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getmKodeBarang();
            case 1:
                return b.getmNamaBarang();
            case 2:
                return b.getmSatuan();
            case 3:
                return b.getmQtyMax();
            case 4:
                return b.getmQtyMin();
            case 5:
                return b.getmRitelsetelah();
            case 6:
                return b.getmRitelsebelum();
            case 7:
                return b.getmBelisetelah();
            case 8:
                return b.getmBelisebelum();
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
            case 2:
                return "SATUAN_BARANG";
            case 3:
                return "QTY_MAX_BARANG";
            case 4:
                return "QTY_MIN_BARANG";
            case 5:
                return "RITEL_SETELAH_PPN";
            case 6:
                return "RITEL_SEBELUM_PPN";
            case 7:
                return "BELI_SETELAH_PPN";
            case 8:
                return "BELI_SEBELUM_PPN";
            default:
                return "";
        }
    }
}
