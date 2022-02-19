package console;

import core.CoreLogic;
import core.FileRW;
import core.Student;

import java.io.FileNotFoundException;
import java.util.List;

public class ConsoleMain {
    public static void main(String[] args) {
        InputArgs parsedArgs;

        try {
            parsedArgs = parseArgs(args);
        } catch (FileNotSpecifiedException e) {
            e.printStackTrace();
            return;
        }

        CoreLogic coreLogic = new CoreLogic();
        List<Student> studentList;

        try {
            studentList = FileRW.readListFromFile(parsedArgs.filePath());
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
            return;
        }

        coreLogic.fillStudents(studentList);
        coreLogic.setMinScore(parsedArgs.minScore());
        coreLogic.setMinStudentsOnCourse(parsedArgs.minStudentsOnCourse());

        coreLogic.expelLowScoreStudents();

        printStudentsArr(coreLogic.getStudentsArr(), parsedArgs.minScore(), parsedArgs.minStudentsOnCourse());
    }

    private static InputArgs parseArgs(String[] args) throws FileNotSpecifiedException {
        String filePath;
        int minScore = 25;
        int minStudentsOnCourse = 3;

        if (args.length == 0)
            throw new FileNotSpecifiedException();

        if (args[0].equals("--file") || args[0].equals("-f"))
            filePath = args[1];
        else
            throw new FileNotSpecifiedException();

        if (args.length >= 3 && args[2].equals("--score")) {
            try {
                minScore = Integer.parseInt(args[3]);
            } catch (NumberFormatException ignored) {
            }
        }

        if (args.length == 6 && args[4].equals("--min_students")) {
            try {
                minStudentsOnCourse = Integer.parseInt(args[5]);
            } catch (NumberFormatException ignored) {
            }
        }

        return new InputArgs(filePath, minScore, minStudentsOnCourse);
    }


    private static class FileNotSpecifiedException extends Exception {
        @Override
        public void printStackTrace() {
            System.err.println("Файл с входными данными не указан.");
        }
    }

    private static void printStudentsArr(Student[][] studentsArr, int minScore, int minStudentsOnCourse) {
        System.out.println("\nМинимально возможный балл: " + minScore +
                "\nМинимально возможное количество студентов на курсе: " + minStudentsOnCourse +
                "\nСписок студентов после исключения:\n");

        for (Student[] studentsOnCourse : studentsArr) {
            for (Student student : studentsOnCourse) {
                if (student != null)
                    System.out.println(student);
            }
        }

        System.out.println();
    }
}
