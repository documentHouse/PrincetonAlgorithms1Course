
public class Board {
    
    private int[][] blocks = null;
    private final int EMPTY_SPACE_VALUE = 0;
    
    // construct a board from an N-by-N array of blocks                                           
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)  {
        /*    
        if (blocks.length == 0)
            this.blocks = null;
        else
        {
            for (int i = 0; i < blocks.length; i++)
            {
                if (blocks.length != blocks[i].length)
                {
                    this.blocks = null;
                }
            }
        }
        */
            
        this.blocks = blocks;
    }
    
    public int dimension()                 // board dimension N
    {
        return blocks.length;
    }
    
    public int hamming()                   // number of blocks out of place
    {
        int hammingDistance = 0;
        
        int dim = this.dimension();
        
        int i, j, blockValue;
        for (i = 0; i < dim; i++)
        {
            for (j = 0; j < dim; j++)
            {
                blockValue = blocks[i][j];
                if (blockValue != EMPTY_SPACE_VALUE)
                {
                    if (blockValue != (i*dim + j + 1))
                    {
                        hammingDistance++;
                    }
                }
            }   
        }
        
        return hammingDistance;
    }
    
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int manhattanDistance = 0;
        
        int dim = this.dimension();
        
        int i, j, blockValue;
        for (i = 0; i < dim; i++)
        {
            for (j = 0; j < dim; j++)
            {
                blockValue = blocks[i][j];
                if (blockValue != EMPTY_SPACE_VALUE)
                {
                    manhattanDistance += Math.abs(blockValue/dim - i);
                    manhattanDistance += Math.abs(blockValue%dim - j - 1);
                }
            }   
        }
        
        return manhattanDistance;
    }
    
    public boolean isGoal()                // is this board the goal board?
    {
        int dim = this.dimension();
        
        int i, j;
        // Process all but the last row
        // to avoid using an extra if statement to check for 
        // the very last position
        for (i = 0; i < dim-1; i++)
        {
            for (j = 0; j < dim; j++)
            {
                if (blocks[i][j] != (i*dim + j + 1))
                    return false;
            }
        }
        
        j = 0;
        // Process the last row
        while (j < dim-1)
        {
            if (blocks[i][j] != (i*dim + j + 1))
                return false;
        }
            
        
        // The last value representing a space must be in the final location
        // if we have gotten this far
        return true;
    }
    
    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    {
        
        int dim = this.dimension();
        if(dim == 1)
            return new Board(new int[1][1]);
        
        int i, j, k;
        boolean spaceFound = false;
        
        // Check the first two rows for the space value
        // if the first row has it then use the second row
        // if the first row doesn't have it then use the first row
        k = 0;
        for(j = 0; j < dim; j++)
        {
            if(blocks[k][j] == EMPTY_SPACE_VALUE)
            {
                k++;
            }
        }

        int[][] twinBlocks = new int[dim][dim];
        
        for(i = 0; i < dim; i++)
        {
            for(j = 0; j < dim; j++)
            {
                twinBlocks[i][j] = blocks[i][j];   
            }
        }
        
        // Swap values
        int tempBlockValue = twinBlocks[k][0];
        twinBlocks[k][0] = twinBlocks[k][1];
        twinBlocks[k][1] = tempBlockValue;

        Board twinBoard = new Board(twinBlocks);
        
        return twinBoard;
    }
    
    public boolean equals(Object y)        // does this board equal y?
    {
        if(this == y)
            return true;
        
        if(y == null)
            return false;
        
        if(this.getClass() != y.getClass())
            return false;
        
        Board otherBoard = (Board)y;
        
        if(this.dimension() != otherBoard.dimension())
            return false;
        
        int dim = this.dimension();
        
        for (int i = 0; i < dim; i++)
        {
            for (int j = 0; j < dim; j++)
            {
                if(this.blocks[i][j] != otherBoard.blocks[i][j])
                    return false;
            }   
        }
             
        return true;
    }
    
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Queue<Board> q = new Queue<Board>();
        return null;
    }
    
    public String toString()               // string representation of the board (in the output format specified below)
    {
        
        StringBuilder s = new StringBuilder();
        int dim = this.dimension();
        s.append(dim + "\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
    
        return s.toString();
    
    }
}