import java.io.*;

public class Sort {
    public static void main(String[] args) throws Exception {
        // use ReadFile to start reading text file given
        ReadFile file = new ReadFile(new File(args[0]));
        // file will return a queue but sort works with arrays
        // use QueueToArray to turn the queue to an array
        int[] fileArray = QueueToArray(file.read_file());
        // use MergeSort to sort the array
        MergeSort(fileArray, fileArray.length);
        reverse(fileArray);
        // sorting will return an array but greedy_algorythm works with queues
        // use ArrayToQueue to turn the array to a queue
        StringQueueImpl<Integer> queue = ArrayToQueue(fileArray);
        // use greedy_assign_disks to assign files to disks
        MaxPQ<Disk> disks = greedy_assign_disks(queue);

        // start printing results
        System.out.println("Sum of all folders = " + file.getSum() + " TB");
        System.out.println("Total number of disks used = " + disks.getSize());
        while (disks.getSize() != 0) {
            System.out.println(disks.getMax());
        }

    }

    public static void merge(int[] left_array, int[] right_array, int[] array, int left_size, int right_size) {
        int i = 0, l = 0, r = 0;
        // The while loops check the conditions for merging
        while (l < left_size && r < right_size) {
            if (left_array[l] < right_array[r]) {
                array[i++] = left_array[l++];
            } else {
                array[i++] = right_array[r++];
            }
        }
        // while l hasn't overcome the left_size , we have more elements to append from
        // the left_array to the array
        while (l < left_size) {
            array[i++] = left_array[l++];
        }

        // while r hasn't overcome the right_size , we have more elements to append from
        // the right_array to the array
        while (r < right_size) {
            array[i++] = right_array[r++];
        }
    }

    public static void MergeSort(int[] array, int length) {
        // we have completely separeted the array elements
        if (length < 2) {
            return;
        }
        // if for example we have an array with 5 elements we will create one array with
        // 2 (=5/2) and one with 3(=5-2)
        int mid = length / 2;
        int[] left_array = new int[mid];
        int[] right_array = new int[length - mid];

        // Dividing array into two and copying into two different arrays
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (i < mid) { // copy to the left_array the first half of the array
                left_array[i] = array[i];
            } else { // copy to the right_array the second half of the array
                right_array[k] = array[i];
                k = k + 1;
            }
        }
        // Recursively calling the function to divide the subarrays further
        MergeSort(left_array, mid);
        MergeSort(right_array, length - mid);
        // Calling the merge method on each subdivision
        merge(left_array, right_array, array, mid, length - mid);
    }

    // the merge sort algorythm we have made return an array with its elements in
    // ascending order
    // reverse return the array passed as parameter in declining order
    public static int[] reverse(int[] array) {
        int[] diversed = new int[array.length];
        int k = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            diversed[k] = array[i];
            k++;
        }
        return diversed;
    }

    // turns queue to array
    public static int[] QueueToArray(StringQueueImpl<Integer> queue) {
        int[] fileArray = new int[queue.size()];
        for (int i = 0; i < fileArray.length; i++) {
            fileArray[i] = queue.get();
        }
        return fileArray;
    }

    // turns array to queue
    public static StringQueueImpl<Integer> ArrayToQueue(int[] fileArray) {
        StringQueueImpl<Integer> queue = new StringQueueImpl<>();
        for (int i = 0; i < fileArray.length; i++) {
            queue.put(fileArray[i]);
        }
        return queue;
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
            // free spcae
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