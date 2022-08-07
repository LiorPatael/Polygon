public class Triangle {
    private Edge a, b, c;

    public Triangle(Edge a, Edge b, Edge c) {
        this.a = new Edge(a.getSrc(), a.getDest());
        this.b = new Edge(b.getSrc(), b.getDest());
        this.c = new Edge(c.getSrc(), c.getDest());
    }

    /*
     Calculate the triangle area by Heron's formula -
     https://en.wikipedia.org/wiki/Heron%27s_formula.
     */
    public double area() {
        double aSquare = Math.pow(this.a.getDist(),2);
        double bSquare = Math.pow(this.b.getDist(),2);
        double cSquare = Math.pow(this.c.getDist(),2);
        double triangleArea = Math.sqrt(4 * aSquare * bSquare - Math.pow((aSquare + bSquare - cSquare), 2)) / 4;
        return triangleArea;
    }

    public Edge getA() {
        return a;
    }

    public Edge getB() {
        return b;
    }

    public Edge getC() {
        return c;
    }
    // need to get check
//    public float angle() {
//        float sum = (c.square() - a.square() - b.square()) / (-2 * a.getDist() * b.getDist());
//        float alpha = (float) Math.acos((sum));
//        return alpha;
//    }


}
