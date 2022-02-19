package com.deveone.console;

import com.deveone.core.CoreLogic;
import com.deveone.core.FileRW;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) {
        InputArgs parsedArgs = parseArgs(args);

        List<Integer> inputList;
        try {
            inputList = getListFromArgs(parsedArgs);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
            return;
        }

        List<Integer> maxLengthSequence = CoreLogic.findMaxLengthConsecutiveSeq(inputList);

        printAnswer(inputList, maxLengthSequence);
    }

    private static List<Integer> getListFromArgs(InputArgs args) throws FileNotFoundException {
        List<Integer> inputList;

        if (args.readFromFile()) {
            inputList = FileRW.readListFromFile(args.filePath());
        } else {
            String[] splitStrs = args.integerSequence().split(" ");
            Integer[] integerArr = Arrays.stream(splitStrs).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            inputList = new ArrayList<>(List.of(integerArr));
        }

        return inputList;
    }

    private static InputArgs parseArgs(String[] args) {
        if (args.length == 0)
            args = new String[]{readIntSeqFromUser()};

        InputArgs parsedArgs;
        if (args[0].equals("--file") || args[0].equals("-f"))
            parsedArgs = new InputArgs(true, args[1], "");
        else
            parsedArgs = new InputArgs(false, "", args[0]);

        return parsedArgs;
    }

    private static String readIntSeqFromUser() {
        Scanner input = new Scanner(System.in);

        String inputLine = "";

        while (inputLine.isEmpty()) {
            System.out.println("Введите последовательность чисел без запятых и переносов строк:");
            inputLine = input.nextLine();
        }

        return inputLine;
    }

    private static void printAnswer(List<Integer> originalList, List<Integer> answer) {
        System.out.println("\nИсходный отсортированный массив:\n" + originalList);
        System.out.println("Максимально длинной последовательностью подряд идущих элементов в данном массиве является:\n" + answer);
    }
}
