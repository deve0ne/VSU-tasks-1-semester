package deveone;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] squares = readTwoSquares();

        int[] square1Coords = convertNotationToCoordinates(squares[0]);
        int[] square2Coords = convertNotationToCoordinates(squares[1]);

        String answerText = ChessPieces.whichPiecesCanMove(square1Coords, square2Coords);

        if (answerText.equals(""))
            System.out.printf("Из клетки %s в клетку %s не может сделать ход никакая фигура.", squares[0], squares[1]);

        System.out.printf("Из клетки %s в клетку %s могут совершить ход такие фигуры, как %s.",
                squares[0], squares[1], answerText);
    }

    private static String[] readTwoSquares() {
        String square1 = readSquare("Введите клетку, из которой делается ход," +
                " в соответствии с шахматной нотацией (К примеру \"A1\").");

        String square2 = readSquare("Введите клетку, на которую делается ход," +
                " в соответствии с шахматной нотацией (К примеру \"B2\").");

        return new String[]{square1, square2};
    }

    private static String readSquare(String textToUser) {
        Scanner input = new Scanner(System.in);

        String allowedChars = "ABCDEFGH";
        String allowedNums = "12345678";
        String square = "";

        System.out.println(textToUser);

        while (square.length() != 2) {
            square = input.next().toUpperCase();

            if (square.length() != 2 ||
                    !allowedChars.contains(String.valueOf(square.charAt(0))) ||
                    !allowedNums.contains(String.valueOf(square.charAt(1)))) {
                System.out.println("Вы ввели недопустимую клетку, повторите попытку.");
                square = "";
            }
        }

        return square;
    }

    private static int[] convertNotationToCoordinates(String square) {
        String tempXCoord = String.valueOf((square.charAt(0) - 'A' + 1));
        String tempYCoord = String.valueOf(square.charAt(1));

        int xCoord = Integer.parseInt(tempXCoord);
        int yCoord = Integer.parseInt(tempYCoord);

        return new int[]{xCoord, yCoord};
    }
}

