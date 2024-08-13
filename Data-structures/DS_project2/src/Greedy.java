import java.io.*;

public class Greedy {
    public static void main(String[] args) throws Exception {
        // use ReadFile to start reading text file given
        ReadFile file = new ReadFile(new File(args[0]));
        // use greedy_assign_disks to assign files to disks
        MaxPQ<Disk> disks = greedy_assign_disks(file.read_file());
        // start printing results
        System.out.println("Sum of all folders = " + file.getSum() + " TB");
        System.out.println("Total number of disks used = " + disks.getSize());
        while (disks.getSize() != 0) {
            System.out.println(disks.getMax());
        }
    }

    // greedy_assign_disks gets a queue with the sizes of folders as a parameter and
    // assigns the folders to disks
    static MaxPQ<Disk> greedy_assign_disks(StringQueueImpl<Integer> text_content) throws Exception {
        // we start assigning the files to disks
        MaxPQ<Disk> disks = new MaxPQ<>(new CompareDiscs());

        int id = 0;

        // while there are still unassigned files
        while (!text_content.isEmpty()) {
            // if the file can be stored at the disk (from the disks used) with the most
            // free space
            if (disks.peek().getFreeSpace() >= text_content.peek()) {
                // we remove the disk that is the heap of the queue
                // so that we can insert it again , after we have stored the file
                Disk tempDisk = disks.getMax();
                // we store the file to the disk , and remove it from the file's queue
                tempDisk.addFolder(text_content.get());
                disks.insert(tempDisk);

            } else {
                // we create a new disk and store the file there
                id++;
                Disk disk = new Disk(Integer.toString(id));
                disk.addFolder(text_content.get());
                disks.insert(disk);
            }
        }
        return disks;
    }

}
