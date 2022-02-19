package figures;

public class Line {
    private final Point firstPoint;
    private final double angle;

    public Line(double x0, double y0, double x1, double y1) {
        firstPoint = new Point(x0, y0);

        //Вторая точка требуется только для вычисления угла линии.
        Point secondPoint = new Point(x1, y1);

        angle = (secondPoint.y() - firstPoint.y()) / (secondPoint.x() - firstPoint.x());
    }

    public boolean isPointAboveLine(Point pointToCheck) {
        return pointToCheck.y() > angle * (pointToCheck.x() - firstPoint.x()) + firstPoint.y();
    }

}
