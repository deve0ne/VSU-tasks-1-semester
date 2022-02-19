package figures;

public class VerticalParabola {
    private final Point centerPoint;
    private final double a;

    public VerticalParabola(double centerX, double centerY, double a) {
        centerPoint = new Point(centerX, centerY);
        this.a = a;
    }

    public boolean isPointInParabola(Point pointToCheck) {
        return pointToCheck.y() > a * Math.pow(pointToCheck.x() - centerPoint.x(), 2) + centerPoint.y();
    }
}
