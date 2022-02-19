import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int height = readNum("Введите высоту фигуры.");
        int width = readNum("Введите ширину фигуры.");

        printFigure(height, width);
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
                if (tempNum < 3)
                    throw new InputMismatchException();

                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели недопустимое число, повторите попытку.");

                input.nextLine();
            }
        }

        return tempNum;
    }

    private static void printFigure(int height, int width) {
        printCap(width);
        printBody(height, width);
        printCap(width);
    }

    //Печатает основное тело фигуры.
    private static void printBody(int height, int width) {
        int exclamStartPos = 0;  //Отступ воскл. знаков от левой стенки.
        int exclamCount = 1;     //Кол-во воскл. знаков в текущей итерации.
        int excessExclam = 0;    //Кол-во воскл. знаков, не поместившихся на прошлой строке.

        for (int i = 0; i < height - 2; i++) {

            //Печать воскл. знаков.
            if (excessExclam == 0) {
                excessExclam = printLine(width - 1, exclamStartPos, exclamCount);

                exclamCount++;
                exclamStartPos += exclamCount - 1;
            } else {
                //Печать воскл. знаков, не поместившихся на предыдущей строке.
                exclamStartPos = excessExclam;
                excessExclam = printLine(width - 1, 0, excessExclam);
            }

            //Отступ воскл. знаков от левой стенки не должен превышать ширину поля.
            if (exclamStartPos > width)
                exclamStartPos -= width;
        }
    }

    //Для печати верхней и нижней крышечки.
    private static void printCap(int width) {
        int capSize = width - 1;

        System.out.print(" ");
        printSequenceWithSameSymbols("-", capSize);
        System.out.println(" ");
    }

    //Печать одной линии тела фигуры.
    private static int printLine(int width, int exclamStartPos, int exclamCount) {
        int excessExclam = 0;
        int spacesAfterLastExclam = width - exclamStartPos - exclamCount;

        //Подсчёт воскл. знаков, не помещающихся на строке.
        if (spacesAfterLastExclam < 0)
            excessExclam = Math.abs(spacesAfterLastExclam);

        System.out.print("|");

        //Печать отступа от левой стенки.
        printSequenceWithSameSymbols(" ", exclamStartPos);

        //Печать воскл. знаков.
        printSequenceWithSameSymbols("!", exclamCount - excessExclam);

        //Печать пробелов после воскл. знаков
        printSequenceWithSameSymbols(" ", spacesAfterLastExclam + excessExclam);

        System.out.println("|");

        return excessExclam;
    }

    //Печатает определённое количество одинаковых символов.
    private static void printSequenceWithSameSymbols(String symbol, int length) {
        for (int i = 0; i < length; i++)
            System.out.print(symbol);
    }
}
