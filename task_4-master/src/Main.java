import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int borderA = readNum("Введите А (левую границу последовательности для проверки).");
        int borderB = readNum("Введите B (правую границу последовательности для проверки).");

        int n = readNum("Введите N (правую границу для подсчёта с единицы кол-ва прогрессий с единицы).");

        int answerInBorders = countProgressionsInBorders(borderA, borderB);
        int answerFromZeroToN = countProgressionsFrom0ToN(n);

        printResults(answerInBorders, answerFromZeroToN);
    }

    private static int readNum(String text) {
        Scanner input = new Scanner(System.in);

        System.out.println(text);

        boolean isValid = false;
        int tempNum = 0;

        while (!isValid) {
            try {
                tempNum = input.nextInt();

                /* Вероятно, неправильно кидать именно InputMismatch,
                 но писать новый класс exсeption'а для такой задачи не вижу смысла. */
                if (tempNum < 0)
                    throw new InputMismatchException();

                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели недопустимое число, повторите попытку.");

                input.nextLine();
            }
        }

        return tempNum;
    }

    private static void printResults(int answerInBorders, int answerFromZeroToN) {
        System.out.println("В указаных границах " + answerInBorders + " последовательности.\n" +
                "C единицы до указанного N " + answerFromZeroToN + " последовательности.");
    }

    //Подсчёт последовательностей в указанном промежутке.
    private static int countProgressionsInBorders(int borderA, int borderB) {
        int progressionsCounter = 0;

        for (int i = borderA; i <= borderB; i++) {
            if (checkArithmeticSeq(i) || checkGeometricSeq(i))
                progressionsCounter++;
        }

        return progressionsCounter;
    }

    //Подсчёт последовательностей c нуля до N.
    private static int countProgressionsFrom0ToN(int N) {
        return countProgressionsInBorders(0, N);
    }

    //Проверка числа на наличие арифметической прогрессии.
    private static boolean checkArithmeticSeq(int num) {
        /* Однозначные и двузначные числа всегда являются членами прогрессии.
         * Дальше в этом методе будут участвовать только числа, длина которых > 2. */
        if (num / 100 == 0)
            return true;

        //Нахождение разности арифметической прогрессии.
        int firstNum = getNumByIndex(num, 1);
        int secondNum = getNumByIndex(num, 2);
        int difference = secondNum - firstNum;

        //Проверка каждой цифры, начиная с третей, на наличие геом. прогресии.
        for (int i = 3; i <= findNumLength(num); i++) {
            int prevNum = getNumByIndex(num, i - 1);
            int currNum = getNumByIndex(num, i);
            int tempDifference = currNum - prevNum;

            if (difference != tempDifference)
                return false;
        }

        return true;
    }

    //Проверка числа на наличие геометрической прогрессии.
    private static boolean checkGeometricSeq(int num) {
        /* Однозначные и двузначные числа всегда являются членами прогрессии.
         * Дальше в этом методе будут участвовать только числа, длина которых > 2. */
        if (num / 100 == 0)
            return true;

        //Нахождение делителя геометрической прогрессии.
        double firstNum = getNumByIndex(num, 1);
        double secondNum = getNumByIndex(num, 2);
        double divider = secondNum / firstNum;

        //Проверка каждой цифры, начиная с третей, на наличие геом. прогресии.
        for (int i = 3; i <= findNumLength(num); i++) {
            double prevNum = getNumByIndex(num, i - 1);
            double currNum = getNumByIndex(num, i);
            double tempDivider = currNum / prevNum;

            if (divider != tempDivider)
                return false;
        }

        return true;
    }

    //Получает цифру числа по индексу. Индексы начинаются с 1.
    private static int getNumByIndex(int num, int index) {
        double tempDivider = Math.pow(10, findNumLength(num) - index);
        return (int) (num / tempDivider % 10);
    }

    //Находит длину числа.
    private static int findNumLength(int num) {
        int length = 0;

        while (num / 10 > 0) {
            num /= 10;
            length++;
        }

        return length + 1;
    }
}
