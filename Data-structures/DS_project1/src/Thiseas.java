import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Thiseas {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]); // the text wich contains the labyrinth is given as an argument
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        try {
            StringStackImpl<String> stringStack = new StringStackImpl<>(); // create a stack

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader); // we use read to read the text

            int[] dimension = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // the
                                                                                                                 // dimention
                                                                                                                 // of
                                                                                                                 // the
                                                                                                                 // maze
                                                                                                                 // is
                                                                                                                 // given
                                                                                                                 // in
                                                                                                                 // the
                                                                                                                 // first
                                                                                                                 // line
                                                                                                                 // of
                                                                                                                 // the
                                                                                                                 // text
            int[] entry = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // the
                                                                                                             // coordinates
                                                                                                             // of the
                                                                                                             // entry
                                                                                                             // point
                                                                                                             // are
                                                                                                             // given in
                                                                                                             // the
                                                                                                             // second
                                                                                                             // line

            stringStack.push(Integer.toString(entry[0])); // put the entry point in the stack
            stringStack.push(Integer.toString(entry[1]));

            String[][] labyrinth = new String[dimension[0]][dimension[1]]; // labyrinth holds the context of the
                                                                           // labyrinth in the text file

            int line = 0; // we begin at the first line
            reader.readLine(); // we read the first two linew before the while loop because the labyrinth is
                               // given at the 3rd line
            reader.readLine();

            while ((reader.readLine()) != null) { // read line of the text without the spaces
                labyrinth[line] = reader.readLine().split(" "); // read the line of the text and put its elements
                                                                // (without the spaces) in the labyrinth array
                line++; // move to the next line
            }

            // check if the dimensions of the labyrinth are equal to the integers given in
            // the first line of the text , if E appears in the text and appart from that E
            // the labyrinth only includes 1s and 0s
            int size = 0;
            int e = 0; // number of times E appears in the text
            for (String[] r : labyrinth) { // we count the number of elements in the labyrinth array
                for (String s : r) {
                    size++;
                    if (s == "E") {
                        e++;
                    }
                    if ((s != labyrinth[0][0]) && (s != labyrinth[0][1]) && (s != labyrinth[1][0])
                            && (s != labyrinth[1][1])) { // if we are not reading the dimension or the coordinates of
                                                         // the entry point
                        if ((s != "0") && (s != "1")) {
                            throw new Exception("The labyrinth doesn't only include 1s and 0s !");
                        }
                    }
                }
            }
            int givenSize = dimension[0] * dimension[1];
            if (size != givenSize) {
                throw new Exception(
                        "The dimension of the labyrinth is not equal to the dimenions given in the first line of the text");
            }
            if (e == 0) {
                throw new Exception("E doesn't appear in the text");
            } else if (e > 1) {
                throw new Exception("E appears more than once in the text");
            }

            int row = entry[0]; // we begin at the entry point
            int col = entry[1];
            boolean foundExit = false;
            boolean adiexodo = false;

            while (foundExit == false) {
                while ((row != 0) && (row != (dimension[0] - 1)) && (col != 0) && (col != (dimension[1] - 1))
                        && adiexodo == false) { // while
                    // we
                    // are
                    // not
                    // in a
                    // boarder
                    System.out.println("You are now at :" + row + " " + col);
                    boolean valid = false;
                    String move;
                    while (valid == false) {
                        // valid becomes true if the input is equal to one of the following : UP / DOWN
                        // / RIGHT / LEFT
                        // and if the place on the board we want to visit has a 0
                        System.out.println(
                                "Do you wanna move up , down . right or left? (answer with : UP / DOWN / RIGHT / LEFT");
                        move = scanner.nextLine();
                        if (move == "UP") {
                            if (labyrinth[row - 1][col] == "0") {
                                row = row - 1;
                                stringStack.push(Integer.toString(row)); // we made a move , so we store its coordinates
                                                                         // in the stack
                                stringStack.push(Integer.toString(col));
                                valid = true;
                            }
                        } else if (move == "DOWN") {
                            if (labyrinth[row + 1][col] == "0") {
                                row = row + 1;
                                stringStack.push(Integer.toString(row));
                                stringStack.push(Integer.toString(col));
                                valid = true;
                            }
                        } else if (move == "RIGHT") {
                            if (labyrinth[row][col + 1] == "0") {
                                col = col + 1;
                                stringStack.push(Integer.toString(row));
                                stringStack.push(Integer.toString(col));
                                valid = true;
                            }
                        } else if (move == "LEFT") {
                            if (labyrinth[row][col - 1] == "0") {
                                col = col - 1;
                                stringStack.push(Integer.toString(row));
                                stringStack.push(Integer.toString(col));
                                valid = true;
                            }
                        }

                        // check if we are at a dead end (if we can't make any move)
                        int x = 0;
                        if (labyrinth[row][col - 1] == "1") {
                            x++;
                        }
                        if (labyrinth[row][col + 1] == "1") {
                            x++;
                        }
                        if (labyrinth[row + 1][col] == "1") {
                            x++;
                        }
                        if (labyrinth[row - 1][col] == "1") {
                            x++;
                        }
                        if (x == 3) {
                            adiexodo = true;
                        }

                    }

                }

                // we are out of the while loop which means we are at a boarder or at a dead end
                if (labyrinth[row][col] == "0") { // check if we found an exit (0)
                    System.out.println("Congratulations!You found an exit!");
                    System.out.println("Exit Coordinates: " + row + "," + col);
                    foundExit = true;
                } else { // if we didn't find an exit we need to go back
                    // go to the place we were last
                    String ans = "YES";
                    // we give a possitive answer because we have to go back at least once
                    // in order to do that we must remove the first two elements of the stack ,
                    // which are the coordinates of the last place of the labyrinth we visited
                    while ((ans == "YES") && (stringStack.size() != 2)) {// if the size of the stack equals 2 , it only
                                                                         // has the coordinates of the entry points
                        stringStack.pop();
                        stringStack.pop();
                        row = Integer.parseInt(stringStack.peek());
                        col = Integer.parseInt(stringStack.peek());
                        System.out.println("You are now at :" + row + " " + col);
                        System.out.println("Do you want to move further back? (YES/NO)");
                        ans = scanner.nextLine();
                        while ((ans != "YES") && (ans != "NO")) { // if the input is incorrect
                            System.out.println("You have to answer with either YES or NO !");
                            System.out.println("Do you want to move further back? (YES/NO)");
                            ans = scanner.nextLine();
                        }
                    }
                    row = Integer.parseInt(stringStack.peek());
                    col = Integer.parseInt(stringStack.peek());
                    System.out.println("You are now at :" + row + " " + col);
                }
            }

        } catch (Exception exception) {
            scanner.close();
            throw new Exception(exception);
        }

        scanner.close();
    }
}