package deque;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayDequeTest {

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            arrayDeque.addFirst(i);
        }
    }

    @Test
    public void testBigAddFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 100000; i++) {
            arrayDeque.addFirst(i);
        }
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            arrayDeque.addLast(i);
        }
    }

    @Test
    public void testAddLastAndFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 5; i++) {
            arrayDeque.addFirst(i);
        }

        for(int i = 5; i < 10; i++) {
            arrayDeque.addLast(i);
        }
    }

    @Test
    public void testEmptyDeque() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            arrayDeque.addFirst(i);
        }

        for(int i = 0; i < 10; i++) {
            arrayDeque.removeFirst();
        }
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            arrayDeque.addLast(i);
        }

        for(int i = 0; i < 10; i++) {
            arrayDeque.removeLast();
        }
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());
    }

    @Test
    public void testBigDequeRemoveFrist() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 15000; i++) {
            arrayDeque.addFirst(i);
        }

        for(int i = 0; i < 15000; i++) {
            arrayDeque.removeFirst();
        }
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());
    }

    @Test
    public void getByIndexFromAddFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 9; i++) {
            arrayDeque.addFirst(i);
        }

        int a = arrayDeque.get(0);
        assertEquals(Integer.valueOf(8), arrayDeque.get(0));
    }

    @Test
    public void getByIndexFromAddLast() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            arrayDeque.addLast(i);
        }
        int a = arrayDeque.get(0);
        assertEquals(Integer.valueOf(0), arrayDeque.get(0));
    }

    @Test
    public void removeEmpty() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }


    @Test
    public void negativeSize() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }
}