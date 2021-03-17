package sorting_searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Given a ranked array (descending order) and a players array (ascending order). Find the rank of each player.
 * 
 * https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
 *
 */
public class Dense_Ranking {

	public static void main(String[] args) {
		Integer[] ranked = {100, 100, 50, 40, 40, 20, 10};
		Integer[] players = {5, 25, 50, 120};
		climbingLeaderboard(Arrays.asList(ranked), Arrays.asList(players));
	}

	/**
	 * T O(r) + O(r * log(p))
	 * S O(r)
	 */
	public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> players) {
		// Remove duplicates
		List<Integer> ranked_Without_Duplicates = ranked.stream().distinct().collect(Collectors.toList());
		List<Integer> results = new ArrayList<>();
		// Find rank of each player
		for(Integer player: players)
			results.add(findRank(player, ranked_Without_Duplicates));
		return results;
	}
	
	public static Integer findRank(Integer score, List<Integer> ranked) {
		// If score is > first value, then it has the highest rank
		if (score > ranked.get(0))
			return 1;
		// If score is < last value, then it has the lowest rank
		if (score < ranked.get(ranked.size() - 1))
			return ranked.size() + 1;
		// Perform binary search to get the exact point where this score can appear 
		Integer low = 0;
		Integer high = ranked.size() - 1;
		while(low <= high) {
			Integer mid = (low + high) >> 1;
		if (ranked.get(mid) < score)
			high = mid - 1;
		else if (ranked.get(mid) > score)
			low = mid + 1;
		else
			return mid + 1;            
		}
		// If the score is not already there, then the rank is high (possible location where it can be found) +/- 2
		// +1 as index is 0 based, +1 as it needs the next rank, so +/-2
		return score < ranked.get(high) ? high + 2 : high - 2;
	}

	/**
	 * T O(p)
	 * S O(1)
	 * 
	 * This is not a complete solution as it can fail for extremely large inputs. But this is a possible approach.
	 */
	public static void findRank_Partial(Integer score, List<Integer> ranked2, List<Integer> results) {
		int i = ranked2.size() - 1;
		while (i > 0 && score > ranked2.get(i))
			i--;
		if (i == 0)
			results.add(1);
		else if (score == ranked2.get(i))
			results.add(i + 1);            
		else    
			results.add(i + 2);
	}
}
