package main;

import figures.*;

import java.util.ArrayList;
import java.util.List;

public class Picture {
    public Color getColor(Point pointToCheck) {
        List<Condition> conditionList = checkGettingInFigures(pointToCheck);

        if (conditionList.contains(Condition.IN_TOP_RECT))
            return checkColorInTopRect(conditionList);

        if (conditionList.contains(Condition.IN_VERTICAL_PARABOLA))
            return checkColorInVerticalParabola(conditionList);

        if (conditionList.contains(Condition.IN_MIDDLE_RECT))
            return checkColorInMiddleRect(conditionList);

        if (conditionList.contains(Condition.IN_HORIZONTAL_PARABOLA))
            return checkColorInHorizontalParabola(conditionList);

        if (conditionList.contains(Condition.IN_LEFT_TOP_QUARTER))
            return Color.WHITE;

        if (conditionList.contains(Condition.ABOVE_LINE))
            return Color.ORANGE;

        return Color.BLUE;
    }

    //Проверка точки на попадание в фигуры.
    private List<Condition> checkGettingInFigures(Point pointToCheck) {
        //Создание объектов фигур с картинки.
        Rectangle topRectangle = new Rectangle(0, 3, 5, 10);
        Rectangle middleRectangle = new Rectangle(-5, -3, 3, 4);

        Line line = new Line(-2, -3, 3, 2);

        VerticalParabola verticalParabola = new VerticalParabola(4, -5, 0.25);
        HorizontalParabola horizontalParabola = new HorizontalParabola(-2, -4, -0.25);

        //Заполнение List'а состояний попадания в фигуры.
        List<Condition> conditionList = new ArrayList<>();

        if (topRectangle.isPointInRectangle(pointToCheck))
            conditionList.add(Condition.IN_TOP_RECT);

        if (middleRectangle.isPointInRectangle(pointToCheck))
            conditionList.add(Condition.IN_MIDDLE_RECT);

        if (line.isPointAboveLine(pointToCheck))
            conditionList.add(Condition.ABOVE_LINE);

        if (verticalParabola.isPointInParabola(pointToCheck))
            conditionList.add(Condition.IN_VERTICAL_PARABOLA);

        if (horizontalParabola.isPointInParabola(pointToCheck))
            conditionList.add(Condition.IN_HORIZONTAL_PARABOLA);

        if (pointToCheck.x() < 0 && pointToCheck.y() > 0)
            conditionList.add(Condition.IN_LEFT_TOP_QUARTER);

        return conditionList;
    }

    private Color checkColorInTopRect(List<Condition> conditionList) {
        if (conditionList.contains(Condition.IN_MIDDLE_RECT))
            return Color.GRAY;

        if (!conditionList.contains(Condition.ABOVE_LINE))
            return Color.GREEN;

        return Color.WHITE;
    }

    private Color checkColorInVerticalParabola(List<Condition> conditionList) {
        if (conditionList.contains(Condition.IN_MIDDLE_RECT)) {
            if (conditionList.contains(Condition.ABOVE_LINE))
                return Color.ORANGE;

            return Color.BLUE;
        }

        if (conditionList.contains(Condition.ABOVE_LINE))
            return Color.BLUE;

        if (conditionList.contains(Condition.IN_LEFT_TOP_QUARTER))
            return Color.YELLOW;

        return Color.GRAY;
    }

    private Color checkColorInMiddleRect(List<Condition> conditionList) {
        if (conditionList.contains(Condition.ABOVE_LINE)) {
            if (conditionList.contains(Condition.IN_HORIZONTAL_PARABOLA))
                return Color.GREEN;

            return Color.YELLOW;
        }

        return Color.ORANGE;
    }

    private Color checkColorInHorizontalParabola(List<Condition> conditionList) {
        if (conditionList.contains(Condition.ABOVE_LINE))
            return Color.YELLOW;

        return Color.WHITE;
    }

    private enum Condition {
        IN_TOP_RECT,
        IN_MIDDLE_RECT,
        ABOVE_LINE,
        IN_VERTICAL_PARABOLA,
        IN_HORIZONTAL_PARABOLA,
        IN_LEFT_TOP_QUARTER
    }
}
