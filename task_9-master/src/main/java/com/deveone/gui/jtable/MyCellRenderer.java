package com.deveone.gui.jtable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MyCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(CENTER);
        setValue(value);
        setFont(table.getFont());
        setOpaque(true);

        if (column == 0) {
            JTableHeader header = table.getTableHeader();

            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
        }

        return this;
    }
}