package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest{

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
}
