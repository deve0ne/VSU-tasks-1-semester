package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRW {
    public static List<Student> readListFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);

        List<Student> studentList = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] studentParams = line.split(", ");

            studentList.add(createStudentFromParams(studentParams));
        }

        return studentList;
    }

    private static Student createStudentFromParams(String[] studentParams) {
        String studName = studentParams[0];
        Student.Gender studGender = studentParams[1].equals("М") ?
                Student.Gender.MALE : Student.Gender.FEMALE;
        int studCourse = Integer.parseInt(studentParams[2]);
        int studAvgScore = Integer.parseInt(studentParams[3]);

        return new Student(studName, studGender, studCourse, studAvgScore);
    }

    public static void writeArrToFile(String filePath, Student[][] studentsArr) {
        File file = new File(filePath);

        FileWriter writer;
        try {
            writer = new FileWriter(file);

            for (Student[] students : studentsArr) {
                for (Student student : students) {
                    if (student == null)
                        break;

                    String studentStr = student.getFullName() + ", " + student.getStringGender() + ", " +
                            student.getCourse() + ", " + student.getAverageScore();

                    writer.write(studentStr + "\n");
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
