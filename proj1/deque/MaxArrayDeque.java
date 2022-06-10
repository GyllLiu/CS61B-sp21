package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private T max;
    private Comparator<T> maxComparator;

    public MaxArrayDeque(Comparator<T> c) {
        maxComparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }

        int maxInd = findMaxInd(maxComparator);
        return get(maxInd);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }

        int maxInd = findMaxInd(c);
        return get(maxInd);
    }

    private int findMaxInd(Comparator<T> c) {

        int max = 0, ind = 1;

        while (ind < this.size()) {
            if (c.compare(this.get(max), this.get(ind)) < 0) {
                max = ind;
            }
            ind = ind + 1;
        }
        return max;
    }
}
