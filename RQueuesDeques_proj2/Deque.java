/**
 * Name      : Andrew Rickert<br>
 * Login     : ironapple@gmail.com<br>
 * Date      : 08/28/12<br>
 * Purpose   : Implements a deque, double ended queue data structure<br>
 * Execution : An object is initialized and values can be pushed or popped from
 *             either end of the queue
 */
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
        
        Node() {
        
        }
        
        Node(Item item) {
            this.item = item;
        }
        
        Node getNext() {
            return next;
        }
        
        void setNext(Node newnext) {
            this.next = newnext;
        }
        
        Node getPrev() {
            return prev;
        }
        
        void setPrev(Node newprev) {
            this.prev = newprev;
        }
        
    }
     
    private Node head;
    private Node tail;
    private int size;
    
    private class DequeIterator implements Iterator<Item> {
        
        private Node currentNode;
        
        public DequeIterator() {
            currentNode = head;
        }
        
        public Item next() {
            
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            
            Item returnItem = currentNode.item;
            
            // The iterator will cause a null pointer exception if hasNext is
            // not checked before this operation
            currentNode = currentNode.next;
            return returnItem;
        }
        
        public boolean hasNext() {
            return currentNode != null;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
     
    
    
    
    public Deque() {                     // construct an empty deque
        head = null;
        tail = null;
        size = 0;
    }
    
    public boolean isEmpty()  {         // is the deque empty?
        return size == 0;
    }
    
    public int size() {                // return the number of items on the deque
        return size;
    }
    
    public void addFirst(Item item) {    // insert the item at the front
        
        if (item == null)
            throw new java.lang.NullPointerException();
            
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = null;
        newFirst.prev = null;
        
        if (!isEmpty()) {
            newFirst.next = head;
            head.prev = newFirst;
        }
        
        head = newFirst;
        
        if (isEmpty())
            tail = newFirst;
        
        size++;
    }
    
    public void addLast(Item item) {    // insert the item at the end
    
        if (item == null)
            throw new java.lang.NullPointerException();
        
        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;
        newLast.prev = null;
        
        if (!isEmpty()) {
            newLast.prev = tail;
            tail.next = newLast;
        }
        
        tail = newLast;
        
        if (isEmpty())
            head = newLast;
        
        size++;
        
    }
    
    public Item removeFirst() {         // delete and return the item at the front
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item removedItem = head.item;
        
        if (size() == 1) {
            
            head = null;
            tail = null;
            size = 0;
            return removedItem;
        }
        
        // We have more than one element in the deque
        head.next.prev = null;
        head = head.next;
        size--;
        
        return removedItem;
    }
    
    public Item removeLast() {          // delete and return the item at the end
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item removedItem = tail.item;
        
        if (size() == 1) {
            head = null;
            tail = null;
            size = 0;
            return removedItem;
        }
        
        // We have more than one element in the deque
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        
        return removedItem;
    }
    
    public Iterator<Item> iterator() {  // return an iterator over items 
                                        // in order from front to end
        return new DequeIterator();
    }
}