
public class Disk implements Comparable<Disk> {
    private String id;
    private MaxPQ<Integer> folders = new MaxPQ<>(new IntegerComparator());
    private int freeSpace;
    private int size = 1000000;

    Disk(String id) {
        this.id = id;
        this.freeSpace = size;
    }

    public String getId() {
        return this.id;
    }

    public int getFreeSpace() {
        return this.freeSpace;
    }

    public int getSize() {
        return this.size;
    }

    public int compareTo(Disk b) {
        if (getFreeSpace() > b.getFreeSpace()) {
            return 1;
        } else if (getFreeSpace() < b.getFreeSpace()) {
            return -1;
        } else {
            return 0;
        }
    }

    // adds a folder to the disk
    public void addFolder(int folder) {
        this.folders.insert(folder);
        freeSpace -= folder;
    }

    // overriding the toString() method
    public String toString() {
        String result = id + getId() + " " + getFreeSpace() + ":";

        while (folders.getSize() != 0) {
            result = result + folders.getMax();
            result = result + " ";
        }

        return result;
    }
}