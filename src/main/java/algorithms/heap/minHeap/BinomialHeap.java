package algorithms.heap.minHeap;

import metrics.PerformanceTracker;

public class BinomialHeap implements MinHeap {
    public static class Node {
        int key;
        int degree;
        Node parent;
        Node child;
        Node sibling;
        Node(int key) { this.key = key; }
        public int getKey() { return key; }
    }

    private Node head;
    private int size;
    private final PerformanceTracker tracker;

    public BinomialHeap(PerformanceTracker tracker) {
        this.tracker = tracker;
        this.head = null;
        this.size = 0;
    }

    @Override
    public Node insert(int key) {
        BinomialHeap h = new BinomialHeap(tracker);
        Node n = new Node(key);
        h.head = n;
        h.size = 1;
        merge(h);
        return n;
    }

    private Node findNodeByKey(Node root, int key) {

        if (root == null) return null;
        if (root.key == key) return root;
        Node res = findNodeByKey(root.child, key);
        if (res != null) return res;
        return findNodeByKey(root.sibling, key);
    }

    @Override
    public int findMin() {
        if (head == null) return Integer.MIN_VALUE;
        Node y = null;
        Node x = head;
        int min = Integer.MAX_VALUE;
        while (x != null) {
            if (tracker.cmp(x.key < min)) {
                min = x.key;
                y = x;
            }
            x = x.sibling;
        }
        return min;
    }

    @Override
    public int extractMin() {
        if (head == null) throw new IllegalStateException("Heap is empty");

        Node prevMin = null;
        Node minNode = head;
        Node prev = null;
        Node curr = head;
        int min = curr.key;
        while (curr != null) {
            if (tracker.cmp(curr.key < min)) {
                min = curr.key;
                prevMin = prev;
                minNode = curr;
            }
            prev = curr;
            curr = curr.sibling;
        }

        if (prevMin == null) head = minNode.sibling;
        else prevMin.sibling = minNode.sibling;

        Node child = minNode.child;
        Node rev = null;
        while (child != null) {
            Node next = child.sibling;
            child.parent = null;
            child.sibling = rev;
            rev = child;
            child = next;
            tracker.swap();
        }
        BinomialHeap h = new BinomialHeap(tracker);
        h.head = rev;

        union(h);
        size--;
        return minNode.key;
    }

    @Override
    public void decreaseKey(Node x, int newKey) {
        if (x == null) throw new IllegalArgumentException("Node is null");
        if (tracker.cmp(newKey > x.key)) {
            throw new IllegalArgumentException("new key is greater than current key");
        }
        x.key = newKey;
        Node y = x;
        Node z = y.parent;
        while (z != null && tracker.cmp(y.key < z.key)) {
            // swap keys to bubble up
            int tmp = y.key;
            y.key = z.key;
            z.key = tmp;
            tracker.swap();
            y = z;
            z = y.parent;
        }
    }

    @Override
    public void merge(BinomialHeap other) {
        if (other == null || other.head == null) return;
        union(other);
        size += other.size;
        other.head = null;
        other.size = 0;
    }

    private void union(BinomialHeap other) {
        head = mergeRootLists(this.head, other.head);
        if (head == null) return;

        Node prev = null;
        Node curr = head;
        Node next = curr.sibling;

        while (next != null) {
            if (curr.degree != next.degree ||
                (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else if (tracker.cmp(curr.key <= next.key)) {
                curr.sibling = next.sibling;
                link(next, curr);
            } else {
                if (prev == null) head = next;
                else prev.sibling = next;
                link(curr, next);
                curr = next;
            }
            next = curr.sibling;
        }
    }

    private void link(Node y, Node z) {

        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
        tracker.swap();
    }

    private Node mergeRootLists(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        Node headMerged;
        Node tail;
        if (tracker.cmp(h1.degree <= h2.degree)) {
            headMerged = h1;
            h1 = h1.sibling;
        } else {
            headMerged = h2;
            h2 = h2.sibling;
        }
        tail = headMerged;
        while (h1 != null && h2 != null) {
            if (tracker.cmp(h1.degree <= h2.degree)) {
                tail.sibling = h1;
                h1 = h1.sibling;
            } else {
                tail.sibling = h2;
                h2 = h2.sibling;
            }
            tail = tail.sibling;
        }
        tail.sibling = (h1 != null) ? h1 : h2;
        return headMerged;
    }

    @Override public int size() { return size; }
    @Override public boolean isEmpty() { return size == 0; }
}
