package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>  {
    private int size;
    private ListNode sentinal;

    private class ListNode {
        public T value;
        private ListNode next;
        private ListNode prev;

        public ListNode (T v, ListNode n, ListNode p) {
            value = v;
            next = n;
            prev = p;
        }
    }

    // construct a null LinkedListDeque
    public LinkedListDeque() {
        size = 0;
        sentinal = new ListNode(null, null, null);
    }

    @Override
    public void addFirst(T item) {
        if(sentinal.next == null) {
            ListNode temp  = new ListNode(item, sentinal, sentinal);
            sentinal.prev = temp;
            sentinal.next = temp;
        } else {
            ListNode sNext = sentinal.next;
            ListNode temp  = new ListNode(item, sNext, sentinal);
            sentinal.next = temp;
            sNext.prev = temp;
        }
        size = size+1;
    }

    @Override
    public void addLast(T item) {
        if(sentinal.prev == null) {
            ListNode temp  = new ListNode(item, sentinal, sentinal);
            sentinal.prev = temp;
            sentinal.next = temp;
        } else {
            ListNode sPrev = sentinal.prev;
            ListNode temp  = new ListNode(item, sentinal, sPrev);
            sentinal.prev = temp;
            sPrev.next = temp;
        }
        size = size+1;
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
        if(size == 0) {
            return null;
        }

        ListNode temp = sentinal.next;
        T sNextVal = sentinal.next.value;
        sentinal.next = sentinal.next.next;
        sentinal.next.prev = sentinal;
        size = size-1;
        temp.next = null;
        temp.prev = null;
        return sNextVal;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        ListNode temp = sentinal.prev;
        T sPrevVal = sentinal.prev.value;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.next = sentinal;
        size = size-1;
        temp.next = null;
        temp.prev = null;
        return sPrevVal;
    }

    @Override
    public T get(int index) {
        ListNode p = sentinal.next;
        int i = 0;
        while (i < index) {
            p = p.next;
            i = i + 1;
        }
        return p.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedDequeIterator();
    }

    private class LinkedDequeIterator implements Iterator<T> {
        private int wisPos = 0;
        @Override
        public boolean hasNext() {
            return wisPos < size;
        }

        @Override
        public T next() {
            T retItem = get(wisPos);
            wisPos += 1;
            return retItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) { return false; }

        if (o instanceof LinkedListDeque) {
            if(((LinkedListDeque<?>) o).size() != size) {
                return false;
            }

            for(int i = 0; i < ((LinkedListDeque<?>) o).size(); i++) {
                if(((LinkedListDeque<?>) o).get(i) == null) {
                    continue;
                }

                if(!((LinkedListDeque<?>) o).get(i).equals(get(i))) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    public T getRecursive(int index) {
        return getHelper(index, sentinal);
    }

    //getRecursive helper function
    public T getHelper(int index, ListNode node) {
        if(index == 0) {
            return node.next.value;
        } else {
            return getHelper(index--, node);
        }
    }
}
