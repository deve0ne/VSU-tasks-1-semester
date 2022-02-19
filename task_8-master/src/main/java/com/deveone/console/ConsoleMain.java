package com.deveone.console;

import com.deveone.core.CoreLogic;
import com.deveone.core.FileRW;
import com.deveone.core.WinSituations;

import java.io.FileNotFoundException;

public class ConsoleMain {
    public static void main(String[] args) {
        InputArgs parsedArgs;

        try {
            parsedArgs = parseCmdArgs(args);
        } catch (FileNotSpecifiedException e) {
            e.printStackTrace();
            return;
        }

        CoreLogic coreLogic = new CoreLogic();
        FileRW fileRW = new FileRW(coreLogic);

        try {
            fileRW.readGamefieldFromFile(parsedArgs.pathToInputFile());
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
            return;
        }

        printGamefield(coreLogic.getGameFieldArr());

        printAnswer(coreLogic.whichPlayerWin());
    }

    private static InputArgs parseCmdArgs(String[] args) throws FileNotSpecifiedException {
        InputArgs parsedArgs;

        if (args.length > 0)
            parsedArgs = new InputArgs(args[0]);
        else
            throw new FileNotSpecifiedException();

        return parsedArgs;
    }

    private static class FileNotSpecifiedException extends Exception {
        @Override
        public void printStackTrace() {
            System.err.println("Файл с входными данными не указан.");
        }
    }

    private static void printGamefield(Integer[][] gamefield) {
        System.out.println("\nИгровое поле:");

        for (Integer[] integers : gamefield) {
            for (Integer value : integers)
                System.out.print(value == null ? "- " : value + " ");

            System.out.println();
        }
    }

    private static void printAnswer(WinSituations situation) {
        System.out.println();

        switch (situation) {
            case PLAYER_0_WIN -> System.out.println("Выиграли нули.");
            case PLAYER_1_WIN -> System.out.println("Выиграли единицы.");
            case DRAW -> System.out.println("Ничья.");
            case NO_WINNERS -> System.out.println("На доске нет победителей.");
        }
    }
}
