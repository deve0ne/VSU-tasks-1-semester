package com.deveone.gui.jtableutils;

import javax.swing.table.DefaultTableModel;

public class NumberedTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return false;

        return super.isCellEditable(rowIndex, columnIndex - 1);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) return Integer.class;

        return super.getColumnClass(columnIndex - 1);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return rowIndex + 1;
        return super.getValueAt(rowIndex, columnIndex - 1);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return;

        super.setValueAt(aValue, rowIndex, columnIndex - 1);
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) return "";

        return Integer.toString(columnIndex);
    }
}