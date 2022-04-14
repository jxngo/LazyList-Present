import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node<T extends Comparable<T>> {
    T item;
    int key;
    Node<T> next;
    boolean marked;
    boolean tagged;
    Lock lock;


    // Constructor
    Node (T item) {
        this.item = item;
        this.key = item.hashCode();
        this.next = null;
        this.marked = false;
        this.tagged = false;
        this.lock = new ReentrantLock();
    }
    // Sentinel Node Constructor
    Node(int key) {
        this.item = null;
        this.key = key;
        this.next = null;
        this.marked = false;
        this.tagged = false;
        this.lock = new ReentrantLock();
    }

    Node(T item, boolean tag) {
        this.item = item;
        this.key = item.hashCode();
        this.next = null;
        this.marked = false;
        this.tagged = tag;
        this.lock = new ReentrantLock();
    }

    void lock() {
        // lock node
        lock.lock();
    }

    void unlock() {
        lock.unlock();
    }
}
