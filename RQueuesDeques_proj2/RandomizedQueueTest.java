import java.util.Iterator;

public class RandomizedQueueTest {
    
    public static void main(String args[]) {
 
        /*
        RandomizedQueue<String> randomStringQueue = new RandomizedQueue<String>();
        randomStringQueue.enqueue("Bob");
        randomStringQueue.enqueue("Steve");
        randomStringQueue.enqueue("John");
        randomStringQueue.enqueue("Joe");
        
        System.out.println("Random value: " + randomStringQueue.dequeue());
        System.out.println("Random value: " + randomStringQueue.dequeue());
        System.out.println("Random value: " + randomStringQueue.dequeue());
        System.out.println("Random value: " + randomStringQueue.dequeue());
        */
        
        
        RandomizedQueue<String> randomStringQueue;
        Iterator<String> itr;
        
        // Test Empty
        randomStringQueue = new RandomizedQueue<String>();
        assert(randomStringQueue.isEmpty());
        
        // Test adding one value to the queue is not empty
        randomStringQueue = new RandomizedQueue<String>();
        randomStringQueue.enqueue("Bob");
        assert(!randomStringQueue.isEmpty());
 
        // Test adding one value to the queue then deleting from queue
        // gives an empty RandomizedQueue
        randomStringQueue = new RandomizedQueue<String>();
        randomStringQueue.enqueue("Bob");
        randomStringQueue.dequeue();
        assert(randomStringQueue.isEmpty());
        
        // Test iterator empty
        randomStringQueue = new RandomizedQueue<String>();
        itr = randomStringQueue.iterator();
        assert(!itr.hasNext());
        
        // Test adding one value to the queue is not empty when the iterator
        // is run
        randomStringQueue = new RandomizedQueue<String>();
        randomStringQueue.enqueue("Bob");
        itr = randomStringQueue.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Bob"));
 
        // Test adding one value to the queue then deleting from queue
        // gives an empty RandomizedQueue
        randomStringQueue = new RandomizedQueue<String>();
        randomStringQueue.enqueue("Bob");
        randomStringQueue.dequeue();
        itr = randomStringQueue.iterator();
        assert(!itr.hasNext());
        
        
        // Test adding one value to the queue then deleting from queue
        // gives an empty RandomizedQueue
        randomStringQueue = new RandomizedQueue<String>();
        randomStringQueue.enqueue("Bob");
        randomStringQueue.enqueue("Steve");
        randomStringQueue.enqueue("Joe");
        randomStringQueue.enqueue("Martin");
        randomStringQueue.enqueue("Barry");
        randomStringQueue.enqueue("John");
        randomStringQueue.enqueue("Aaron");
        itr = randomStringQueue.iterator();
        while(itr.hasNext())
            System.out.print(itr.next() + " ");
        System.out.println();
        
        
        System.out.println("Completed testing!");
        
        
    }
    
}