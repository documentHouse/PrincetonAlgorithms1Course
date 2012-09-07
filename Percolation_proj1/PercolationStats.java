/**
 * Name      : Andrew Rickert<br>
 * Login     : ironapple@gmail.com<br>
 * Date      : 08/14/12<br>
 * Purpose   : Calculate the statistics for the percolation simulation<br>
 * Execution : java PercolationStats [N] [T], where N is board size length
 *             and T is the number of simulations<br>
 */

public class PercolationStats {
    
    private double[] experimentalFractions;
    
    /**
     * The Constructor for the PercolationStats object
     * @param N The length of one side of the percolation board.
     * @param T The number of times the simulation is run.
     */
    public PercolationStats(int N, int T) 
    {   // perform T independent computational experiments on an N-by-N grid
        if ((N <= 0) || (T <= 0))
            throw new IllegalArgumentException("Both N and T must be >= 0");
        
        experimentalFractions = new double[T];
        
        Percolation percolation = null;
        int row;
        int col;
        int sites;
        for (int i = 0; i < T; i++)
        {
            
            percolation = new Percolation(N);
            sites = 0;
            
            while (!percolation.percolates())
            {    
                do
                {
                    row = StdRandom.uniform(1, N+1);
                    col = StdRandom.uniform(1, N+1);
                } while(percolation.isOpen(row, col));
                
                percolation.open(row, col);
                sites++;
            }
         
            experimentalFractions[i] = ((double) sites/((double) N*N));
        }
        
    }
    
     // sample mean of percolation threshold
    public double mean() {                   

        return StdStats.mean(experimentalFractions);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {                  
        
        return StdStats.stddev(experimentalFractions);
    }
    
    public static void main(String[] args)
    {      
                
        if (args.length == 2)
        {
            
            int nValue = Integer.parseInt(args[0]);
            int tValue = Integer.parseInt(args[1]);
            PercolationStats percolationStats = new PercolationStats(nValue, tValue);
            
            double confidenceLeft = percolationStats.mean()
                - (1.96*percolationStats.stddev())/Math.sqrt(tValue);
            double confidenceRight = percolationStats.mean()
                + (1.96*percolationStats.stddev())/Math.sqrt(tValue);
            System.out.println("mean                    = " 
                                   + percolationStats.mean());
            System.out.println("stddev                  = "
                                   + percolationStats.stddev());
            System.out.println("95% confidence inteval  = " + confidenceLeft
                                   + ", " + confidenceRight);
            
        }
        else
        {
            System.out.println("Usage: PercolationStats [N] [T]");
        }
        
    }
}