package string;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Anagrams {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCases = Integer.parseInt(scanner.nextLine());
		String inputString = null;
		while(testCases-->0){
			inputString = scanner.nextLine();
			System.out.println(changesNeeded(inputString));
		}
		scanner.close();
	}

	public static int changesNeeded(String inputString){
		int length = inputString.length();
		Map<Character,Integer> map1 = new TreeMap<Character,Integer>();
		Map<Character,Integer> map2 = new TreeMap<Character,Integer>();
		int endIndex = length/2; // for substring, endIndex is exclusive and beginIndex is inclusive
		if(length%2!=0)
			return -1;
		fillMap(map1,inputString.substring(0,endIndex));
		fillMap(map2,inputString.substring(endIndex,length));
		return compareMaps(map1,map2);
	}

	private static int compareMaps(Map<Character, Integer> map1, Map<Character, Integer> map2) {
		int changes = 0, extra = 0;
		Set<Character> keySet = map1.keySet();
		Character[] keySetArray = new Character[keySet.size()]; 
		keySet.toArray(keySetArray);
		for(Character c:keySetArray){
			if(map2.containsKey(c)){								
				if(extra>0)
					extra-=Math.abs(map1.get(c)-map2.get(c));
				else{
					changes += Math.abs(map1.get(c)-map2.get(c));
					extra=Math.abs(map1.get(c)-map2.get(c));
					}
			}
			else{
				changes += map1.get(c);
				extra += map1.get(c);
			}
		}
		return changes;
	}

	public static void fillMap(Map<Character,Integer> map, String string){
		for(int i=0;i<string.length();i++){
			char character = string.charAt(i);
			if(map.containsKey(character))
				map.put(character,map.get(character)+1);
			else
				map.put(character,1);
		}
	}
}