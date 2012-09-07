/**
 * Name      : Andrew Rickert<br>
 * Login     : ironapple@gmail.com<br>
 * Date      : 08/15/12<br>
 * Purpose   : Implements the basic operations for percolation simulation<br>
 * Execution : An object is created with 'new Percolation(int N) where N is
 *             the size of the side length grid
 */

public class Percolation {
 
    private enum SiteValue {
        BLOCKED, 
           OPEN
    }
    
    //private QuickFindUF sitesUnion = null;
    private WeightedQuickUnionUF sitesUnion = null;
    private SiteValue[] sites; // Stores the values for the sites in the grid
    private int N;

    private int SOURCE_INDEX;
    private int SINK_INDEX;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {     
        
        this.N = N;
        
        SOURCE_INDEX = N;
        SINK_INDEX = N*(N+1);
        
        sites = new SiteValue[(N+1)*(N+1)]; 
        
        for (int i = 0; i < sites.length; i++)
            sites[i] = SiteValue.BLOCKED;
        
        //sitesUnion = new QuickFindUF((N+1)*(N+1));
        sitesUnion = new WeightedQuickUnionUF((N+1)*(N+1));
    }
    
    private int index(int i, int j)
    {
        return j*(N+1) + i;
    }
    
    private void checkBounds(int i, int j)
    {
        
        if (i <= 0 || i > N)
            throw new IndexOutOfBoundsException("Row index i out of bounds.");
          
        if (j <= 0 || j > N)
            throw new IndexOutOfBoundsException("Column index j out of bounds.");
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j)
    { 

        checkBounds(i, j);
        
        // If this site is open then we already performed the procedure
        // below. Saves some work
        if (sites[index(i, j)] == SiteValue.OPEN)
            return;
        
        sites[index(i, j)] = SiteValue.OPEN;
        
        if (i == 1)
            sitesUnion.union(index(i, j), SOURCE_INDEX);
        if (i == N)
            sitesUnion.union(index(i, j), SINK_INDEX);
        
        
        // Top
        if (i-1 >= 1)  
            if (isOpen(i-1, j))
                sitesUnion.union(index(i-1, j), index(i, j));
        
        // Left
        if (j-1 >= 1)
            if (isOpen(i, j-1))
                sitesUnion.union(index(i, j-1), index(i, j));
                
        // Right
        if (j+1 <= N)
            if (isOpen(i, j+1))
                sitesUnion.union(index(i, j+1), index(i, j));
        
        // Bottom
        if (i+1 <= N)
            if (isOpen(i+1, j))
                sitesUnion.union(index(i+1, j), index(i, j));
        
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j)
    {    
        checkBounds(i, j);
        return sites[index(i, j)] == SiteValue.OPEN;
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j)
    {    
        checkBounds(i, j);
        return sitesUnion.connected(SOURCE_INDEX, index(i, j));
    }
    
    // does the system percolate?
    public boolean percolates()
    {            
        return sitesUnion.connected(SOURCE_INDEX, SINK_INDEX);
    }
}