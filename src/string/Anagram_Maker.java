package string;

import java.util.Arrays;

public class Anagram_Maker {
	public static int numberNeeded(char[] first, char[] second) {
		Arrays.sort(first);
		Arrays.sort(second);
		int length1 = first.length;
		int length2 = second.length;
		int deleted = 0;
		int i = 0, j = 0;
		while(true){
			if(first[i] == second[j]){
				i++;
				j++;
			}
			else{
				if(first[i] < second[j])
					i++;
				else
					j++;
				deleted++;
			}
			if(i == length1 || j == length2)
				break;
		}
		return deleted + (length1-i) + (length2-j);
	}

	public static int numberNeeded(String first, String second) {
		int[] count = new int[25];
		int i=0, length1 = first.length(), length2 = second.length();
		while(i != length1)
			count[first.charAt(i++) - 97]++;
		i=0;
		while(i != length2)
			count[second.charAt(i++) - 97]--;
		int deletions = 0;
		for(int j : count)
			deletions+=Math.abs(j);
		return deletions;
	}

	public static void main(String[] args) {
		String a = "alphabetticalz";
		String b = "zelphatmos";
		System.out.println(numberNeeded(a, b));
	}
}
