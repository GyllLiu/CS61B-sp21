package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private double loadFactor = 0.75;

    private static final double factor = 1.5;

    private static final int INIT_CAPACITY = 16;

    private int tableSize;

    /** Constructors */
    public MyHashMap() {
        this.size = 0;
        this.buckets = createTable(INIT_CAPACITY);
        this.tableSize = INIT_CAPACITY;
    }

    public MyHashMap(int initialSize) {
        this.size = 0;
        this.buckets = createTable(initialSize);
        this.tableSize = initialSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.buckets = createTable(initialSize);
        this.loadFactor = maxLoad;
        this.tableSize = initialSize;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return (Collection<Node>[]) new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        this.size = 0;
        this.buckets = createTable(INIT_CAPACITY);
        this.tableSize = INIT_CAPACITY;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("input key is null");
        int i = hash(key);
        if(buckets[i] != null) {
            for(Node node : buckets[i]) {
                if(node.key.equals(key)) return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(key == null) throw new IllegalArgumentException("first argument to put() is null");
        if(this.size/tableSize > loadFactor) {
            resize(this.factor*tableSize);
        }
        int i = hash(key);
        if(buckets[i] == null) {
            buckets[i] = createBucket();
        }
        for (Node node: buckets[i]) {
            if(node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        buckets[i].add(createNode(key, value));
        this.size++;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for(int i = 0; i < this.tableSize; i++) {
            if(buckets[i] == null) continue;;
            for(Node node : buckets[i]) {
                keySet.add(node.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        int h = hash(key);
        if(buckets[h] == null) return null;
        for(Node node : buckets[h]) {
            if(node.key == key) {
                V val = node.value;
                buckets[h].remove(node);
                return val;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        int h = hash(key);
        for(Node node : buckets[h]) {
            if(node.key == key && node.value == value) {
                V val = node.value;
                buckets[h].remove(node);
                return val;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private int hash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (this.tableSize-1);
    }

    private void resize(double tableSize) {
        int tempTbSz = (int)tableSize;
        MyHashMap<K, V> temp = new MyHashMap<>(tempTbSz);
        for(K key : this.keySet()) {
            temp.put(key, this.get(key));
        }
        this.size = temp.size;
        this.tableSize = tempTbSz;
        this.buckets = temp.buckets;
    }
}
