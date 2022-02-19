package core;

import java.util.ArrayList;
import java.util.List;

public class CoreLogic {
    private ArrayList<ArrayList<Student>> courseSortedStudentList;
    private int minScore = 25;
    private int minStudentsOnCourse = 3;

    public void fillStudents(List<Student> studentList) {
        courseSortedStudentList = sortStudentsByCourse(studentList);
    }

    private ArrayList<ArrayList<Student>> sortStudentsByCourse(List<Student> studentList) {
        ArrayList<ArrayList<Student>> sortedStudentList = new ArrayList<>();

        studentList.sort((stud1, stud2) -> {
            if (stud1.getCourse() == stud2.getCourse())
                return stud1.getAverageScore() > stud2.getAverageScore() ? -1 : 1;

            return stud1.getCourse() > stud2.getCourse() ? 1 : -1;
        });

        int currCourse = 0;
        for (Student student : studentList) {
            int currStudentCourse = student.getCourse();

            if (currStudentCourse > currCourse) {
                currCourse++;
                sortedStudentList.add(new ArrayList<>());
            }

            sortedStudentList.get(currCourse - 1).add(student);
        }

        return sortedStudentList;
    }

    public void expelLowScoreStudents() {
        for (ArrayList<Student> studentsOnCourse : courseSortedStudentList) {
            for (int i = studentsOnCourse.size() - 1; i > minStudentsOnCourse - 1; i--) {
                Student currStudent = studentsOnCourse.get(i);

                if (currStudent.getAverageScore() > minScore)
                    break;

                studentsOnCourse.remove(currStudent);
            }
        }
    }

    private Student[][] convertStudentsListToArray() {
        int courseCount = courseSortedStudentList.size();
        int maxStudents = 0;

        //Так как на каждом курсе может быть разное количество студентов, ищем максимальное их количество.
        for (ArrayList<Student> studentsOnCourse : courseSortedStudentList) {
            int studentsCount = studentsOnCourse.size();
            if (studentsCount > maxStudents)
                maxStudents = studentsCount;
        }

        Student[][] studentsArr = new Student[courseCount][maxStudents];

        for (int i = 0; i < courseCount; i++)
            studentsArr[i] = courseSortedStudentList.get(i).toArray(studentsArr[i]);

        return studentsArr;
    }

    public Student[][] getStudentsArr() {
        return convertStudentsListToArray();
    }

    public List<Student> getStudentsList() {
        List<Student> students = new ArrayList<>();
        for (ArrayList<Student> studentsOnCourse : courseSortedStudentList)
            students.addAll(studentsOnCourse);

        return students;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMinStudentsOnCourse() {
        return minStudentsOnCourse;
    }

    public void setMinStudentsOnCourse(int minStudentsOnCourse) {
        this.minStudentsOnCourse = minStudentsOnCourse;
    }
}
