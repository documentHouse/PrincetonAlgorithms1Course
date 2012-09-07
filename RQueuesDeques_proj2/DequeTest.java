import java.util.Iterator;

public class DequeTest {
    
    public static void main(String args[]) {
 
        Deque<String> deque;
        Iterator<String> itr;
        
        // Test Empty
        deque = new Deque<String>();
        assert(deque.isEmpty());
        
        // Test adding one value to the front is not empty
        deque = new Deque<String>();
        deque.addFirst("Bob");
        assert(!deque.isEmpty());
        
        // Test adding one value to the end is not empty
        deque = new Deque<String>();
        deque.addLast("Bob");
        assert(!deque.isEmpty());
 
        // Test adding one value to the front then deleting from front
        // gives an empty Deque
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.removeFirst();
        assert(deque.isEmpty());
 
        // Test adding one value to the end then deleting from end
        // gives an empty Deque
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.removeLast();
        assert(deque.isEmpty());        
        
        // Test adding one value to the front then deleting from end
        // gives an empty Deque
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.removeLast();
        assert(deque.isEmpty());
        
        // Test adding one value to the end then deleting from end
        // gives an empty Deque
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.removeFirst();
        assert(deque.isEmpty());  
     
        // Test adding one value to the front and checking the size is one
        deque = new Deque<String>();
        deque.addFirst("Bob");
        assert(deque.size() == 1);

        // Test adding one value to the end and checking the size is one
        deque = new Deque<String>();
        deque.addLast("Bob");
        assert(deque.size() == 1);
        
        // Test adding two values to the front and checking the size is two
        // then removing from the front and checking the size is 1
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addFirst("Steve");
        assert(deque.size() == 2);
        deque.removeFirst();
        assert(deque.size() == 1);

        // Test adding two values to the end and checking the size is two
        // then removing from the end and checking the size is 1
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addLast("Steve");
        assert(deque.size() == 2);
        deque.removeLast();
        assert(deque.size() == 1);
            
        // Test adding two values to the front and checking the size is two
        // then removing from the end and checking the size is 1
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addFirst("Steve");
        assert(deque.size() == 2);
        deque.removeLast();
        assert(deque.size() == 1);
        
        // Test adding two values to the end and checking the size is two
        // then removing from the front and checking the size is 1
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addLast("Steve");
        assert(deque.size() == 2);
        deque.removeFirst();
        assert(deque.size() == 1);
        
        // Test adding one value to the front and one to the end and checking 
        // the size is two then removing from the front and checking the size 
        // is 1
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addLast("Steve");
        assert(deque.size() == 2);
        deque.removeFirst();
        assert(deque.size() == 1);
        
        // Test adding one value to the front and one to the end and checking 
        // the size is two then removing from the end and checking the size 
        // is 1
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addLast("Steve");
        assert(deque.size() == 2);
        deque.removeLast();
        assert(deque.size() == 1);
        
        // Test adding one value to the end and one to the front and checking 
        // the size is two then removing from the front and checking the size 
        // is 1
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addFirst("Steve");
        assert(deque.size() == 2);
        deque.removeFirst();
        assert(deque.size() == 1);
        
        // Test adding one value to the end and one to the front and checking 
        // the size is two then removing from the front and checking the size 
        // is 1
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addFirst("Steve");
        assert(deque.size() == 2);
        deque.removeLast();
        assert(deque.size() == 1);
        
        // Test adding three values to the front and removing three values
        // from the front and checking that the deque is empty.
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addFirst("Steve");
        deque.addFirst("Joe");
        assert(deque.size() == 3);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        assert(deque.isEmpty());
                
        // Test adding three values to the front and removing three values
        // from the front and checking that the deque is empty.
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addLast("Steve");
        deque.addLast("Joe");
        assert(deque.size() == 3);
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        assert(deque.isEmpty());
        
        // Test adding three values to the front and removing three values
        // from the back and checking that the deque is empty.
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addFirst("Steve");
        deque.addFirst("Joe");
        assert(deque.size() == 3);
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        assert(deque.isEmpty());
        
        // Test adding three values to the end and removing three values
        // from the front and checking that the deque is empty.
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addLast("Steve");
        deque.addLast("Joe");
        assert(deque.size() == 3);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        assert(deque.isEmpty());
     
        
        // Test that the iterator does not 'have next' on an empty deque
        deque = new Deque<String>();
        itr = deque.iterator();
        assert(!itr.hasNext());
        
        // Test that the iterator does 'have next' when a deque has one element
        // added to the front
        deque = new Deque<String>();
        deque.addFirst("Bob");
        itr = deque.iterator();
        assert(itr.hasNext());
        
        // Test that the iterator does 'have next' when a deque has one element
        // added to the end
        deque = new Deque<String>();
        deque.addLast("Bob");
        itr = deque.iterator();
        assert(itr.hasNext());
        
        // Test that the iterator returns the correct value when one value is
        // added to the front of the deque and that afterwards there are no
        // more elements to iterate through
        deque = new Deque<String>();
        deque.addFirst("Bob");
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Bob"));
        assert(!itr.hasNext());
        
        // Test that the iterator returns the correct value when one value is
        // added to the end of the deque and that afterwards there are no
        // more elements to iterate through
        deque = new Deque<String>();
        deque.addLast("Bob");
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Bob"));
        assert(!itr.hasNext());
        
        // Test that the iterator returns the correct value when two values are
        // added to the front of the deque and that afterwards there are no
        // more elements to iterate through
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addFirst("Steve");
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Steve"));
        assert(itr.hasNext());
        assert(itr.next().equals("Bob"));
        assert(!itr.hasNext());
        
        // Test that the iterator returns the correct value when two values are
        // added to the front of the deque and that afterwards there are no
        // more elements to iterate through
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addLast("Steve");
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Bob"));
        assert(itr.hasNext());
        assert(itr.next().equals("Steve"));
        assert(!itr.hasNext());
        
        
        // Test that the iterator returns the correct value when two values are
        // added to the front of the deque and then one value is removed from
        // the front. Also check that iteration completes normally.
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addFirst("Steve");
        deque.removeFirst();
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Bob"));
        assert(!itr.hasNext());
        
        // Test that the iterator returns the correct value when two values are
        // added to the end of the deque and then one value is removed from
        // the end. Also check that iteration completes normally.
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addLast("Steve");
        deque.removeLast();
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Bob"));
        assert(!itr.hasNext());
        
        // Test that the iterator returns the correct value when two values are
        // added to the front of the deque and then one value is removed from
        // the end. Also check that iteration completes normally.
        deque = new Deque<String>();
        deque.addFirst("Bob");
        deque.addFirst("Steve");
        deque.removeLast();
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Steve"));
        assert(!itr.hasNext());
        
        // Test that the iterator returns the correct value when two values are
        // added to the end of the deque and then one value is removed from
        // the front. Also check that iteration completes normally.
        deque = new Deque<String>();
        deque.addLast("Bob");
        deque.addLast("Steve");
        deque.removeFirst();
        itr = deque.iterator();
        assert(itr.hasNext());
        assert(itr.next().equals("Steve"));
        assert(!itr.hasNext());
        
        System.out.println("Completed testing!");
        
        
    }
    
}