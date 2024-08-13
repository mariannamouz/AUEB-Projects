import java.util.Comparator;

public class CompareDiscs implements Comparator<Disk> {
    public int compare(Disk discA, Disk discB) {

        return new IntegerComparator().compare(discA.getFreeSpace(), discB.getFreeSpace());

    }
}
