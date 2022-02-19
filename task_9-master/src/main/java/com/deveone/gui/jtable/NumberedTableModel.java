package com.deveone.gui.jtable;

import javax.swing.table.DefaultTableModel;

public class NumberedTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 || rowIndex == 1) return false;

        return super.isCellEditable(rowIndex, columnIndex - 1);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) return Integer.class;

        return super.getColumnClass(columnIndex - 1);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            if (rowIndex == 0)
                return "Исходная последовательность";
            else
                return "Ответ";
        }

        return super.getValueAt(rowIndex, columnIndex - 1);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return;

        String strValue = (String) aValue;

        if (strValue.matches("[-+]?[0-9]+")) {
            if (columnIndex == getColumnCount() - 1)
                setColumnCount(getColumnCount() + 1);

            super.setValueAt(aValue, rowIndex, columnIndex - 1);
        } else if (strValue.isEmpty()) {
            super.setValueAt(aValue, rowIndex, columnIndex - 1);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) return "";

        return Integer.toString(columnIndex);
    }
}