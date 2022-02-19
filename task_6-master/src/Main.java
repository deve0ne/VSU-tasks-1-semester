import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double numX = readDouble("Введите число x:");
        double indexN = readDouble("Введите n (номер члена ряда):");
        double numE = readDouble("Введите число e:");

        double sumToIndexN = getSeqSumToSpecifiedIndex(numX, indexN);
        double sumGreaterE = getSeqSumToSpecifiedNum(numX, numE);
        double sumGreaterEDiv10 = getSeqSumToSpecifiedNum(numX, numE / 10);
        double mathSum = Math.sinh(numX);

        printAnswer(sumToIndexN, sumGreaterE, sumGreaterEDiv10, mathSum);
    }

    private static double readDouble(String text) {
        Scanner input = new Scanner(System.in);

        System.out.println(text);

        double tempNum = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                tempNum = input.nextDouble();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели недопустимое число, повторите попытку.");
                input.nextLine();
            }
        }

        return tempNum;
    }

    private static double getSeqSumToSpecifiedNum(double numX, double borderNum) {
        double tempNum = numX;
        double result = tempNum;

        /*
        1. Счётчик i ещё является одновременно и степенью числителя, и основанием факториала в знаменателе.
        2. Поскольку последовательность является убывающей, цикл завершается по достижении
           минимального значения double.
        */
        for (int i = 3; tempNum > Double.MIN_VALUE; i += 2) {
            tempNum = (tempNum * numX * numX) / (i * (i - 1));

            if (tempNum < borderNum)
                break;

            result += tempNum;
        }

        return result;
    }

    private static double getSeqSumToSpecifiedIndex(double numX, double borderIndex) {
        double tempNum = numX;
        double result = tempNum;

        for (int i = 3, j = 0;
             tempNum > Double.MIN_VALUE && j < borderIndex;
             i += 2, j++) {
            tempNum = (tempNum * numX * numX) / (i * (i - 1));
            result += tempNum;
        }

        return result;
    }

    //Не вижу смысла в этой задаче разбивать вывод на 4 метода.
    private static void printAnswer(double sumToIndexN, double sumGreaterE,
                                    double sumGreaterEDiv10, double mathSum) {
        System.out.println("1) Сумма n слагаемых заданного вида: " + sumToIndexN + ";");
        System.out.println("2) Сумма слагаемых, по абсолютной величине больших e: " + sumGreaterE + ";");
        System.out.println("3) Сумма слагаемых, по абсолютной величине больших e/10: " + sumGreaterEDiv10 + ";");
        System.out.println("4) Значение функции с помощью метода Math: " + mathSum + ".");
    }
}
