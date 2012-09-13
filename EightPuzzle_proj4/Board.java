
public class Board {
    
    private int[][] blocks = null;
    
    public Board(int[][] blocks)  {         
// construct a board from an N-by-N array of blocks                                           
// (where blocks[i][j] = block in row i, column j)
        this.blocks = blocks;
    }
    
    public int dimension()                 // board dimension N
    {
        return blocks.length;
    }
    
    public int hamming()                   // number of blocks out of place
    {
        return 0;
    }
    
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        return 0;
    }
    
    public boolean isGoal()                // is this board the goal board?
    {
        return false;
    }
    
    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    {
        return null;
    }
    
    public boolean equals(Object y)        // does this board equal y?
    {
        if(this == y)
            return true;
        
        Board otherBoard = (Board)y;
        
        if(this.dimension() != otherBoard.dimension())
            return false;
        
        int dim = this.dimension();
        
        for (int i = 0; i < dim; i++)
        {
            for (int j = 0; j < dim; j++)
            {
                if(blocks[i][j] != otherBoard[i][j])
                    return false;
            }   
        }
             
        return true;
    }
    
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return null;
    }
    
    public String toString()               // string representation of the board (in the output format specified below)
    {
        StringBuilder boardString = new StringBuilder();
        int dim = this.dimension();
        
        for (int i = 0; i < dim; i++)
        {
            for (int j = 0; j < dim; j++)
            {
                
            }   
        }
        
        return null;
    }
}