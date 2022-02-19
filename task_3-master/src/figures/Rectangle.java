package figures;

public class Rectangle {
    //Под "min" и "max подразумевается координата, соответственно самая ближайшая и далёкая от начала отсчёта.
    private final Point minPoint;
    private final Point maxPoint;

    public Rectangle(double minX, double minY, double maxX, double maxY) {
        minPoint = new Point(minX, minY);
        maxPoint = new Point(maxX, maxY);
    }

    public boolean isPointInRectangle(Point pointToCheck) {
        return pointToCheck.x() >= minPoint.x() && pointToCheck.x() <= maxPoint.x() &&
                pointToCheck.y() >= minPoint.y() && pointToCheck.y() <= maxPoint.y();
    }
}
