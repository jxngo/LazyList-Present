
// 9.7 Lazy Synchronization
public class LazyList<T extends Comparable<T>> {
    // First node (head)
    private Node<T> head;
    
    // Constructor
    public LazyList() {
        this.head = new Node<>(Integer.MIN_VALUE);
        this.head.next = new Node<>(Integer.MAX_VALUE);
    }

    private boolean validate(Node <T> pred, Node<T> curr) {
        return !pred.marked && !curr.marked && pred.next == curr;
    }

    // LazyList add function
    public boolean add(T item) {
        int key = item.hashCode();
        while (true) {
            Node pred = head;
            Node curr = head.next;
            while (curr.key < key) {
                pred = curr; curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred,curr)) {
                        if (curr.key == key) {
                            return false;
                        } else {
                            Node node = new Node(item);
                            node.next = curr;
                            pred.next = node;
                            return true;
                        }
                    } 
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }

    // LazyList remove function
    public boolean remove(T item) {
        int key = item.hashCode();
        while (true) {
            Node pred = head;
            Node curr = head.next;
            while (curr.key < key) {
                pred = curr; curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred,curr)) {
                        if (curr.key != key) {
                            return false;
                        } else {
                            curr.marked = true;
                            pred.next = curr.next;
                            return true;
                        }
                    } 
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }

     // LazyList contains function
     public boolean contains(T item) {
        int key = item.hashCode();
        Node curr = head;
        while (curr.key < key) 
            curr = curr.next;
        return curr.key == key && !curr.marked;
    }
}
