package array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 47un
 *
 * Given a set of time intervals in any order, merge all overlapping intervals.
 * Let the intervals be represented as pairs of integers for simplicity. 
 * e.g. Intervals : {{1,3}, {2,4}, {5,7}, {6,8} }.
 *      The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}.
 *      Similarly {5, 7} and {6, 8} should be merged and become {5, 8}
 * 
 * http://www.geeksforgeeks.org/merging-intervals/
 * 
 * =========
 * METHOD 1
 * ===========
 * Sort the input list of intervals based on start time
 * Scan the list as long as the list size is'nt 1
 *  If from of current item is the same as from of next item, remove current item
 * Since the list is sorted, the items to the right have wider intervals
 * If to of current is greater than from of next, remove both items, and add a new item = from of current, to of larger tos
 * NOTE : Removing i will bring i + 1 to i 
 * 
 * TIME     : O(nlogn)
 * SPACE    : O(1)      // Intervals are to be read directly into a list. Array is used here for simplicity of input.
 *
 * Comparator
 * ==========
 * When comparing x and y, 
 *   RHS > LHS ? -1 
 *   RHS < LHS ?  1 
 *   RHS = LHS ?  0 
 * 
 */

public class Merge_Overlapping_Intervals {

    public static void main(String...strings) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(6, 8));
        intervals.add(new Interval(1, 9));
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(4, 7));
        mergeIntervals(intervals);
        System.out.println(intervals);
    }

    private static void mergeIntervals(List<Interval> intervals) {
        intervals.sort(new IntervalComparator());
        for(int i = 0; i < intervals.size() - 1 && intervals.size() != 1; ){
            Interval x = intervals.get(i);
            Interval y = intervals.get(i + 1);
            if(x.start == y.start)	
                intervals.remove(i);
            else if(x.end > y.start){
            	// remove both
                intervals.remove(i);
                // now i + 1 is at i
                intervals.remove(i);
                intervals.add(i, new Interval(x.start, Math.max(x.end, y.end)));
            }
            else
                i++;
        }
    }

    private static class IntervalComparator implements Comparator<Interval>{		
    	public int compare(Interval x, Interval y){
    		return y.start == x.start && 
    				y.end == x.end ? 
    						0
    						: y.start > x.start || y.start == x.start && y.end > x.end ? 
    									-1 
    									: 1;
    	}
    }

    static class Interval{
        int start;
        int end;

        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "(" + this.start + ", " + this.end + ")";
        }
    }
}
