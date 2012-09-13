

public class Brute {
    
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
    
    private static void calculateSegments()
    {
        
        int numPoints = points.length;
        
        if (numPoints < 4)
            return;
        
        double slopeValue;
        int i, j, k, l;
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (i = 0; i < numPoints - 3; i++)
        {
            points[i].draw();
            for (j = i + 1; j < numPoints - 2; j++)
            {
                points[j].draw();
                slopeValue = points[i].slopeTo(points[j]);
                for (k = j + 1; k < numPoints - 1; k++)
                {
                    points[k].draw();
                    if (points[i].slopeTo(points[k]) != slopeValue)
                        continue;
                    for (l = k + 1; l < numPoints; l++)
                    {
                        points[l].draw();
                        if (points[i].slopeTo(points[l]) == slopeValue)
                        {
                            System.out.println(points[i] + " -> "  + points[j] 
                                                   + " -> " + points[k] 
                                                   + " -> " + points[l]);
                            points[i].drawTo(points[j]);
                            points[j].drawTo(points[k]);
                            points[k].drawTo(points[l]);
                        }
                    }
                }
            }      
        }
    }
    
    public static void main(String[] args) 
    {
        
        
        if (args.length == 1)
        {

            loadPoints(args[0]);
            calculateSegments();
 
        }
        else
        {
            System.out.println("Usage: Brute [filename]");
        }
        
    }
    
}