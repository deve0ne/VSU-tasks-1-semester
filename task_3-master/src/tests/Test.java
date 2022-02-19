package tests;

import figures.Point;
import main.Color;
import main.Picture;

import java.util.ArrayList;
import java.util.List;

public class Test {
    Picture picture;

    public Test(Picture picture) {
        this.picture = picture;
    }

    public boolean isProgramCorrect() {
        List<Boolean> testResults = testProgram();

        if (!testResults.contains(false)) {
            System.out.println("Все тесты успешно пройдены. Программа работает корректно.\n");
            return true;
        }

        printFailedTests(testResults);

        return false;
    }

    //Проводит тесты и возвращает список с их результатами
    private List<Boolean> testProgram() {
        List<Boolean> testResults = new ArrayList<>();

        testResults.add(testPoint(new Point(0, 0), Color.ORANGE));     // 1
        testResults.add(testPoint(new Point(1, 3.5), Color.GRAY));     // 2
        testResults.add(testPoint(new Point(-1, -1), Color.YELLOW));   // 3
        testResults.add(testPoint(new Point(3, 7), Color.WHITE));      // 4
        testResults.add(testPoint(new Point(-10, -3), Color.YELLOW));  // 5
        testResults.add(testPoint(new Point(0, -5), Color.BLUE));      // 6
        testResults.add(testPoint(new Point(-4, -2), Color.GREEN));    // 7
        testResults.add(testPoint(new Point(7, 3), Color.GRAY));       // 8
        testResults.add(testPoint(new Point(-10, -10), Color.ORANGE)); // 9

        return testResults;
    }

    private boolean testPoint(Point pointToCheck, Color correctAnswer) {
        Color programAnswer = picture.getColor(pointToCheck);

        return programAnswer == correctAnswer;
    }

    //Печатает индексы всех проваленных тестов.
    private void printFailedTests(List<Boolean> testResults) {
        StringBuilder failedTestIndexes = new StringBuilder();

        while (testResults.contains(false)) {
            int index = testResults.indexOf(false);

            failedTestIndexes.append(index + 1).append(", ");

            testResults.remove(index);
        }

        //Удаление двух лишних знаков в конце, чтобы всё выглядело красиво.
        failedTestIndexes.delete(failedTestIndexes.length() - 2, failedTestIndexes.length());

        System.out.println("Программа работает некорректно: не пройдены тесты " + failedTestIndexes + ".");
    }
}
