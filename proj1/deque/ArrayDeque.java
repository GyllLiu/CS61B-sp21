package deque;

import jh61b.junit.In;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int last;
    private int first;
    private int size;
    private int firstNum;
    private int lastNum;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        last = 0;
        first = items.length - 1;
        firstNum = 0;
        lastNum = 0;
    }

    @Override
    public void addFirst(T item) {
        resetPtr();
        if (first == last || first < 0) {
            reIndexFirstAdd();
        }
        items[first] = item;
        firstNum = firstNum + 1;
        size = size + 1;
        first = first - 1 < 0 ? items.length - 1 : first - 1;
    }

    @Override
    public void addLast(T item) {
        resetPtr();
        if (first == last || last > items.length - 1) {
            reIndexFirstAdd();
        }
        items[last] = item;
        lastNum = lastNum + 1;
        size = size + 1;
        last = last + 1 > items.length - 1 ? 0 : last + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            if(i == size - 1) {
                sb.append(get(i));
            } else {
                sb.append(get(i) + " ");
            }
        }
        System.out.println(sb);
    }

    @Override
    public T removeFirst() {
        resetPtr();
        if(size == 0) {
            return null;
        }

        if(first >= items.length - 1 || (first == items.length - 1 && items[first] == null)) {
            first = -1;
        }

        if(firstNum == 0 && lastNum > 0) {
            lastNum = lastNum - 1;
        }

        firstNum = firstNum - 1 >= 0 ? firstNum - 1 : 0;
        first = first + 1;
        T retItem = items[first];
        items[first] = null;
        size = size - 1;
        if(size < (int) (items.length*0.25)) {
            reIndexFirstRemove();
        }
        return retItem;
    }

    @Override
    public T removeLast() {
        resetPtr();
        //if it is an empty AD, return null
        if(size == 0) {
            return null;
        }
        T retItem;

        if(last < 0 || (last == 0 && items[last] == null)) {
            last = items.length;
        }

        if(lastNum < 0 && firstNum > 0) {
            firstNum = firstNum - 1;
        }

        lastNum = lastNum - 1 >= 0 ? lastNum - 1 : 0;
        last = last - 1;
        retItem = items[last];
        items[last] = null;
        size = size - 1;
        if(size != 0 && size < items.length*0.25) {
            reIndexFirstRemove();
        }

        return retItem;
    }

    @Override
    public T get(int index) {
        resetPtr();

        if(first < last) {
            return items[first + index + 1];
        }

        firstNum = items.length - first - 1;
        lastNum = last;
        if(index < items.length - first - 1) {
            return items[first + index + 1];
        } else {
            return items[index - firstNum];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) { return false; }

        if (o instanceof ArrayDeque) {
            if(((ArrayDeque<?>) o).size() != size) {
                return false;
            }

            for(int i = 0; i < ((ArrayDeque<?>) o).size(); i++) {
                if(((ArrayDeque<?>) o).get(i) == null) {
                    continue;
                }
                if(!((ArrayDeque<?>) o).get(i).equals(get(i))) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    public void resize(double refactor) {
        T[] temp = (T[]) new Object[(int)(items.length * refactor)];
        if(last > first) {
            System.arraycopy(items, first, temp, temp.length/2-1, last - first);
        } else {
            System.arraycopy(items, 0, temp, 0, last);
            System.arraycopy(items, first, temp,temp.length - (items.length - first), items.length - first);
        }
        items = temp;
    }

    //help reset the First index when larger the length of the deque
    public void reIndexFirstAdd() {
        double increaseRefactor = 1.5;
        int capacity = (int) (items.length * increaseRefactor);
        int befResizeLen = items.length;
        resize(increaseRefactor);
        if(first < last) {
            first = items.length/2-1;
            last = first + size + 1;
        } else {
            first = first + capacity - befResizeLen;
        }
    }

    //help reset the First index when reduce the length of the deque
    public void reIndexFirstRemove() {
        double decreaseRefactor = 0.5;
        int capacity = (int) (items.length * decreaseRefactor);
        int befResizeLen = items.length;
        resize(decreaseRefactor);
        if(first < last) {
            first = items.length/2-1;
            last = first + size + 1;
        } else {
            first = first + capacity - befResizeLen;
        }
    }

    //reset the first and last pointer
    private void resetPtr() {
        if(size == 0) {
            items[first] = null;
            items[last] = null;
            first = items.length - 1;
            last = 0;
        }
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wisPos;
        public ArrayDequeIterator() {
            wisPos = 0;
        }
        @Override
        public boolean hasNext() {
            return wisPos < size;
        }

        @Override
        public T next() {
            if(wisPos < items.length - first - 1) {
                T retItem = items[first + wisPos + 1];
                wisPos = wisPos + 1;
                return retItem;
            } else {
                if(first == items.length - 1) {
                    T retItem = items[wisPos];
                    wisPos = wisPos + 1;
                    return retItem;
                } else {
                    T retItem = items[wisPos - last];
                    wisPos = wisPos + 1;
                    return retItem;
                }
            }
        }
    }
}
