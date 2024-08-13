import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class TwoDTree {
    private class Treenode {
        private Point item; // an object of the class Point
        private Treenode l; // pointer to left subtree
        private Treenode r; // pointer to right subtree

        public Treenode(Point data) {
            this.item = data;
        }

        public Point getData() {
            return this.item;
        }

        public void setData(Point data) {
            this.item = data;
        }

        public Treenode getLeft() {
            return l;
        }

        public void setLeft(Treenode left) {
            this.l = left;
        }

        public Treenode getRight() {
            return r;
        }

        public void setRight(Treenode right) {
            this.r = right;
        }
    }

    private Treenode head; // head of the tree
    private int size; // size of the tree

    public TwoDTree() { // construct an empty tree
        this.size = 0;
        this.head = null;
    }

    private TwoDTree(Treenode head) { // construct a tree with a head
        this.head = head;
    }

    public boolean isEmpty() { // is the tree empty?
        return this.size() == 0;
    }

    public int size() { // number of points in the tree
        return this.size;
    }

    public Treenode head() {
        return this.head;
    }

    public boolean search(Point point) {
        return search_RecursiveFunction(head(), point, "x"); // we start by comparing x coordinates
    }

    // helper function used so that we can call it recursivelly
    public boolean search_RecursiveFunction(Treenode head, Point point, String cord) {

        if (isEmpty()) {
            return false;
        }
        TwoDTree currentTree;
        // if the node we are searching for is equal to the head
        // then we have found the node we are looking for , so we return true
        if (point.equals(head.getData())) {
            return true;
        } else { // if we need to keep searching
            if (cord == "x") { // if we are comparing the x coordinates
                if (head.getData().x() > point.x()) {
                    currentTree = LeftSubTree(head);
                } else {
                    currentTree = RightSubTree(head);
                }
                return currentTree.search_RecursiveFunction(currentTree.head(), point, "y");
            } else if (cord == "y") { // if we are comparing the y coordinates
                if (head.getData().y() > point.y()) {
                    currentTree = LeftSubTree(head);
                } else {
                    currentTree = RightSubTree(head);
                }
                return currentTree.search_RecursiveFunction(currentTree.head(), point, "x");
            }
        }

        return false;
    }

    public void insert(Point point) { // inserts the point p to the 2d-tree
        // calls insert with node=head and cord="x" , because we want to compare the x
        // coordinates first
        this.head = insert_RecursiveFunction(this.head, point, "x");
    }

    public Treenode insert_RecursiveFunction(Treenode node, Point point, String cord) {
        // function called recursivelly until we put the given point in the 2d-tree
        if (node == null) {
            return new Treenode(point);
        }
        // if point is already in the 2d-tree
        else if (node.getData().equals(point)) {
            System.out.println("Can't insert node that already exists");
            return null;
        }

        // if we are comparing the x coordinates
        if (cord == "x") {
            // Left case
            // if the node's x coordinate is bigger than the point's we want to add
            if (node.getData().x() > point.x()) {
                node.setLeft(insert_RecursiveFunction(node.getLeft(), point, "y"));
            }
            // if the node's x coordinate is less than the point's we want to add
            else {
                node.setRight(insert_RecursiveFunction(node.getRight(), point, "y"));
            }
            // if we are comparing the y coordinates
        } else if (cord == "y") {
            // if the node's y coordinate is bigger than the point's we want to add
            if (node.getData().y() > point.y()) {
                node.setLeft(insert_RecursiveFunction(node.getLeft(), point, "x"));
            }
            // if the node's y coordinate is lesser than the point's we want to add
            else {
                node.setRight(insert_RecursiveFunction(node.getRight(), point, "x"));
            }
        }
        return node;
    }

    // point in the tree that is closest to p (null if tree is empty)
    public Point nearestNeighbor(Point p) {
        // if the 2d-tree is empty
        if (isEmpty()) {
            return null;
        }
        return this.nearestNeighbor_RecursiveFunction(this.head.getData(), p, new Rectangle(0, 100, 0, 100), "x");
    }

    public Point nearestNeighbor_RecursiveFunction(Point closestPoint, Point searchingPoint, Rectangle rectangle,
            String cord) {
        // if tree branch is empty
        if (this.isEmpty()) {
            return closestPoint;
        }

        // if the head is closer than the closest point
        if (this.head.getData().distanceTo(searchingPoint) < closestPoint.distanceTo(searchingPoint)) {
            closestPoint = this.head().getData();
        }

        // if the distance of the closest point with the searchingPoint is bigger than
        // the distance of the node-rectangle with the searchingPoint
        if (closestPoint.distanceTo(searchingPoint) >= rectangle.distanceTo(searchingPoint)) {
            // x coordinates
            if (cord == "x") {
                closestPoint = LeftSubTree(head).nearestNeighbor_RecursiveFunction(closestPoint, searchingPoint,
                        new Rectangle(
                                rectangle.xmin(), head.getData().x(), rectangle.ymin(), rectangle.ymax()),
                        "y");
                closestPoint = RightSubTree(head).nearestNeighbor_RecursiveFunction(closestPoint, searchingPoint,
                        new Rectangle(
                                head.getData().x(), rectangle.xmax(), rectangle.ymin(), rectangle.ymax()),
                        "y");
            }
            // y coordinates
            else if (cord == "y") {
                closestPoint = LeftSubTree(head).nearestNeighbor_RecursiveFunction(closestPoint, searchingPoint,
                        new Rectangle(
                                rectangle.xmin(), rectangle.xmax(), rectangle.ymin(), head.getData().y()),
                        "x");
                closestPoint = RightSubTree(head).nearestNeighbor_RecursiveFunction(closestPoint, searchingPoint,
                        new Rectangle(
                                rectangle.xmin(), rectangle.xmax(), head.getData().y(), rectangle.ymax()),
                        "x");
            }
        }
        return closestPoint;
    }

    public List<Point> rangeSearch(Rectangle rectangle) { // Returns a list
        // with the Points that are contained in the rectangle
        List<Point> pointList = new ArrayList<Point>();
        // Empty tree
        if (isEmpty()) {
            return pointList;
        }
        return rangeSearch_RecursiveFunction(pointList, rectangle, new Rectangle(0, 100, 0, 100), "x");
    }

    // function called recursivelly
    public List<Point> rangeSearch_RecursiveFunction(List<Point> pointList, Rectangle rectangle,
            Rectangle pointRectangle,
            String cord) {

        // if the branch is empty
        if (isEmpty()) {
            return pointList;
        }

        // if the rectangle contains this node
        if (rectangle.contains(this.head().getData())) {
            pointList.add(this.head().getData());
        }

        // the point rectangle and the rectangle we are searching in , need to intersect
        if (!(rectangle.intersects(pointRectangle))) {
            return pointList;
        } else {
            // x coordinates
            if (cord == "x") {
                LeftSubTree(head).rangeSearch_RecursiveFunction(pointList, rectangle,
                        new Rectangle(pointRectangle.xmin(), head.getData().x(), pointRectangle.ymin(),
                                pointRectangle.ymax()),
                        "y");
                RightSubTree(head).rangeSearch_RecursiveFunction(pointList, rectangle,
                        new Rectangle(head.getData().x(), pointRectangle.xmax(), pointRectangle.ymin(),
                                pointRectangle.ymax()),
                        "y");
            }
            // y coordinates
            else if (cord == "y") {
                LeftSubTree(head).rangeSearch_RecursiveFunction(pointList, rectangle,
                        new Rectangle(pointRectangle.xmin(), pointRectangle.xmax(), pointRectangle.ymin(),
                                head.getData().y()),
                        "x");
                RightSubTree(head).rangeSearch_RecursiveFunction(pointList, rectangle,
                        new Rectangle(pointRectangle.xmin(), pointRectangle.xmax(), head.getData().y(),
                                pointRectangle.ymax()),
                        "x");
            }
        }
        return pointList;
    }

    // return the left sub tree
    public TwoDTree LeftSubTree(Treenode node) {
        // Empty
        if (head == null) {
            return null;
        }
        return new TwoDTree(node.getLeft());
    }

    // return the right sub tree
    public TwoDTree RightSubTree(Treenode node) {
        // Empty tree
        if (head == null) {
            return null;
        }
        return new TwoDTree(node.getRight());
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // first we create a 2d-tree from the text given
        TwoDTree tree = createTree(new File(args[0]));
        boolean exit = false;
        while (!exit) {
            // print menu
            tree.menu();
            // user's choice
            int choice = scanner.nextInt();
            // check if user's input is valid
            while (choice < 1 || choice > 6) {
                System.out.println("The number you gave is not valid!");
                // print menu again
                tree.menu();
                // user choices again
                choice = scanner.nextInt();
            }
            // carry out user's choice
            if (choice == 1) {
                System.out.println(tree.size());
            } else if (choice == 2) {
                Point p = tree.createPoint();
                tree.insert(p);
            } else if (choice == 3) {
                Point p = tree.createPoint();
                boolean exists = tree.search(p);
                if (exists == true) {
                    System.out.println("Point exists in the tree");
                } else {
                    System.out.println("Point doesn't exist in the tree");
                }
            } else if (choice == 4) {
                System.out.println("Please enter x coordinates's minimum value");
                int xmin = scanner.nextInt();
                System.out.println("Please enter x coordinates's maximum value");
                int xmax = scanner.nextInt();
                System.out.println("Please enter y coordinates's minimum value");
                int ymin = scanner.nextInt();
                System.out.println("Please enter y coordinates's maximum value");
                int ymax = scanner.nextInt();
                // create rectangle
                Rectangle rect = new Rectangle(xmin, xmax, ymin, ymax);
                // find points of 2d-tree that are included in rectangle
                List<Point> pointsIncluded = tree.rangeSearch(rect);
                System.out.println("These are the points of the tree contained within the given parallelogram :");
                for (Point p : pointsIncluded) {
                    System.out.println(p);
                }
            } else if (choice == 5) {
                Point p = tree.createPoint();
                Point nearestPoint = tree.nearestNeighbor(p);
                System.out.print("The closest point is : " + nearestPoint + "with a distance of : "
                        + nearestPoint.distanceTo(p));
            } else {
                exit = true;
            }
        }
    }

    public void menu() {
        System.out.println("1)Compute the size of the tree");
        System.out.println("2)Insert a new point");
        System.out.println("3)Search if a given point exists in the tree");
        System.out.println("4)Provide a query rectangle");
        System.out.println("5)Provide a query point");
        System.out.println("6)EXIT");
        System.out.println("Please enter a number from 1 to 6 , based on your choice");
    }

    public Point createPoint() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter point's x coordinate");
        int x = scanner.nextInt();
        System.out.println("Please enter point's y coordinate");
        int y = scanner.nextInt();
        Point p = new Point(x, y);
        return p;
    }

    public static TwoDTree createTree(File file) throws Exception { // returns 2d-tree with points from text
        try {
            TwoDTree twoDTree = new TwoDTree();
            BufferedReader read = new BufferedReader(new FileReader(file));

            int numberOfPoints = Integer.parseInt(read.readLine()); // number read in the first line of the text , equal
                                                                    // to number of points
            int n = 0; // temporary variable that holds the number of lines read

            while (read.readLine() != null) {
                n++;
            }

            // Check if the number given in the first line is equal to the number of the
            // rest of the lines
            // We check this first before inserting points to the tree
            if (numberOfPoints != n) {
                throw new Exception(
                        "The number given in the first line of the text is not equal to the number of points");
            }

            // reset the buffer so that we can read the text again
            read = new BufferedReader(new FileReader(file));

            read.readLine();
            String line;

            while ((line = read.readLine()) != null) {
                String[] strTbl = line.split(" ");
                int x = Integer.parseInt(strTbl[0]);
                int y = Integer.parseInt(strTbl[1]);
                Point p = new Point(x, y);
                if (p.y() > 100 || p.y() < 0 || p.x() < 0 || p.x() > 100) {
                    throw new Exception("Coordinates x and y can't be less than 0 or greater than 100!");
                }
                twoDTree.insert(p);
            }
            return twoDTree;

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        } catch (IOException e) {
            throw new IOException(e + " occurred");
        } catch (Exception e) {
            throw new Exception(e + " occurred");
        }
    }

}
