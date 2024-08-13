import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueWithOnePointer<T> implements StringQueue<T> {
    private int size = 0;
    Node<T> tail = new Node<>();
    // the tail points to the head of the queue
    // which means that the

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(T item) {
        Node<T> node = new Node<>(); // create a new node
        node.setData(item);
        this.size = this.size + 1; // since we added an item, the stack's size has increased

        if (size() == 1) { // if there was only one item in the queue
            tail.setNext(node); // since there was only one item in the queue , now there will be 2 , which
                                // means that the node that used to be the tal is now the head , and the node
                                // that is added is the new tail
            node.setNext(tail); // because the node added is the new tail , it is linked to the head whick is
                                // the old tail

        } else { // if the queue had more than 1 item
            node.setNext(tail.getNext()); // the node added is the new tail , so it has to be linked to the head
            tail.setNext(node); // the node that used to be the tail , is now linked to the node added
        }
        tail = node; // this node is now the tail of the queue
    }

    public T get() throws NoSuchElementException {
        if (isEmpty()) { // if the queue is empty throw a NoSuchElementException
            throw new NoSuchElementException();
        }
        T removed_item = tail.getNext().getData(); // store in the variable removed_item the item that we will later
                                                   // remove
        if (size() == 1) { // if there is only one item in the queue
            tail = null;

        } else if (size() == 2) { // if the queue has 2 items
            tail.setNext(null); // the only item in the queue is now the tail

        } else { // if the queue has more than 2 items
            tail.setNext(tail.getNext().getNext()); // the item after the head is now the new head , and is linked to
                                                    // the tail
        }

        this.size = this.size - 1; // since we removed an item, the queue's size has dicreased
        return removed_item;
    }

    public T peek() throws NoSuchElementException {
        if (isEmpty()) { // if the queue is empty throw a NoSuchElementException
            throw new NoSuchElementException();
        }
        return tail.getNext().getData();
    }

    public void printQueue(PrintStream stream) {
        Node<T> node = tail.getNext();
        if (isEmpty()) {
            stream.println("Queue is empty!");
        }
        while (node.getNext() != null) { // while there is a next item in the queue
            stream.println(node.getData()); // print node
            node = node.getNext(); // move to the next node
        }
    }

    public int size() {
        return this.size;
    }
}