
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() { // return the x-coordinate
        return this.x;
    }

    public int y() { // return the y-coordinate
        return this.y;
    }

    public double distanceTo(Point z) { // Euclidean distance between two points
        // double distance = Math.sqrt(((this.x - z.x) ^ 2) + ((this.y - z.y) ^ 2));
        double distance = Math.sqrt(Math.pow(this.x - z.x, 2) + Math.pow(this.y - z.y, 2));
        return distance;
    }

    public int squareDistanceTo(Point z) { // square of the Euclidean distance between two points
        return (int) (Math.pow(distanceTo(z), 2));
    }

    public String toString() { // string representation: (x, y)
        return "(" + this.x + ", " + this.y + ")";
    }
}
