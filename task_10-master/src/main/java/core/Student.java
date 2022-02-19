package core;

public class Student {
    private String fullName;
    private Gender gender;
    private int course;
    private int averageScore;

    public enum Gender {
        MALE,
        FEMALE
    }

    public Student(String fullName, Gender gender, int course, int averageScore) {
        this.fullName = fullName;
        this.gender = gender;
        this.course = course;
        this.averageScore = averageScore;
    }

    public String getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getStringGender() {
        return gender == Gender.MALE ? "М" : "Ж";
    }

    public int getCourse() {
        return course;
    }

    public int getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return "ФИО: '" + fullName + '\'' +
                ", пол: " + getStringGender() +
                ", курс: " + course +
                ", средний балл: " + averageScore +
                '}';
    }
}
