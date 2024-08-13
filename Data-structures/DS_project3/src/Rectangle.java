
public class Rectangle {
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    public Rectangle(int xmin, int xmax, int ymin, int ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public int xmin() { // minimum x-coordinate of rectangle
        return this.xmin;
    }

    public int ymin() { // minimum y-coordinate of rectangle
        return this.ymin;
    }

    public int xmax() { // maximum x-coordinate of rectangle
        return this.xmax;
    }

    public int ymax() { // maximum y-coordinate of rectangle
        return this.ymax;
    }

    public boolean contains(Point p) { // does p belong to the rectangle?
        // xmin <= p.x <= xmax and ymin <= p.y <= ymax
        return p.x() >= this.xmin && p.x() <= this.xmax && p.y() >= this.ymin && p.y() <= this.ymax;
    }

    public boolean intersects(Rectangle that) { // do the two rectangles intersect?
        // One of the two rectangles is above the top edge of the other rectangle
        // we compare their y-coordinates
        if (this.ymax < that.ymin || this.ymin > that.ymax) {
            return false;
        }

        // One of the two rectangles is on the left side of the left edge of the other
        // rectangle
        // we compare their x-coordinates
        if (this.xmax < that.xmin || this.xmin > that.xmax) {
            return false;
        }

        // returns true if the two rectangles have at least one point in common
        return true;
    }

    public double distanceTo(Point p) { // Euclidean distance from p to closest point in rectangle
        // if the rectangle contains the point given
        if (contains(p)) {
            return 0;
        }

        if (p.x() < this.xmin) {
            if (p.y() > this.ymax) { // 1st case
                Point topL = new Point(this.xmin, this.ymax);
                return topL.distanceTo(p);
            } else if (p.y() < this.ymin) { // 6th case
                Point bottomL = new Point(this.xmin, this.ymin);
                return bottomL.distanceTo(p);
            } else { // 4th case
                double min = 0;
                for (int i = this.ymin; i <= this.ymax; i++) {
                    Point pnt = new Point(this.xmin, i);
                    if (pnt.distanceTo(p) < min) {
                        min = pnt.distanceTo(p);
                    }
                }
                return min;
            }
        } else if (p.x() > this.xmax) {
            if (p.y() > this.ymax) { // 3rd case
                Point topR = new Point(this.xmax, this.ymax);
                return topR.distanceTo(p);
            } else if (p.y() < this.ymin) { // 8th case
                Point bottomR = new Point(this.xmax, this.ymin);
                return bottomR.distanceTo(p);
            } else { // 5th case
                double min = 0;
                for (int i = this.ymin; i <= this.ymax; i++) {
                    Point pnt = new Point(this.xmax, i);
                    if (pnt.distanceTo(p) < min) {
                        min = pnt.distanceTo(p);
                    }
                }
                return min;
            }
        } else { // this.xmin <= p.x() <= this.xmax
            if (p.y() > this.ymax) { // 2nd case
                double min = 0;
                for (int i = this.xmin; i <= this.xmax; i++) {
                    Point pnt = new Point(i, this.ymax);
                    if (pnt.distanceTo(p) < min) {
                        min = pnt.distanceTo(p);
                    }
                }
                return min;
            } else if (p.y() < this.ymin) { // 7th case
                double min = 0;
                for (int i = this.xmin; i <= this.xmax; i++) {
                    Point pnt = new Point(i, this.ymin);
                    if (pnt.distanceTo(p) < min) {
                        min = pnt.distanceTo(p);
                    }
                }
                return min;
            }
        }
        return -1;
    }

    public int squareDistanceTo(Point p) { // square of Euclidean distance from p to closest point in rectangle
        return (int) (Math.pow(distanceTo(p), 2));
    }

    public String toString() { // string representation: [xmin, xmax] × [ymin, ymax]
        return "[" + this.xmin + ", " + this.xmax + "]" + " x " + "[" + this.ymin + ", " + this.ymax + "]";
    }
}
