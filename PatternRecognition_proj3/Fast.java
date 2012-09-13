import java.util.Arrays;

public class Fast {
 
    private static Point[] points;
    
    private static void loadPoints(String filename)
    {
        In in = new In(filename);
        
        int numPoints = in.readInt();
        
        if (numPoints != 0)
            points = new Point[numPoints];
        else
            return;
        
        
        int[] pointValues = null;
        int pointValueX;
        int pointValueY;
        for (int i = 0; i < numPoints; i++)
        {
            
            pointValueX = in.readInt();
            pointValueY = in.readInt();
            points[i] = new Point(pointValueX, pointValueY);
            
        }
    }
    
    private static boolean notAlreadyFound(int currentIndex, double slopeValue)
    {
        
        for (int i = 0; i < currentIndex; i++)
        {
            if (points[i].slopeTo(points[currentIndex]) == slopeValue)
                return false;
        }
                
        return true;
    }
    
    private static void calculateSegments() 
    {
        int numPoints = points.length;
        
        if (numPoints < 4)
            return;
        
        /*
        System.out.println("The points...");
        for(Point point : points)
            System.out.print(point + " ");
        System.out.println();
        */
        //final int MIN_COLLINEAR_POINTS = 4;
        
        int i, j, k;
        int slopeCount = 0;
        double slopeValue;
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        points[0].draw();
        
        for (i = 0; i < numPoints; i++)
        {
        
            if (i > numPoints - 4)
                return;
                
            Arrays.sort(points, i, numPoints, points[i].SLOPE_ORDER);
            
            /*
            System.out.println("sorted points...");
            for(int q = i; q < numPoints; q++)
                System.out.print(points[q] + " ");
            System.out.println();
            */
            
            slopeValue = points[i].slopeTo(points[i+1]);
            slopeCount = 0;
            for (j = i + 1; j < numPoints; j++)
            {
                points[j].draw();
                if (points[i].slopeTo(points[j]) == slopeValue)
                    slopeCount++;
                else
                {
                    
                    if (slopeCount >= 3)
                    {
                        //System.out.println("found...");
                        if (notAlreadyFound(i, slopeValue))
                        {
                            System.out.print(points[i]);
                            for (k = j - slopeCount; k < j; k++)
                            {
                                System.out.print(" -> " + points[k]);
                                points[i].drawTo(points[k]);
                            }
                            System.out.println();
                        }
                        slopeCount = 1;
                    }
                    
                    slopeValue = points[i].slopeTo(points[j]);
                }
                
                if ((j == numPoints - 1) && (slopeCount >= 3))
                {
                    //System.out.println("found...");
                    if (notAlreadyFound(i, slopeValue))
                    {
                        System.out.print(points[i]);
                        for (k = j - slopeCount + 1; k < numPoints; k++)
                        {
                            System.out.print(" -> " + points[k]);
                            points[i].drawTo(points[k]);
                        }
                        System.out.println();
                    }
                }       
            }    
        }    
    }
    
    public static void main(String[] args) {
     
        if (args.length == 1)
        {
     
            loadPoints(args[0]);
            calculateSegments();
           
        }
        else
        {
            System.out.println("Usage: Fast [filename]");
        }
    }
    
}