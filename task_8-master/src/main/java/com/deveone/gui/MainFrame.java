package com.deveone.gui;

import com.deveone.core.CoreLogic;
import com.deveone.core.FileRW;
import com.deveone.core.WinSituations;
import com.deveone.gui.jtableutils.GameNumberedTableModel;
import com.deveone.gui.jtableutils.MyCellRenderer;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JTable gameField;
    private JScrollPane tablePane;
    private JButton minusRowButton;
    private JButton minusColumnButton;
    private JButton plusColumnButton;
    private JButton plusRowButton;
    private JButton checkWinButton;
    private JButton readFileButton;
    private JButton writeFileButton;
    private JTextPane winTextPane;

    private final CoreLogic coreLogic = new CoreLogic();
    private final FileRW fileRW = new FileRW(coreLogic);
    private JFileChooser fileChooser;
    private GameNumberedTableModel tableModel;

    public MainFrame() {
        super();

        configureFrame();
        createTable();
        createFileChooser();
        createButtonListeners();
        createTextArea();

        pack();
        setVisible(true);
    }

    private void configureFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(700, 500));
        setTitle("5 in a row game");
    }

    private void createTable() {
        tableModel = new GameNumberedTableModel(coreLogic);
        gameField.setModel(tableModel);

        tableModel.setColumnCount(6);
        tableModel.setRowCount(5);

        gameField.setRowHeight(30);

        gameField.setDefaultRenderer(gameField.getColumnClass(0), new MyCellRenderer());
        gameField.setDefaultRenderer(gameField.getColumnClass(1), new MyCellRenderer());
    }

    private void createTextArea() {
        StyledDocument doc = new DefaultStyledDocument();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();

        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attributeSet, 20);

        doc.setParagraphAttributes(0, doc.getLength(), attributeSet, false);

        winTextPane.setStyledDocument(doc);
        winTextPane.setEditable(false);
    }

    private void createButtonListeners() {
        minusRowButton.addActionListener(e -> {
            int rowCount = tableModel.getRowCount();

            if (rowCount - 1 > 4)
                tableModel.setRowCount(rowCount - 1);
        });

        minusColumnButton.addActionListener(e -> {
            int columnCount = tableModel.getColumnCount();

            if (columnCount - 1 > 5)
                tableModel.setColumnCount(columnCount - 1);
        });

        plusRowButton.addActionListener(e -> tableModel.setRowCount(tableModel.getRowCount() + 1));

        plusColumnButton.addActionListener(e -> tableModel.setColumnCount(tableModel.getColumnCount() + 1));

        checkWinButton.addActionListener(e -> {
            WinSituations winSituation = coreLogic.whichPlayerWin();

            switch (winSituation) {
                case PLAYER_0_WIN -> winTextPane.setText("Выиграли нули.");
                case PLAYER_1_WIN -> winTextPane.setText("Выиграли единицы.");
                case DRAW -> winTextPane.setText("Ничья.");
                case NO_WINNERS -> winTextPane.setText("На доске нет победителей.");
            }
        });

        readFileButton.addActionListener(e -> {
            if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                try {
                    fileRW.readGamefieldFromFile(fileChooser.getSelectedFile().getPath());
                } catch (FileNotFoundException exception) {
                    System.err.println("Файл не найден");
                }

                writeArrToJTable(coreLogic.getGameFieldArr());
            }
        });

        writeFileButton.addActionListener(e -> {
            if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();

                if (!filePath.toLowerCase().endsWith(".txt"))
                    filePath += ".txt";

                fileRW.writeGamefieldToFile(filePath);
            }
        });
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

    private void writeArrToJTable(Integer[][] array) {
        //Пересоздаём таблицу, чтобы обнулить предыдущие значения.
        createTable();

        for (int currRow = 0; currRow < array.length; currRow++) {
            int rowCount = tableModel.getRowCount();
            if (currRow >= rowCount)
                tableModel.setRowCount(rowCount + 1);

            for (int currColumn = 0; currColumn < array[currRow].length; currColumn++) {
                //1 столбец отведен под номер строки, поэтому его не считаем.
                int columnCount = tableModel.getColumnCount() - 1;
                if (currColumn >= columnCount)
                    tableModel.setColumnCount(columnCount + 2);

                Integer value = array[currRow][currColumn];

                if (value == null)
                    tableModel.setTableValueAt(null, currRow, currColumn + 1);
                else
                    tableModel.setTableValueAt(value.toString(), currRow, currColumn + 1);
            }
        }
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 3, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        minusRowButton = new JButton();
        minusRowButton.setText("−");
        panel1.add(minusRowButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        plusRowButton = new JButton();
        plusRowButton.setText("+");
        panel1.add(plusRowButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        plusColumnButton = new JButton();
        plusColumnButton.setText("+");
        panel1.add(plusColumnButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minusColumnButton = new JButton();
        minusColumnButton.setText("−");
        panel1.add(minusColumnButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tablePane = new JScrollPane();
        tablePane.setForeground(new Color(-1));
        panel1.add(tablePane, new GridConstraints(1, 1, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        gameField = new JTable();
        tablePane.setViewportView(gameField);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        readFileButton = new JButton();
        readFileButton.setText("Загрузить игровое поле из файла");
        panel2.add(readFileButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        writeFileButton = new JButton();
        writeFileButton.setText("Записать игровое поле в файл");
        panel2.add(writeFileButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkWinButton = new JButton();
        checkWinButton.setText("Найти победителя");
        panel3.add(checkWinButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel3.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        winTextPane = new JTextPane();
        panel3.add(winTextPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}