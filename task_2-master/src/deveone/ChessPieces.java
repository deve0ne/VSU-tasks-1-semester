package deveone;

import java.util.ArrayList;
import java.util.List;

public class ChessPieces {
    public static String whichPiecesCanMove(int[] square1, int[] square2) {
        int xDistance = Math.abs(square1[0] - square2[0]);
        int yDistance = Math.abs(square1[1] - square2[1]);

        boolean onSameVertical = xDistance == 0;
        boolean onSameHorizontal = yDistance == 0;
        boolean onSameDiagonal = xDistance == yDistance;
        boolean withinOneSquareRadius = (xDistance < 2) && (yDistance < 2);

        List<String> matchingPieces = new ArrayList<>();

        if (canPawnMove(square1, square2, onSameVertical))
            matchingPieces.add("пешка");

        if (canBishopMove(onSameDiagonal))
            matchingPieces.add("слон");

        if (canKnightMove(xDistance, yDistance))
            matchingPieces.add("конь");

        if (canRookMove(onSameHorizontal, onSameVertical))
            matchingPieces.add("ладья");

        if (canQueenMove(onSameVertical, onSameHorizontal, onSameDiagonal, withinOneSquareRadius))
            matchingPieces.add("ферзь");

        if (canKingMove(withinOneSquareRadius))
            matchingPieces.add("король");

        if (matchingPieces.size() == 0) {
            return "";
        }

        StringBuilder answerText = new StringBuilder();
        for (String piece : matchingPieces)
            answerText.append(piece).append(", ");

        answerText.delete(answerText.length() - 2, answerText.length());

        return answerText.toString();
    }

    private static boolean canPawnMove(int[] square1, int[] square2, boolean onSameVertical) {
        boolean pawnOnStartPos = square1[1] == 2;
        int yDistance = square2[1] - square1[1];

        return onSameVertical && (yDistance == 1 || (yDistance == 2 && pawnOnStartPos));
    }

    private static boolean canBishopMove(boolean onSameDiagonal) {
        return onSameDiagonal;
    }

    private static boolean canKnightMove(int xDistance, int yDistance) {
        return (xDistance == 2 && yDistance == 1) || (xDistance == 1 && yDistance == 2);
    }

    private static boolean canRookMove(boolean onSameHorizontal, boolean onSameVertical) {
        return onSameHorizontal || onSameVertical;
    }

    private static boolean canKingMove(boolean withinOneSquareRadius) {
        return withinOneSquareRadius;
    }

    private static boolean canQueenMove(boolean onSameVertical, boolean onSameHorizontal,
                                        boolean onSameDiagonal, boolean withinOneSquareRadius) {
        return onSameVertical || onSameHorizontal || onSameDiagonal || withinOneSquareRadius;
    }
}
