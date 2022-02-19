package com.deveone.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileRW {
    CoreLogic coreLogic;

    public FileRW(CoreLogic coreLogic) {
        this.coreLogic = coreLogic;
    }

    public void readGamefieldFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);

        coreLogic.clearGameField();

        for (int i = 0; fileScanner.hasNextLine(); i++) {
            String strRow = fileScanner.nextLine();
            strRow = strRow.replaceAll("-", "null");
            Stream<String> strRowStream = Arrays.stream(strRow.split(" "));

            Integer[] row = strRowStream.map(strNumber ->
                            strNumber.equals("null") ? null : Integer.valueOf(strNumber))
                    .toArray(Integer[]::new);

            for (int j = 0; j < row.length; j++) {
                coreLogic.setGameFieldCell(i, j, row[j]);
            }
        }
    }

    public void writeGamefieldToFile(String filePath) {
        File file = new File(filePath);
        Integer[][] gamefield = coreLogic.getGameFieldArr();

        FileWriter writer;
        try {
            writer = new FileWriter(file);

            for (Integer[] row : gamefield) {
                for (Integer value : row)
                    writer.write(value == null ? "- " : value + " ");
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
