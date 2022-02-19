package gui.jtable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class StandardCellRenderer extends DefaultTableCellRenderer {
    private final int minScore;

    public StandardCellRenderer(int minScore) {
        this.minScore = minScore;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(CENTER);
        setValue(value);
        setFont(table.getFont());
        setOpaque(true);
        setBackground(Color.WHITE);

        if (column == 0) {
            JTableHeader header = table.getTableHeader();

            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
        }

        String strStudScore = (String) table.getValueAt(row, 4);
        if (strStudScore != null)
            if (Integer.parseInt(strStudScore) <= minScore) {
                setBackground(Color.RED);
            }

        return this;
    }
}