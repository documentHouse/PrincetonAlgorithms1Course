public class Subset {
    
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.out.println("Usage: Subset [number]");
            return;
        }
        
        RandomizedQueue<String> inQueue = new RandomizedQueue<String>();
        
        String inString = null;
        
        while (!StdIn.isEmpty())
        {
            inString = StdIn.readString();
            inQueue.enqueue(inString);
        }
        
        
        int numRandom = Integer.parseInt(args[0]);
        for (int i = 0; i < numRandom; i++)
            System.out.println(inQueue.dequeue());
        
        
    }
    
}