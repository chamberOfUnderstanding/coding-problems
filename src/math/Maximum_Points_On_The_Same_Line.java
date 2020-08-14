package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 47un
 *
 * Given a set of points, count all the points that lie on the same line
 *
 * http://www.geeksforgeeks.org/count-maximum-points-on-same-line/
 * https://discuss.leetcode.com/topic/6028/sharing-my-simple-solution-with-explanation
 * 
 * =================
 * METHOD 1 : Slopes
 * =================
 * Every point has to be paired with every other point and the slope has to be calculated. So the performance is O(n^2)
 * So run two loops
 *  Say the current point is p1
 *  Track the lines that start with p1 and have same slopes using a map. This maps the slope to number of lines with that slope.
 *  Track the points which are same as this point using another variable, say samePoints
 *  Track the maximum number of lines of same slope made by pairing p1 with every other point (p2, p3, p4...) using localMaximum
 *    Note that localMaximum is not the result. The result is the maximum of all localMaximums
 *  Run another loop to scan the remaining points (p2, p3, p4...)
 *   Let this point be p2
 *   If p2 = p1, update samePoints, continue to next point
 *   If x coordinates of p2 and p1 are the same, then the slope is + infinity
 *   Else calculate slope as (p2.y - p1.y) / (p2.x - p2.x)
 *   If the slope map contains this slope, increase the lines with same slope
 *   Else add this slope with a mapping to 1 (Only 1 line with this slope)
 *   Update localMaximum
 *  Now localMaximum will have the maximum points that can lie on some line that starts with p1
 *  The points that are same as p1, samePoints, will also contribute to these lines, just like p1, so update localMaximum with samPoints
 *  Result is the largest among the result seen so far and the updated localMaximum for this point
 *    
 * TIME    : O(n ^ 2)
 * SPACE   : O(n)
 * 
 */
 
public class Maximum_Points_On_The_Same_Line {

	public static void main(String[] args) {
		List<Point> points = new ArrayList<>();
		points.add(new Point(-1, 1));
		points.add(new Point(3, 4));
		points.add(new Point(0, 0));
		points.add(new Point(1, 1));
		points.add(new Point(3, 3));
		points.add(new Point(4, 4));
		points.add(new Point(4, 4));
		points.add(new Point(4, 4));
		points.add(new Point(4, 4));
		System.out.println(countPointsOnTheSameLine(points));
	}

	private static int countPointsOnTheSameLine(List<Point> points) {
		int maximum = 0;
		for(int i = 1; i < points.size(); i++) {
			Map<Double, Integer> slopes = new HashMap<>();
			int localMaximum = 0;
			int samePoints   = 1;
			for(int j = i + 1; j < points.size(); j++) {
				Point point1 = points.get(i);
				Point point2 = points.get(j);
				double slope = 0;
				if(point1.x == point2.x && point1.y == point2.y) {
					samePoints++;
					continue;
				}
				else if(point1.x == point2.x)
					slope = Double.MAX_VALUE;
				else
					slope = (point2.y - point1.y) / (point2.x - point1.x);
				slopes.put(slope, slopes.containsKey(slope)? slopes.get(slope) + 1 : 1);
				localMaximum = Math.max(localMaximum, slopes.get(slope));
			}
			maximum = Math.max(maximum, localMaximum + samePoints);
		}
		return maximum;
	}

	private static class Point{
		int x;
		int y;

		Point(int x, int y){
			this.x = x;	
			this.y = y;	
		}
	}
}
