/*************************************************************************
 * Name: Andrew Rickert
 * Email: ironapple@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    
    private class BySlope implements Comparator<Point> {
        
        public int compare(Point p1, Point p2) {
            
            // This comparison needs to be completed
            if (Point.this.slopeTo(p1) < Point.this.slopeTo(p2))
                return -1;
            else if (Point.this.slopeTo(p2) < Point.this.slopeTo(p1))
                return 1;
            else 
                return 0;
        }
        
    }
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that.y == this.y) {
            if (that.x == this.x) {
                return Double.NEGATIVE_INFINITY;       
            }
            else {
                return 0.0;
            }
        }
        else {
            if (that.x == this.x)
                return Double.POSITIVE_INFINITY;
            else {
                return (that.y - this.y)/(that.x - this.x);
            }       
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if(that.y == this.y)
            return Double.compare(that.x, this.x);
        else
            return Double.compare(that.y, this.y);
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}