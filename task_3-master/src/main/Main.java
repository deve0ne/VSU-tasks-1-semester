package main;

import exceptions.OutOfRangeException;
import figures.Point;
import tests.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Picture picture = new Picture();

        Test test = new Test(picture);

        if (!test.isProgramCorrect())
            return;

        double x = readCoord("Введите x (в диапазоне от -10 до 10 включительно).");
        double y = readCoord("Введите y (в диапазоне от -10 до 10 включительно).");

        Color color = picture.getColor(new Point(x, y));

        System.out.println("Введёная точка попадает в " + color.getLocalizedText() + " поле.");
    }

    private static double readCoord(String textToUser) {
        Scanner input = new Scanner(System.in);

        double num = 0;
        boolean isCorrect = false;

        //Проверка на правильность ввода.
        while (!isCorrect) {
            System.out.println(textToUser);

            isCorrect = true;

            try {
                num = input.nextDouble();

                //Проверка на попадание точки в ограничения координатной плоскости.
                if (num < -10 || num > 10)
                    throw new OutOfRangeException();

            } catch (InputMismatchException e) {
                System.out.println("Вы ввели недопустимое число, повторите попытку.\n");
                isCorrect = false;

            } catch (OutOfRangeException e) {
                System.out.println(e.getMessage() + "\n");
                isCorrect = false;
            }

            //Чтобы try catch не выполнялось бесконечно.
            input.nextLine();
        }

        return num;
    }


}
