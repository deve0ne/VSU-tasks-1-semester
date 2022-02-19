package com.deveone.gui;

import com.deveone.core.CoreLogic;
import com.deveone.core.FileRW;
import com.deveone.gui.jtable.MyCellRenderer;
import com.deveone.gui.jtable.NumberedTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame{
    private JTable table;
    private JPanel mainPanel;
    private JButton findMaxConsSeqButton;
    private JButton readFromFileButton;
    private JButton writeToFileButton;

    private NumberedTableModel tableModel;
    private JFileChooser fileChooser;

    public MainFrame() {
        super();

        configureFrame();
        createTable();
        createFileChooser();
        createButtonListeners();

        pack();
        setVisible(true);
    }

    private void configureFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
    }

    private void createTable() {
        tableModel = new NumberedTableModel();
        table.setModel(tableModel);

        tableModel.setColumnCount(2);
        tableModel.setRowCount(2);

        table.setRowHeight(30);

        table.setDefaultRenderer(table.getColumnClass(0), new MyCellRenderer());
        table.setDefaultRenderer(table.getColumnClass(1), new MyCellRenderer());
    }

    private void createFileChooser() {
        fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "*.txt";
            }
        });

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    private void createButtonListeners() {
        findMaxConsSeqButton.addActionListener(o -> {
            findMaxLengthConsecutiveSeq();
            writeListToJTableRow(1, findMaxLengthConsecutiveSeq());
        });

        readFromFileButton.addActionListener(o -> {
            if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                List<Integer> list;

                try {
                    list = FileRW.readListFromFile(fileChooser.getSelectedFile().getPath());
                } catch (FileNotFoundException exception) {
                    System.err.println("Файл не найден");
                    return;
                }
                //Пересоздаём таблицу, чтобы обнулить количество столбцов.
                createTable();
                writeListToJTableRow(0, list);
            }
        });

        writeToFileButton.addActionListener(o -> {
            if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();

                if (!filePath.toLowerCase().endsWith(".txt"))
                    filePath += ".txt";

                List<Integer> list = getListFromJTableRow(1);

                FileRW.writeListToFile(filePath, list);
            }
        });
    }

    private List<Integer> findMaxLengthConsecutiveSeq() {
        List<Integer> sequence = getListFromJTableRow(0);

        if (sequence.isEmpty())
            return sequence;

        return CoreLogic.findMaxLengthConsecutiveSeq(sequence);
    }

    private List<Integer> getListFromJTableRow(int row) {
        List<Integer> list = new ArrayList<>();

        for (int column = 1; column < tableModel.getColumnCount() - 1; column++) {
            String strValue = (String) tableModel.getValueAt(row, column);

            if (strValue.isEmpty())
                break;

            list.add(Integer.parseInt(strValue));
        }


        return list;
    }

    private void writeListToJTableRow(int row, List<Integer> list) {
        clearJTableRow(row);

        for (int i = 0; i < list.size(); i++)
            tableModel.setValueAt(list.get(i).toString(), row, i+1);

        //Почему-то на последней итерации цикла JTable не обновляет таблицу. Для этого обновляем её вручную.
        tableModel.fireTableDataChanged();
    }

    private void clearJTableRow(int row) {
        for (int column = 1; column < tableModel.getColumnCount() - 1; column++)
            tableModel.setValueAt("", row, column);

        tableModel.fireTableDataChanged();
    }
}
