package figures;

public class HorizontalParabola {
    private final Point centerPoint;
    private final double a;

    public HorizontalParabola(double centerX, double centerY, double a) {
        centerPoint = new Point(centerX, centerY);
        this.a = a;
    }

    public boolean isPointInParabola(Point pointToCheck) {
        return pointToCheck.x() < a * Math.pow(pointToCheck.y() - centerPoint.y(), 2) + centerPoint.x();
    }
}