package com.deveone.gui.jtableutils;

import com.deveone.core.CoreLogic;

public class GameNumberedTableModel extends NumberedTableModel {
    CoreLogic coreLogic;

    public GameNumberedTableModel(CoreLogic coreLogic) {
        this.coreLogic = coreLogic;
    }

    //Меняет значение и в JTable, и в самом массиве игрового поля.
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String strValue = (String) aValue;

        if (aValue.equals("") || aValue.equals("null")) {
            coreLogic.setGameFieldCell(rowIndex, columnIndex - 1, null);
            super.setValueAt(aValue, rowIndex, columnIndex);

        } else if (strValue.equals("0") || strValue.equals("1")) {
            coreLogic.setGameFieldCell(rowIndex, columnIndex - 1, Integer.parseInt(strValue));
            super.setValueAt(aValue, rowIndex, columnIndex);
        }
    }

    //Для случаев, когда значение нужно изменить только в JTable.
    public void setTableValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }
}
