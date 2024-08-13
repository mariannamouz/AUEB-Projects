import java.io.*;

public class ReadFile {
    // it gets as a parameter a text file
    private File file;
    private int sum; // variable sum will be used to hold the value of the size of all the folders

    ReadFile(File file) {
        this.file = file;
        this.sum = 0;
    }

    public int getSum() {
        return this.sum;
    }

    // reads the text given and puts the numbers (file sizes) given in a queue
    public StringQueueImpl<Integer> read_file() throws Exception {
        // in this queue we store every file size we read from the text
        StringQueueImpl<Integer> content = new StringQueueImpl<Integer>();
        String text;
        int line = 1;
        try {
            BufferedReader read = new BufferedReader(new FileReader(this.file));
            while ((text = read.readLine()) != null) {
                if (Integer.parseInt(text) >= 0 && Integer.parseInt(text) <= 1000000) {
                    content.put(Integer.valueOf(text));
                    this.sum = this.sum + Integer.parseInt(text);
                } else {
                    throw new Exception("The size of the file in line" + line + " is not between 0 and 1.000.000 MB");
                }
                line++;
            }
            read.close();
        } catch (FileNotFoundException filenotfoundexception) {
            throw new FileNotFoundException("File not found, consider using another type of path");
        } catch (IOException ioexception) {
            throw new IOException(ioexception + " occurred");
        } catch (Exception exception) {
            throw new Exception(exception + " occurred");
        }
        return content;
    }

}
