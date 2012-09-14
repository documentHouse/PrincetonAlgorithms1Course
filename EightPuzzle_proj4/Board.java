import java.util.Iterator;

public class Board {
    
    public int[][] blocks = null;
    private final int EMPTY_SPACE_VALUE = 0;
    
    // construct a board from an N-by-N array of blocks                                           
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)  {
        
        // No null blocks
        assert(blocks != null);
        
        // No staggered arrays
        for(int i = 0; i < blocks.length; i++)
          assert(blocks.length == blocks[i].length);
                    
        boolean hasEmptySpace = false;
        
        int dim = blocks.length;
        
        for(int i = 0; i < dim; i++)
        {
          for(int j = 0; j < dim; j++)
          {
            if(blocks[i][j] == EMPTY_SPACE_VALUE)
            {
              hasEmptySpace = true;
              i = dim;
              break;
            }
          }
        }
        
        assert(hasEmptySpace);
        
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
                  /*
                  System.out.println("i = " + i + " -- j = " + j);
                  System.out.println("blockValue = " + blockValue);
                  System.out.println("blockValue%dim = " + blockValue%dim);
                  System.out.println("Math.abs((blockValue - 1)/dim - i) = " + (Math.abs((blockValue - 1)/dim - i)));
                  System.out.println("Math.abs((blockValue - 1)%dim - j) = " + (Math.abs((blockValue - 1)%dim - j)));
                  */
                  manhattanDistance += Math.abs((blockValue - 1)/dim - i);
                  manhattanDistance += Math.abs((blockValue - 1)%dim - j);
                }
            }   
        }
        
        return manhattanDistance;
    }
    
    public boolean isGoal()                // is this board the goal board?
    {
        int dim = this.dimension();
        
        if(dim == 1)
          return true;
        
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
        // Process the last row except the empty space value which is supposed to be at the end of the row
        while (j < dim-1)
        {
            if (blocks[i][j] != (i*dim + j + 1))
                return false;
            j++;
        }
            
        
        // The last value representing a space must be in the final location
        // if we have gotten this far
        return true;
    }
    
    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    {
        
        int dim = this.dimension();
        if(dim == 1)
        {
            int[][] oneBlock = new int[1][1];
            oneBlock[1][1] = 0;
            return new Board(oneBlock);
        }
        
        int i, j, k;
        
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
    
    private void swap(int i1, int j1, int i2, int j2)
    {
        int tempBlockValue = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = tempBlockValue;
    }
    
    private boolean areValidIndices(int i, int j)
    {
      int dim = this.dimension();
      return ( ((-1 < i) && (i < dim)) && ((-1 < j) && (j < dim)));
      
    }
    
    private Board copy()
    {
     
      int dim = this.dimension();
      int[][] newBlocks = new int[dim][dim];
      
      for(int i = 0; i < dim; i++)
      {
        for(int j = 0; j < dim; j++)
        {
          newBlocks[i][j] = blocks[i][j];
        }
      }
      
      Board newBoard = new Board(newBlocks);
      
      return newBoard;
    }
    
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Queue<Board> neighborQueue = new Queue<Board>();
        
        if(this.dimension() == 1)
          return neighborQueue;
        
        // Find the empty space
        int EmptyX = 0;
        int EmptyY = 0;
        int dim = blocks.length;
        for (int i = 0; i < dim; i++)
        {
          for(int j = 0; j < dim; j++)
          {
            if(this.blocks[i][j] == EMPTY_SPACE_VALUE)
            {
              EmptyX = i;
              EmptyY = j;
              // End the search for the empty space in the puzzle
              i = dim;
              break;
            }
          }
        }
        
        // Holds all possible new locations for the space
        Queue<Integer> availableIndices = new Queue<Integer>();
        
        // Top
        availableIndices.enqueue(EmptyX);
        availableIndices.enqueue(EmptyY - 1);
        
        // Right
        availableIndices.enqueue(EmptyX + 1);
        availableIndices.enqueue(EmptyY);
        
        // Bottom
        availableIndices.enqueue(EmptyX);
        availableIndices.enqueue(EmptyY + 1);
        
        // Left
        availableIndices.enqueue(EmptyX - 1);
        availableIndices.enqueue(EmptyY);
        
        
        int indexX;
        int indexY;
        // Process all 4 pairs of indices to make sure they are all valid
        for(int i = 0; i < 4; i++)
        {
          indexX = availableIndices.dequeue();
          indexY = availableIndices.dequeue();
          if(areValidIndices(indexX, indexY))
          {
            availableIndices.enqueue(indexX);
            availableIndices.enqueue(indexY);
          }
        }
        
        int numPairs = availableIndices.size()/2;
        for(int i = 0; i < numPairs; i++)
        {
          indexX = availableIndices.dequeue();
          indexY = availableIndices.dequeue();
          
          Board neighborBoard = this.copy();
          neighborBoard.swap(EmptyX, EmptyY, indexX, indexY);
          
          neighborQueue.enqueue(neighborBoard);
        }
        
        return neighborQueue;
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
    
    
    public static void main(String[] args)
    {
      
      // Testing a 2x2 board, the 3-puzzle
      int[][] blocks = new int[2][2];
      blocks[0][0] = 1;
      blocks[0][1] = 2;
      blocks[1][0] = 3;
      blocks[1][1] = 0;
       
      Board board = new Board(blocks);
    
      // Test that we have the goal board initially
      assert(board.isGoal());
      
      // Test that we do not have the goal board after a swap
      board.swap(0,0,0,1);
      assert(!board.isGoal());
      
      // Test that we have the goal board after the initial swap
      board.swap(0,0,0,1);
      assert(board.isGoal());
      
      // Test that we do not have the goal board after a swap
      board.swap(0,0,1,0);
      assert(!board.isGoal());
      
      // Test that we have the goal board after the initial swap
      board.swap(0,0,1,0);
      assert(board.isGoal());
      
      // Test that we do not have the goal board after a swap
      board.swap(0,0,1,1);
      assert(!board.isGoal());
      
      // Test that we have the goal board after the initial swap
      board.swap(0,0,1,1);
      assert(board.isGoal());
      
      // Test if the board has the proper dimension
      assert(board.dimension() == 2);
      
      // Test the hamming distance is 0 for the goal board
      assert(board.hamming() == 0);
      
      // Test the manhattan distance is 0 for the goal board
      assert(board.manhattan() == 0);
      
      
      
      // Testing a 3x3 board, the 8-puzzle
      blocks = new int[3][3];
      blocks[0][0] = 1;
      blocks[0][1] = 2;
      blocks[0][2] = 3;
      blocks[1][0] = 4;
      blocks[1][1] = 5;
      blocks[1][2] = 6;
      blocks[2][0] = 7;
      blocks[2][1] = 8;
      blocks[2][2] = 0;
      
      board = new Board(blocks);
     
      
      // Test that we have the goal board initially
      assert(board.isGoal());
      
      // Test that we do not have the goal board after a swap
      board.swap(0,0,0,1);
      assert(!board.isGoal());
      
      // Test that we have the goal board after the initial swap
      board.swap(0,0,0,1);
      assert(board.isGoal());
      
      // Test that we do not have the goal board after a swap
      board.swap(0,0,1,0);
      assert(!board.isGoal());
      
      // Test that we have the goal board after the initial swap
      board.swap(0,0,1,0);
      assert(board.isGoal());
      
      // Test that we do not have the goal board after a swap
      board.swap(0,0,1,1);
      assert(!board.isGoal());
      
      // Test that we have the goal board after the initial swap
      board.swap(0,0,1,1);
      assert(board.isGoal());
      
      // Test if the board has the proper dimension
      assert(board.dimension() == 3);
      
      // Test the hamming distance is 0 for the goal board
      assert(board.hamming() == 0);
      
      // Test the manhattan distance is 0 for the goal board
      assert(board.manhattan() == 0);
      
      // Test the hamming distance is 2 for a swap of the first two members
      board.swap(0,0,0,1);
      assert(board.hamming() == 2);
      board.swap(0,0,0,1);
      
      // Test the manhattan distance is 2 for a swap of the first two members
      board.swap(0,0,0,1);
      assert(board.manhattan() == 2);
      board.swap(0,0,0,1);
      
      // Test the hamming distance is 2 for a swap of the first two members
      board.swap(0,1,2,1);
      assert(board.hamming() == 2);
      board.swap(0,1,2,1);
      
      // Test the manhattan distance is 4 for a swap of the first two members
      board.swap(0,1,2,1);
      assert(board.manhattan() == 4);
      board.swap(0,1,2,1);
      
      // Test the hamming distance is 4 for a swap of the first two members
      board.swap(0,0,2,1);
      board.swap(2,0,0,2);
      assert(board.hamming() == 4);
      board.swap(0,0,2,1);
      board.swap(2,0,0,2);
      
      // Test the manhattan distance is 14 for a swap of the first two members
      board.swap(0,0,2,1);
      board.swap(2,0,0,2);
      assert(board.manhattan() == 14);
      board.swap(0,0,2,1);
      board.swap(2,0,0,2);
      
      System.out.println("Testing complete!");
      
      /*
      Iterator<Board> neighbors = board.neighbors().iterator();
      
      System.out.println("Printing the neighbors of the following board");
      System.out.println("Board: " + board);
      while(neighbors.hasNext())
        System.out.println(neighbors.next());
      
      System.out.println("Original Board: " + board);
      */
      
    }
    
}