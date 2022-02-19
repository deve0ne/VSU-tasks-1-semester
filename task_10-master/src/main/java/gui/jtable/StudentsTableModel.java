package gui.jtable;

import core.Student;

public class StudentsTableModel extends NumberedTableModel {
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String strValue = (String) aValue;

        if (rowIndex == getRowCount() - 1)
            setRowCount(getRowCount() + 1);

        switch (columnIndex) {
            case 2 -> {
                if (strValue.equals("М") || strValue.equals("Ж"))
                    super.setValueAt(aValue, rowIndex, columnIndex);
            }
            case 3 -> {
                if (strValue.matches("[1-4]"))
                    super.setValueAt(aValue, rowIndex, columnIndex);
            }
            case 4 -> {
                if (strValue.matches("^(?:[0-9]|[1-4][0-9]|50)$"))
                    super.setValueAt(aValue, rowIndex, columnIndex);
            }
            default -> super.setValueAt(aValue, rowIndex, columnIndex);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 1 -> "ФИО";
            case 2 -> "Пол";
            case 3 -> "Курс";
            case 4 -> "Средний балл";
            default -> super.getColumnName(columnIndex);
        };
    }
}
