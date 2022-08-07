import java.io.File;

public class Main {
    public static void main(String[] args) {
        //Write here the path name of the kml file.
        File file = new File("src/Allowed area.kml");
        Polygon polygon = new Polygon(file);
        System.out.println(polygon.isOn());
    }
}
