import java.io.File;
import java.util.*;


public class Polygon {
    public static ArrayList<Point> points;

    //creating the polygon by the coordinates order.
    public Polygon(File file) {
        this.points = new ArrayList<>();
        String str = "";
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.equals("\t\t\t\t\t<coordinates>")) {
                    line = input.nextLine();
                    str = line;
                }
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //loads the coordinates
        String[] coordinates = str.split(" ");
        int id = 0;
        for (String point : coordinates) {
            String[] value = point.split(",");
            double xValue = Float.parseFloat(value[0]);
            double yValue = Float.parseFloat(value[1]);
            points.add(new Point(xValue, yValue, 0, id++));
        }
    }

    /*
    This function calculate the polygon area by the shoelace formula.
    https://en.wikipedia.org/wiki/Shoelace_formula
     */
    public double polygonArea() {
        int n = points.size();
        double sum1 = 0, sum2 = 0;
        for (int i = 0; i < n - 1; i++) {
            sum1 += Math.abs(points.get(i).getX() * points.get(i + 1).getY());
            sum2 += Math.abs(points.get(i + 1).getX() * points.get(i).getY());
        }
        sum1 += Math.abs(points.get(n - 1).getX() * points.get(0).getY());
        sum2 += Math.abs(points.get(0).getX() * points.get(n - 1).getY());
        double polygonArea = Math.abs((sum1 - sum2) / 2);
        return polygonArea;
    }

    /*
    returns if the point is in side of the polygon or not.
    If not, this function will return the must closest distance between the point to the polygon.
     */
    public boolean isOn() {
        double beforeAdded = polygonArea();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pick the X value of the Point : ");
        double xValue = Double.parseDouble(scanner.next());
        System.out.println("Pick the Y value of the Point : ");
        double yValue = Double.parseDouble(scanner.next());

        double min = Double.MAX_VALUE;
        Point checkPoint = new Point(xValue, yValue);
        Edge ansEdge = null;
        //searching for the minimum distance value and from which edge.
        for (int i = 0; i < points.size() - 1; i++) {
            Edge tempEdge = new Edge(points.get(i), points.get(i + 1));
            double dist = getDist(tempEdge, checkPoint);
            if (dist != -1) {
                if (min > dist) {
                    min = dist;
                    ansEdge = tempEdge;
                }
            } else if (dist == -1) {
                double distToSrc = tempEdge.getSrc().distance(checkPoint);
                double distToDest = tempEdge.getDest().distance(checkPoint);
                if (distToSrc < min) {
                    min = distToSrc;
                    ansEdge = tempEdge;
                } else if (distToDest < min) {
                    min = distToSrc;
                    ansEdge = tempEdge;
                }
            }
        }
        checkPoint.setID(ansEdge.getDest().getId()); //adding the check point to the polygon

        //creates a new polygon by adding the check point.
        points.add(ansEdge.getDest().getId(), checkPoint);
        for (int i = checkPoint.getId(); i < points.size(); i++) {
            points.get(i).setID(i);
        }
        //if the check point is outside from the polygon then the polygon's area is need to be bigger.
        double afterAdded = polygonArea();
        if (afterAdded <= beforeAdded) {
            return true;
        } else {
            System.out.println("The point is outside from the polygon.\nThe closest distance from this point to the polygon is : " + min);
            return false;
        }
    }

    /*
    returns the distance between the meeting point that came from the two Linear equations.
    The first equation is the edge's Linear equation.
    The second equation came from the opposite edge incline.
    https://www.sikumuna.co.il/wiki/%D7%A0%D7%A7%D7%95%D7%93%D7%AA_%D7%97%D7%99%D7%AA%D7%95%D7%9A_%D7%A9%D7%9C_%D7%A9%D7%A0%D7%99_%D7%99%D7%A9%D7%A8%D7%99%D7%9D
    if the meeting point is not on the edge, the function return -1.
     */
    public double getDist(Edge edge, Point point) {
        //creat the first equation
        double m1 = -1 / edge.Incline();
        double b1 = point.getY() - m1 * point.getX();

        //creat the second equation
        double m2 = edge.Incline();
        double b2 = edge.getSrc().getY() - m2 * edge.getSrc().getX();

        //calculate the meeting point
        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;
        Point meetPoint = new Point(x, y);

        //check if the meeting point is on the edge.
        if (edge.onEdge(meetPoint) == true) {
            double dist = meetPoint.distance(point);
            return dist;
        }
        return -1;
    }
}
