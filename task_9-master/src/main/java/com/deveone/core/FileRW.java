package com.deveone.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRW {
    public static List<Integer> readListFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);

        List<Integer> list = new ArrayList<>();

        while (fileScanner.hasNext())
            list.add(fileScanner.nextInt());

        return list;
    }

    public static void writeListToFile(String filePath, List<Integer> list) {
        File file = new File(filePath);

        FileWriter writer;
        try {
            writer = new FileWriter(file);

            for (Integer value : list)
                writer.write(value == null ? "- " : value + " ");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
