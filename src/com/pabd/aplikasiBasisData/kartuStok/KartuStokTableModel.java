/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pabd.aplikasiBasisData.kartuStok;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class KartuStokTableModel extends AbstractTableModel {

    private List<KartuStok> kartuStokList = new ArrayList<KartuStok>();

    public KartuStokTableModel(List<KartuStok> pKartuStokList) {
        this.kartuStokList = pKartuStokList;
    }

    public void deleteKategori(int row) {
        kartuStokList.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateKategori(int row, KartuStok pKartuStok) {
        kartuStokList.set(row, pKartuStok);
        fireTableRowsUpdated(row, row);
    }

    public void addKategori(KartuStok pKartuStok) {
        kartuStokList.add(pKartuStok);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public int getRowCount() {
        return kartuStokList.size();
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        KartuStok kartuStok = kartuStokList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kartuStok.getmTanggal();
            case 1:
                return kartuStok.getmNomorBukti();
            case 2:
                return kartuStok.getmKeterangan();
            case 3:
                return kartuStok.getmMasuk();
            case 4:
                return kartuStok.getmKeluar();
            case 5:
                return kartuStok.getmSaldo();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "TANGGAL";
            case 1:
                return "NOMOR_BUKTI";
            case 2:
                return "KETERANGAN";
            case 3:
                return "MASUK";
            case 4:
                return "KELUAR";
            case 5:
                return "SALDO";
            default:
                return "";
        }
    }

}
