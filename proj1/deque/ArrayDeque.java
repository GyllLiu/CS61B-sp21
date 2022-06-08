package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int last;
    private int first;
    private int size;
    private double increaseRefactor = 1.5;
    private double decreaseRefactor = 0.25;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        last = 0;
        first = items.length - 1;
    }

    @Override
    public void addFirst(T item) {
        if (first == last) {
            reIndexFirstAdd();
        }
        items[first] = item;
        size = size + 1;
        first = first - 1;
    }

    @Override
    public void addLast(T item) {
        if (first == last) {
            reIndexFirstAdd();
        }
        items[last] = item;
        last = last + 1;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        return first == items.length - 1 && last == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public T removeFirst() {
        if(last == 0 && first == items.length - 1) {
            return null;
        }

        if(items[first] == null && first == items.length - 1) {
            return null;
        }

        int reCapacity = (int) (items.length*0.25);
        if(size < reCapacity) {
            reIndexFirstRemove();
        }
        items[first] = null;
        first = first + 1;
        size = size - 1;
        return items[first];
    }

    @Override
    public T removeLast() {
        //if it is an empty AD, return null
        if(last == 0 && first == items.length - 1) {
            return null;
        }

        //if no items added to last, return null
        if(items[last] == null && last == 0) {
            return null;
        }

        if(size < items.length*0.25) {
            reIndexFirstRemove();
        }
        items[last] = null;
        last = last - 1;
        return items[last];
    }

    @Override
    public T get(int index) {
        if(index < items.length - first - 1) {
            return items[first + index + 1];
        } else {
            return items[index];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public void resize(double capacity) {
        if(capacity >= 1) {
            T[] temp = (T[]) new Object[(int)(capacity)];
            System.arraycopy(items, 0, temp, 0, last);
            System.arraycopy(items, first, temp, temp.length - (items.length - first), items.length - first);
            items = temp;
        }
    }

    //help reset the First index when larger the length of the deque
    public void reIndexFirstAdd() {
        int capacity = (int) (items.length * increaseRefactor);
        int befResizeLen = items.length;
        resize(capacity);
        first = first + capacity - befResizeLen;
    }

    //help reset the First index when reduce the length of the deque
    public void reIndexFirstRemove() {
        int capacity = (int) (items.length * decreaseRefactor);
        int befResizeLen = items.length;
        resize(capacity);
        first = first - (befResizeLen - capacity);
    }
}
