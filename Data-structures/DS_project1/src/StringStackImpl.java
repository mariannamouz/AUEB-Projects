import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T> {
	private int size = 0;
	Node<T> head = new Node<>();

	public boolean isEmpty() {
		return size() == 0;
	}

	public void push(T item) {
		Node<T> node = new Node<>(); // create a new node
		node.setData(item);
		this.size = this.size + 1; // since we added an item, the stack's size has increased
		if (!isEmpty()) {
			node.setNext(head); // the node that was the head of the stack will now come after this node
		}
		head = node; // this node is now the head of the stack
	}

	public T pop() throws NoSuchElementException {
		if (isEmpty()) { // if the stack is empty throw a NoSuchElementException
			throw new NoSuchElementException();
		}
		T removed_item = head.getData(); // store in the variable removed_item the item that we will later remove
		head = head.getNext(); // remove item on the top of the stack
		this.size = this.size - 1; // since we removed an item, the stack's size has dicreased
		return removed_item; // return the item that used to be on the top of the stack
	}

	public T peek() throws NoSuchElementException {
		if (isEmpty()) { // if the stack is empty throw a NoSuchElementException
			throw new NoSuchElementException();
		}
		return head.getData();
	}

	public void printStack(PrintStream stream) {
		Node<T> node = head;
		if (isEmpty()) {
			stream.println("Stack is empty!");
		}
		while (node.getNext() != null) { // while there is a next item in the stack
			stream.println(node.getData()); // print node
			node = node.getNext(); // move to the next node
		}
	}

	public int size() {
		return this.size;
	}

}