package com.deveone.core;

import java.util.*;

public class CoreLogic {
    public static List<Integer> findMaxLengthConsecutiveSeq(List<Integer> integerList) {
        integerList.sort(Comparator.naturalOrder());
        integerList = integerList.stream().distinct().toList();

        List<ArrayList<Integer>> splitList = splitListToSequences(integerList);

        return getFirstMaxSizeList(splitList);
    }

    private static List<ArrayList<Integer>> splitListToSequences(List<Integer> arr) {
        List<ArrayList<Integer>> listWithSequences = new ArrayList<>();
        ArrayList<Integer> sequence = new ArrayList<>();

        //Первый элемент в любом случае должен попасть в последовательность.
        sequence.add(arr.get(0));

        for (int i = 1; i < arr.size(); i++) {
            Integer arrValue = arr.get(i);

            if (sequence.get(sequence.size() - 1) == arrValue - 1) {
                sequence.add(arrValue);
            } else {
                listWithSequences.add(new ArrayList<>(sequence));
                sequence.clear();
                sequence.add(arrValue);
            }
        }
        listWithSequences.add(new ArrayList<>(sequence));

        return listWithSequences;
    }

    private static ArrayList<Integer> getFirstMaxSizeList(List<ArrayList<Integer>> list) {
        int maxSize = findMaxListSize(list);
        list = list.stream().filter(o -> o.size() == maxSize).toList();
        
        return list.get(0);
    }

    private static int findMaxListSize(List<ArrayList<Integer>> list) {
        int maxSize = 0;

        for (ArrayList<Integer> row: list) {
            if (row.size() > maxSize)
                maxSize = row.size();
        }

        return maxSize;
    }
}
