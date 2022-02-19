package com.deveone.core;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CoreLogic {
    private final ArrayList<ArrayList<Integer>> gameField = new ArrayList<>();

    public WinSituations whichPlayerWin() {
        boolean player0Win = isPlayerHave5InRow(0);
        boolean player1Win = isPlayerHave5InRow(1);

        if (player0Win && player1Win)
            return WinSituations.DRAW;
        else if (player0Win)
            return WinSituations.PLAYER_0_WIN;
        else if (player1Win)
            return WinSituations.PLAYER_1_WIN;
        else
            return WinSituations.NO_WINNERS;
    }

    private boolean isPlayerHave5InRow(int player) {
        Integer[][] arrGameField = convertListToArray(gameField);

        DirectionsToCheck[] directions = {DirectionsToCheck.VERTICAL,
                DirectionsToCheck.HORIZONTAL,
                DirectionsToCheck.DIAGONAL_UP,
                DirectionsToCheck.DIAGONAL_DOWN};

        for (int currRow = 0; currRow < arrGameField.length; currRow++) {
            for (int currCol = 0; currCol < arrGameField[currRow].length; currCol++) {

                //Если ячейка пустая или не захвачена игроком, пропускаем её.
                if (!checkCellForValue(arrGameField[currRow][currCol], player))
                    continue;

                //Проверка всех направлений на наличие 5 захваченных ячеек в ряд.
                for (DirectionsToCheck direction : directions) {
                    if (checkOneDirectionWin(direction, arrGameField, currCol, currRow, player))
                        return true;
                }
            }
        }

        return false;
    }

    private static boolean checkOneDirectionWin(DirectionsToCheck direction,
                                                Integer[][] arrGameField, int currCol, int currRow, int player) {
        for (int i = 0; i < 5; i++) {
            if (currRow < 0 || currRow > arrGameField.length - 1 ||
                    currCol < 0 || currCol > arrGameField[0].length - 1)
                return false;

            if (!checkCellForValue(arrGameField[currRow][currCol], player))
                return false;

            currCol += direction.xStep;
            currRow += direction.yStep;
        }

        return true;
    }

    //Так как игровое поле просматривается слева направо и сверху вниз, нет необходимости идти назад.
    private enum DirectionsToCheck {
        HORIZONTAL(1, 0),
        VERTICAL(0, 1),
        DIAGONAL_UP(1, -1),
        DIAGONAL_DOWN(1, 1);

        private final int xStep;
        private final int yStep;

        DirectionsToCheck(int xStep, int yStep) {
            this.xStep = xStep;
            this.yStep = yStep;
        }
    }

    public void setGameFieldCell(int rowIndex, int columnIndex, Integer value) {
        ensureArrayListCapacity(gameField, rowIndex, list -> list.add(new ArrayList<>()));
        ArrayList<Integer> row = gameField.get(rowIndex);

        ensureArrayListCapacity(row, columnIndex, list -> list.add(null));
        row.set(columnIndex, value);
    }

    //Вероятно, не самый интуитивно понятный код, но это единственный способ
    //избежания дублирования кода, который я смог придумать.
    private static <E> void ensureArrayListCapacity(E arrayList, int minIndex, Consumer<E> addPlaceholderToList) {
        int sizeDiff = minIndex - (((ArrayList<?>) arrayList).size() - 1);

        if (sizeDiff > 0) {
            for (int i = 0; i < sizeDiff; i++)
                addPlaceholderToList.accept(arrayList);
        }
    }

    private static boolean checkCellForValue(Integer cellValue, int expectedValue) {
        return cellValue != null && cellValue == expectedValue;
    }

    public Integer[][] getGameFieldArr() {
        return convertListToArray(gameField);
    }

    private static Integer[][] convertListToArray(ArrayList<ArrayList<Integer>> arrList) {
        int rowCount = arrList.size();
        int maxColumnCount = 0;

        //Так как в каждой строке может быть разное количество заполненных столбцов, ищем максимальное их количество.
        for (ArrayList<Integer> element : arrList) {
            int elSize = element.size();
            if (elSize > maxColumnCount)
                maxColumnCount = elSize;
        }

        Integer[][] newArray = new Integer[rowCount][maxColumnCount];

        for (int i = 0; i < rowCount; i++)
            newArray[i] = arrList.get(i).toArray(newArray[i]);

        return newArray;
    }

    public void clearGameField() {
        gameField.clear();
    }
}
