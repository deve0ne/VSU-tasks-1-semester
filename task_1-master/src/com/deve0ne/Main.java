package com.deve0ne;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// 17. (*) Даны различных целых 3 числа.
// Выбрать из этих чисел среднее по значению (например, для чисел 8, 5, 100 правильным ответом будет 8).
// Использовать методы Math.Min(...) и Math.Max(...), условный оператор нельзя, только присваивания.

public class Main {
    public static void main(String[] args) {
        int averageNum = findAverageAmongThreeNum();

        System.out.println("Средним по значению числом является " + averageNum + ".");
    }

    private static int findAverageAmongThreeNum() {
        List<Integer> numbers = readNumbersToCompare();

        //Нахождение максимального числа в двух парах чисел.
        int biggestInFirstPair = findBiggestInPair(numbers.get(0), numbers.get(1));
        int biggestInSecondPair = findBiggestInPair(numbers.get(1), numbers.get(2));
        int max = findBiggestInPair(biggestInFirstPair, biggestInSecondPair);

        //Удаление максимального числа из List'a.
        numbers.remove((Integer) max);

        //Нахождение максимального числа среди двух оставшихся чисел. Оно и будет средним среди исходных трех.
        return findBiggestInPair(numbers.get(0), numbers.get(1));
    }

    private static List<Integer> readNumbersToCompare() {
        Scanner input = new Scanner(System.in);
        List<Integer> tempList = new ArrayList<>();

        System.out.println("Последовательно введите 3 различных целых числа:");

        while (tempList.size() != 3) {
            int tempNum = 0;
            boolean valid = true;

            //Проверка на правильность ввода.
            try {
                tempNum = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели недопустимое число, повторите попытку.");
                valid = false;
                input.nextLine();
            }

            if (valid)
                tempList.add(tempNum);
        }
        return tempList;
    }

    private static int findBiggestInPair(int x, int y) {
        //Математическая функция, находящая макс. число.
        return (int)((x + y) + Math.sqrt(Math.pow(x - y, 2))) / 2;
    }
}
