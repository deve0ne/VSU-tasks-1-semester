package gui;

import core.CoreLogic;
import core.FileRW;
import core.Student;
import gui.jtable.StandardCellRenderer;
import gui.jtable.StudentsTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class MainFrame extends JFrame {
    private JTextField minScoreField;
    private JTextField minStudentOnCourseField;
    private JPanel mainPanel;
    private JTable table;
    private JButton expelButton;
    private JButton loadFromFileButton;
    private JButton saveToFileButton;
    private JButton clearTableButton;

    private StudentsTableModel tableModel;
    private JFileChooser fileChooser;
    private final CoreLogic coreLogic = new CoreLogic();

    public MainFrame() {
        super();

        configureFrame();
        createTable();
        createFileChooser();
        createButtonListeners();
        createTextFieldsListeners();

        pack();
        setVisible(true);
    }

    private void configureFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        setTitle("Отчислятор студентов 3000");
    }

    private void createTable() {
        tableModel = new StudentsTableModel();
        table.setModel(tableModel);

        tableModel.setColumnCount(5);
        tableModel.setRowCount(1);

        table.setRowHeight(30);
        table.getColumn(table.getColumnName(1)).setMinWidth(200);

        createCellRenders(coreLogic.getMinScore());
    }

    private void createCellRenders(int minScore) {
        table.setDefaultRenderer(table.getColumnClass(0), new StandardCellRenderer(minScore));
        table.setDefaultRenderer(table.getColumnClass(1), new StandardCellRenderer(minScore));
        table.repaint();
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
        loadFromFileButton.addActionListener(o -> {
            if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = fileChooser.getSelectedFile().getPath();
                    coreLogic.fillStudents(FileRW.readListFromFile(path));
                } catch (FileNotFoundException exception) {
                    System.err.println("Файл не найден");
                }

                writeArrToJTable(coreLogic.getStudentsArr());
            }
        });

        saveToFileButton.addActionListener(o -> {
            if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();

                if (!filePath.toLowerCase().endsWith(".txt"))
                    filePath += ".txt";

                FileRW.writeArrToFile(filePath, coreLogic.getStudentsArr());
            }
        });

        clearTableButton.addActionListener(o -> createTable());

        expelButton.addActionListener(o -> {
            coreLogic.expelLowScoreStudents();
            writeArrToJTable(coreLogic.getStudentsArr());
        });
    }

    private void createTextFieldsListeners() {
        minScoreField.addActionListener(o -> {
            String enteredText = o.getActionCommand();
            int enteredInt;

            try {
                enteredInt = Integer.parseInt(enteredText);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            coreLogic.setMinScore(enteredInt);
            createCellRenders(coreLogic.getMinScore());
        });

        minStudentOnCourseField.addActionListener(o -> {
            String enteredText = o.getActionCommand();
            int enteredInt;

            try {
                enteredInt = Integer.parseInt(enteredText);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            coreLogic.setMinStudentsOnCourse(enteredInt);
        });
    }

    private void writeArrToJTable(Student[][] studentsArr) {
        //Пересоздаём таблицу, чтобы обнулить предыдущие значения.
        createTable();

        int row = 0;

        for (Student[] students : studentsArr) {
            for (Student student : students) {
                if (student == null)
                    break;

                String[] studentParams = new String[]{student.getFullName(), student.getStringGender(),
                        Integer.toString(student.getCourse()), Integer.toString(student.getAverageScore())};

                for (int column = 0; column < studentParams.length; column++)
                    tableModel.setValueAt(studentParams[column], row, column + 1);

                row++;
            }
        }

        tableModel.fireTableDataChanged();
    }
}
