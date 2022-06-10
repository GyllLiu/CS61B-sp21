package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {

    @Test
    public void getMaxTests() {
        MaxArrayDeque<String> maxArrayDeque = new MaxArrayDeque<>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        maxArrayDeque.addLast("1");
        maxArrayDeque.addLast("2");
        maxArrayDeque.addLast("3");
        String max = maxArrayDeque.max();

        assertEquals("Max should be \"3\"", "3", max);
    }

    @Test
    public void getMaxTestsLarger() {
        MaxArrayDeque<String> maxArrayDeque = new MaxArrayDeque<>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        maxArrayDeque.addLast(String.valueOf(1));
        maxArrayDeque.addLast(String.valueOf(2));
        maxArrayDeque.addLast(String.valueOf(4));
        maxArrayDeque.addLast(String.valueOf(5));
        maxArrayDeque.addLast(String.valueOf(9));
        maxArrayDeque.addLast(String.valueOf(8));
        maxArrayDeque.addLast(String.valueOf(9));
        maxArrayDeque.addLast(String.valueOf(2));


        String max = maxArrayDeque.max();


        assertEquals("Max should be \"9\"", "9", max);
    }

    @Test
    public void getMaxByComparator() {
        MaxArrayDeque<String> maxArrayDeque = new MaxArrayDeque<>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        Comparator myStringComparator = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };


        maxArrayDeque.addLast(String.valueOf(1));
        maxArrayDeque.addLast(String.valueOf(2));
        maxArrayDeque.addLast(String.valueOf(4));
        maxArrayDeque.addLast(String.valueOf(5));
        maxArrayDeque.addLast(String.valueOf(9));
        maxArrayDeque.addLast(String.valueOf(8));
        maxArrayDeque.addLast(String.valueOf(9));
        maxArrayDeque.addLast(String.valueOf(2));


        String max = maxArrayDeque.max(myStringComparator);


        assertEquals("Max should be \"9\"", "9", max);
    }

    @Test
    public void randomizedTestIncludingGet() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(new Comparator<Integer>() {
            public int compare(Integer s1, Integer s2) {
                return s1 - s2;
            }
        });


        maxArrayDeque.addLast(0);
        assertEquals(Integer.valueOf(0), maxArrayDeque.removeFirst());
        maxArrayDeque.addFirst(2);
        assertEquals(Integer.valueOf(2), maxArrayDeque.get(0));

        maxArrayDeque.addLast(4);
        maxArrayDeque.addFirst(5);
        maxArrayDeque.addLast(6);
        maxArrayDeque.addFirst(7);
        assertEquals(Integer.valueOf(6), maxArrayDeque.removeLast());
        assertEquals(Integer.valueOf(7), maxArrayDeque.removeFirst());
        assertEquals(Integer.valueOf(2), maxArrayDeque.get(1));
        assertEquals(Integer.valueOf(4), maxArrayDeque.get(2));
        maxArrayDeque.addLast(12);
        maxArrayDeque.addFirst(13);
        maxArrayDeque.addFirst(14);
        assertEquals(Integer.valueOf(12), maxArrayDeque.removeLast());
        assertEquals(Integer.valueOf(4), maxArrayDeque.removeLast());
        assertEquals(Integer.valueOf(14), maxArrayDeque.removeFirst());
        assertEquals(Integer.valueOf(2), maxArrayDeque.removeLast());
        maxArrayDeque.addFirst(19);
        assertEquals(Integer.valueOf(13), maxArrayDeque.get(1));
        assertEquals(Integer.valueOf(13), maxArrayDeque.get(1));
        maxArrayDeque.addLast(22);
    }
}
