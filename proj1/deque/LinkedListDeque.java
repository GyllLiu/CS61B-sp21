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
    public boolean isEmpty() {
        return (sentinal.next == null && sentinal.prev == null) || (sentinal.next == sentinal.prev && size == 0);
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
        if((sentinal.next == sentinal.prev && size == 0) || sentinal.next == null) {
            return null;
        }
        T sNextVal = sentinal.next.value;
        sentinal.next = sentinal.next.next;
        sentinal.next.next.prev = sentinal;
        size = size-1;
        return sNextVal;
    }

    @Override
    public T removeLast() {
        if((sentinal.next == sentinal.prev && size == 0) || sentinal.next == null) {
            return null;
        }
        T sPrevVal = sentinal.prev.value;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.prev.next = sentinal;
        size = size-1;
        return sPrevVal;
    }

    @Override
    public T get(int index) {
        ListNode p = sentinal.next;
        while (index != 0) {
            p = p.next;
        }
        return p.value;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
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
