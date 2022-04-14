// Get Java linkedlist
// Lazy List from book 

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

class Servant implements Runnable {
    private int servantNo;
    private LazyList list;
    private List <Integer> bag;
    private final AtomicInteger removeCounter = new AtomicInteger(0);
    private final AtomicInteger addCounter = new AtomicInteger(0);
    private final AtomicInteger containsCounter = new AtomicInteger(0);
    private final AtomicBoolean finished = new AtomicBoolean();
    
    public Servant(int servantNo, List<Integer> bag, LazyList list) {
        this.servantNo = servantNo;
        this.bag = bag;
        this.list = list;
    }

    @Override 
    public void run() {
        while (!finished.get()) {
            if (removeCounter.get() == 9) {
                finished.compareAndSet(false, true);
                continue;
            }
            // Random choice between 1,2, and 3
            int rand = new Random().nextInt(3) + 1;
            // Add onto LL
            if (rand == 1) {
                int add = addCounter.get();
                if (list.add(bag.get(add))) addCounter.incrementAndGet();
            }
            // Remove from LL
            else if (rand == 2) {
                int removeRand = new Random().nextInt(10);
                if (list.remove(bag.get(removeRand))) {
                    System.out.println("Thank You Note sent out for:" + removeRand + " " +servantNo);
                    removeCounter.incrementAndGet();
                }
            }
            else {
                int contains = containsCounter.incrementAndGet();
                if (list.contains(contains)) System.out.println("Found inside lazylist");
            }

        }
    }
}
public class sortPresents {
    public List<Integer> bag;
    public LazyList<Integer> list;
    public static void main(String args[]) throws Exception {
        // Total of 500,000 presents
        int presents = 10;
        sortPresents sortPresents = new sortPresents();
        sort(sortPresents,presents);
    }
    public static void sort(sortPresents m, int nPresents) {
        // Make unsorted unique integer arraylist of 500000
        m.bag = new ArrayList<>(nPresents);
        // Make the LazyList
        m.list = new LazyList<>();
        // Unsorted and unique array
        for (int i = 0; i <= nPresents - 1; i ++) m.bag.add(i);
        Collections.shuffle(m.bag);
        for (int i = 0; i < 4; i++) {
            Servant servant = new Servant(i, m.bag, m.list);
            Thread thrServant = new Thread(servant);
            thrServant.start();
        }
    }
}