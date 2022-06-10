package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            arrayDeque.addFirst(i);
        }
    }

    @Test
    public void testBigAddFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 100000; i++) {
            arrayDeque.addFirst(i);
        }
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            arrayDeque.addLast(i);
        }
    }

    @Test
    public void testAddLastAndFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            arrayDeque.addFirst(i);
        }

        for (int i = 5; i < 10; i++) {
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
        for (int i = 0; i < 10; i++) {
            arrayDeque.addFirst(i);
        }

        for (int i = 0; i < 10; i++) {
            arrayDeque.removeFirst();
        }
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            arrayDeque.addLast(i);
        }

        for (int i = 0; i < 5; i++) {
            arrayDeque.removeLast();
        }

        arrayDeque.printDeque();
        assertTrue("arrayDeque should be empty upon initialization", arrayDeque.isEmpty());
    }

    @Test
    public void testBigDequeRemoveFrist() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 15000; i++) {
            arrayDeque.addFirst(i);
        }

        for (int i = 0; i < 15000; i++) {
            arrayDeque.removeFirst();
        }
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());
    }

    @Test
    public void getByIndexFromAddFirst() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            arrayDeque.addFirst(i);
        }

        int a = arrayDeque.get(0);
        assertEquals(Integer.valueOf(8), arrayDeque.get(0));
    }

    @Test
    public void getByIndexFromAddLast() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
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

    @Test
    public void testEqualsNull() {
        ArrayDeque<Integer> a = null;
        ArrayDeque<Integer> b = new ArrayDeque<>();
        b.addLast(1);
        assertFalse("it shoud be false", b.equals(a));
    }

    @Test
    public void testEqualsNotSameSize() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        b.addLast(1);
        b.addLast(1);
        b.addLast(1);
        a.addLast(1);
        assertFalse("a should not equal to b", b.equals(a));
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            a.addLast(i);
            b.addLast(i);
        }

        assertTrue("a should not equal to b", b.equals(a));
    }

    @Test
    public void testNotEquals() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            a.addLast(i);
            b.addFirst(i);
        }

        assertFalse("a should not equal to b", b.equals(a));
    }


    @Test
    public void testEqualsString() {
        ArrayDeque<String> a = new ArrayDeque<>();
        ArrayDeque<String> b = new ArrayDeque<>();
        for (int i = 0; i < 10000; i++) {
            a.addFirst(String.valueOf(i));
            b.addFirst(String.valueOf(i));
        }
        assertTrue("a should equal to b", b.equals(a));
    }

    @Test
    public void testIteratorAddFirst() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 15; i++) {
            a.addFirst(String.valueOf(i));
        }

        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }

    @Test
    public void testIteratorAddLast() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addLast(String.valueOf(i));
        }

        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }

    @Test
    public void testIteratorAddLastAndFirst() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addLast(String.valueOf(i));
        }

        for (int i = 5; i < 10; i++) {
            a.addFirst(String.valueOf(i));
        }

        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }

    @Test
    public void testAddFirstRemoveLast() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addFirst(String.valueOf(i));
        }

        for (int i = 5; i < 10; i++) {
            a.removeLast();
        }

        assertTrue("the deque should be empty", a.isEmpty());
    }

    @Test
    public void testAddLastRemoveFirst() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addFirst(String.valueOf(i));
        }

        for (int i = 5; i < 10; i++) {
            a.removeLast();
        }

        assertTrue("the deque should be empty", a.isEmpty());
    }

    @Test
    public void testRemoveFirstVal() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addFirst(String.valueOf(i));
        }


        assertEquals("0", a.removeLast());
    }

    @Test
    public void testEqualsDL() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addFirst(String.valueOf(i));
        }

        LinkedListDeque<String> b = new LinkedListDeque<>();
        for (int i = 0; i < 5; i++) {
            b.addFirst(String.valueOf(i));
        }

        assertTrue("a and be should be equals", a.equals(b));
    }

    @Test
    public void testIteratorNextManyTimes() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addLast(String.valueOf(i));
        }

        Iterator<String> it = a.iterator();
        for (int i = 0; i < 100; i++) {
            it.next();
        }
    }

    @Test
    public void testIteratorHasNext() {
        ArrayDeque<String> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addLast(String.valueOf(i));
        }

        Iterator<String> it = a.iterator();
        for (int i = 0; i < 100000; i++) {
            it.next();
        }

        assertFalse("it should has no next", it.hasNext());
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < 20; i++) {
            arrayDeque.addLast(i);
        }

        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();


        for (int i = 0; i < 20; i++) {
            arrayDeque.addFirst(i);
        }

        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();

        int index = 0;
        for (int item : arrayDeque) {
            int a = item;
            int b = arrayDeque.get(index);
            assertEquals("Should be equal", a, b);
            index += 1;
        }
    }
}