package deque;

import java.util.Iterator;

public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
    Iterator<T> iterator();
    boolean equals(Object o);

    default boolean isEmpty() {
        return size() == 0;
    }
}
