
//This class represent the polygon's coordinates.

public class Point {
    private double x, y, z;
    private int id;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Point(double x, double y, double z, int id) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getId() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public double distance(Point p) {
        double xDist = Math.pow(p.x - this.x, 2);
        double yDist = Math.pow(p.y - this.y, 2);
        return Math.sqrt(xDist + yDist);
    }


    public String toString() {
        return this.id + " : " + this.getX() + "," + this.getY() + "," + 0;
    }

}
