import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl<T> implements StringQueue<T> {
    private int size = 0;
    Node<T> head = new Node<>();
    Node<T> tail = new Node<>();

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(T item) {
        Node<T> node = new Node<>(); // create a new node
        node.setData(item);
        this.size = this.size + 1; // since we added an item, the stack's size has increased
        if (isEmpty()) { // if the queue is empty the item will not be just the tail , but also the head
            head = node;
        } else if (head == tail) { // if there is only one item in the queue
            head.setNext(node);
        } else { // if the queue has more than 1 item
            tail.setNext(node);
        }
        tail = node; // this node is now the tail of the queue
    }

    public T get() throws NoSuchElementException {
        if (isEmpty()) { // if the queue is empty throw a NoSuchElementException
            throw new NoSuchElementException();
        }
        T removed_item = head.getData(); // store in the variable removed_item the item that we will later remove
        if (head == tail) { // if there is only one item in the queue
            head = null;
        } else { // if the queue has more than 1 item
            head = head.getNext();
        }

        this.size = this.size - 1; // since we removed an item, the queue's size has dicreased
        return removed_item;
    }

    public T peek() throws NoSuchElementException {
        if (isEmpty()) { // if the queue is empty throw a NoSuchElementException
            throw new NoSuchElementException();
        }
        return head.getData();
    }

    public void printQueue(PrintStream stream) {
        Node<T> node = head;
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