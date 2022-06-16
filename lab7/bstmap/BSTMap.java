package bstmap;

import com.puppycrawl.tools.checkstyle.checks.annotation.AnnotationUseStyleCheck;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private BSTNode root;
    private int size;
    private class BSTNode {
        private K key;           // sorted by key
        private V val;         // associated data
        private BSTNode left, right;  // left and right subtrees
         // number of nodes in subtree

        private BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public BSTMap() {
        root = new BSTNode(null, null);
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (size() == 0) {
            this.root = new BSTNode(key, value);
        }
        this.root = putHelper(root, key, value);
        this.size += 1;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        addKey(set, root);
        return set;
    }

    private void addKey(Set<K> set, BSTNode root) {
        if(root == null || root.key == null) {
            return;
        }
        set.add(root.key);
        addKey(set, root.left);
        addKey(set, root.right);
    }

    @Override
    public V remove(K key) {
        if (key == null || root == null || root.key == null) {
            return null;
        }
        BSTNode retNode = root;
        while (retNode != null) {
            if(key.compareTo(retNode.key) < 0) {
                retNode = retNode.left;
            } else if (key.compareTo(retNode.key) > 0) {
                retNode = retNode.right;
            } else {
                break;
            }
        }

        this.root = delete(root, key);
        size = size - 1;
        return retNode.val;
    }

    private BSTNode delete(BSTNode node, K key) {
        if(key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key);
        } else {
            if(node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                BSTNode minNode = findMin(node.right); //find smallest key in right subtree
                minNode.right = deleteMin(node.right);
                minNode.left = node.left;
                node = minNode;
            }
        }
        return node;
    }

    private BSTNode deleteMin(BSTNode node) {
        if(node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        if(node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Not support this operation");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private V getHelper(BSTNode root, K key) {
        if (root == null || key == null || root.key == null) {
            return null;
        }

        if(root.key.equals(key)) {
            return root.val;
        }

        return getHelper(root.right, key) != null ? getHelper(root.right, key) : getHelper(root.left, key);
    }

    private boolean containsKeyHelper(BSTNode root, K key) {
        if(root == null || key == null || root.key == null) {
            return false;
        }

        if(root.key.compareTo(key) == 0) {
            return true;
        }

        return containsKeyHelper(root.right, key) || containsKeyHelper(root.left, key);
    }

    private BSTNode putHelper(BSTNode root, K key, V val) {
        if(root == null) {
            return new BSTNode(key, val);
        }

        if(root.key.compareTo(key) < 0) {
             root.right = putHelper(root.right, key, val);
        } else if(root.key.compareTo(key) > 0) {
            root.left = putHelper(root.left, key, val);
        }
        return root;
    }
}
