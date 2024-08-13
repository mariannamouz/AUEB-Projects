public interface PriorityQueueInterface<T> {
    void insert(T item);

    T peek();

    T getMax();
}
