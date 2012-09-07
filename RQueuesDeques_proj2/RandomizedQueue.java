/**
 * Name      : Andrew Rickert<br>
 * Login     : ironapple@gmail.com<br>
 * Date      : 08/28/12<br>
 * Purpose   : Implements a random queue data structure<br>
 * Execution : An object is initialized and random values can be drawn from it
 *             as long as value remain in the queue
 */
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int size;
    private int capacity;
    private Object[] items;
    
    private class Node {
        private Item item;
        
        Node(Item item) {
            this.item = item;
        }
        
        Item getItem() {
            return item;
        }
        
        void setItem(Item newitem) {
            this.item = newitem;
        }
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] randomIndices;
        private int index;
        private int currentSize;
        
        public RandomizedQueueIterator() {

            currentSize = size;
            
            randomIndices = new int[currentSize];
            index = 0;
            
            // Set up all the indices in proper order
            for (int i = 0; i < randomIndices.length; i++)
                randomIndices[i] = i;
            
            if (currentSize > 0) {
                int randomIndex = StdRandom.uniform(currentSize);
            
                // Mix up the indices randomly
                for (int i = 0; i < randomIndices.length; i++) {
                
                    randomIndex = StdRandom.uniform(currentSize);
                    swap(i, randomIndex);
                
                }
                
            }
            
            /* It's weird to me that this compiles since it's in the class we 
               are trying to create but this would work too. You would load an 
               a randomized queue with all the indices you want to put in your
               array. You would then do the randomized dequeue operation 
               implemented in this class until there are no more elements. Each
               element dequed would be put in the next open position at the end
               of the array.
             
            RandomizedQueue<Integer> randomIntStore = 
                                           new RandomizedQueue<Integer>();
            randomIntStore.enqueue(0);
            randomIntStore.enqueue(1);
            randomIntStore.enqueue(2);
            randomIntStore.enqueue(3);
            
            randomIntStore.dequeue();
            randomIntStore.dequeue();
            randomIntStore.dequeue();
            randomIntStore.dequeue();
            */
        }
        
        private void swap(int i, int j) {
            int temp = randomIndices[i];
            randomIndices[i] = randomIndices[j];
            randomIndices[j] = temp;
        }
        
        public Item next() {
           
            //return ((Node) items[randomIndices[index++]]).item;
            Item item = ((Node) items[randomIndices[index++]]).getItem();
            return item;
        }
        
        public boolean hasNext() {
            return index < currentSize;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
     
    
    
    
    public RandomizedQueue() {                     // construct an empty deque
        size = 0;
        capacity = 1;
        items = new Object[capacity];
        
    }
    
    public boolean isEmpty()  {         // is the deque empty?
        return size == 0;
    }
    
    public int size() {                // return the number of items on the deque
        return size;
    }
    
    private void resizeArray(int newCapacity) {
   
        if (newCapacity > 0) {
            
            Object[] resizedItemsArray = new Object[newCapacity];
            
            for (int i = 0; i < capacity; i++)
                resizedItemsArray[i] = items[i];
            
            for (int i = capacity; i < newCapacity; i++)
                resizedItemsArray[i] = null;
            
            capacity = newCapacity;
            items = resizedItemsArray;
            
        }
        
    }
    
    public void enqueue(Item item) {    // add the item
        
        if (item == null)
            throw new NullPointerException();
            
        if (size + 1 > capacity)
            resizeArray(2*capacity);
        
        items[size++] = new Node(item);

    }
    
    public Item dequeue() {             // delete and return a random item
        
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        int randomIndex = StdRandom.uniform(size);
        
        Item randomItem = ((Node) items[randomIndex]).item;
        
        if (size - 1 != randomIndex)             
            items[randomIndex] = items[size-1];

        items[size-1] = null;
        size--;
        
        System.out.println("Capacity: " + capacity + " -- size: " + size);
        
        if (size == capacity/4)
            resizeArray(capacity/2);
        
        return randomItem;
    }
    
    public Item sample() {            // return (but do not delete) a random item
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        int randomIndex = StdRandom.uniform(size);
        
        Item randomItem = ((Node) items[randomIndex]).getItem();
        
        return randomItem;
    }
        
    public Iterator<Item> iterator() {  // return an iterator over items in 
                                        // order from front to end
        return new RandomizedQueueIterator();
    }
}