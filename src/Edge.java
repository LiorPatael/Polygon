//This class represant the Polygon edges.
public class Edge {
    private double dist;
    private Point src, dest;

    public Edge(Point src, Point dest) {
        this.src = new Point(src.getX(), src.getY(), 0, src.getId());
        this.dest = new Point(dest.getX(), dest.getY(), 0, dest.getId());
        this.dist = src.distance(dest);
    }

    public Point getSrc() {
        return this.src;
    }

    public Point getDest() {
        return this.dest;
    }

    public double Incline() {
        double yValue = this.dest.getY() - this.src.getY();
        double xValue = this.dest.getX() - this.src.getX();
        return yValue / xValue;
    }

    public boolean onEdge(Point check) {
        if (check.distance(this.src) + check.distance(this.dest) == this.src.distance(this.dest)) return true;
        else return false;
    }
}
