package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
 * 
 * ALGORITHM
 * =========
 * Maintain two extra arrays, one a copy of the input word array, another an index array with values 0,1,2...n
 * Sort each word in the word array
 * Sort the entire word array. As this sort is performed, any swaps/adjustments made must be made in the index array as well.
 * Now the index array holds the result. i.e. How the words in the word array need to be arranged.
 * For each value in the index array, scan the word at this index and print it
 * 
 * String.compareTo()
 *    a.compareTo(b) gives 0 if a is lexicographically equal to b, gives -1 if a is lexicographically < b and +1 if, well.. you know it..
 */
public class Anagram_Get_Together {
	
	public static void main(String...strings){
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add("cat");
		listOfWords.add("dog");
		listOfWords.add("tac");
		listOfWords.add("god");
		listOfWords.add("act");
		Anagram_Get_Together anagramGetTogether = new Anagram_Get_Together();
		anagramGetTogether.doIt(listOfWords);
	}
	
	// If there are n words, each with m characters, then sorting a word costs mlogm and there are n such words, hence nmlogm
	// Now sorting the entire words array will take nlogn where each comparison can go upto O(m) => mnlogn
	// O(mnlogmn) , O(n) + O(n)
	private void doIt(List<String> listOfWords){
		List<String> listOfSortedWords = new LinkedList<>();		
		for(String word : listOfWords)
			listOfSortedWords.add(sort(word));
		int[] indices = new int[listOfSortedWords.size()];
		for(int i = 0; i < indices.length; i++)
			indices[i] = i;			
		quicksort(listOfSortedWords, indices, 0, listOfSortedWords.size()-1);
		for(int i = 0; i < listOfWords.size(); i++)
			System.out.print(listOfWords.get(indices[i]) + " ");		
	}

	private void quicksort(List<String> listOfSortedWords, int[] indices, int low, int high) {
		if(low < high){
			int mid = partition(listOfSortedWords, indices, low, high);
			quicksort(listOfSortedWords, indices, low, mid);
			quicksort(listOfSortedWords, indices, mid+1, high);
		}		
	}

	private int partition(List<String> listOfSortedWords, int[] indices, int leftIndex, int rightIndex) {
		String pivot = listOfSortedWords.get(leftIndex);
		leftIndex--;
		rightIndex++;
		while(true){
			while(pivot.compareTo(listOfSortedWords.get(++leftIndex)) > 0);
			while(pivot.compareTo(listOfSortedWords.get(--rightIndex)) < 0);
			if(leftIndex >= rightIndex)
				return rightIndex;
			swap(listOfSortedWords, indices, leftIndex, rightIndex);
		}
	}

	private String sort(String word) {
		char[] wordArray = word.toCharArray();
		Arrays.sort(wordArray);
		return new String(wordArray);
	}

	private void swap(List<String> words, int[] indices, Integer index1, int index2) {
		String tempString = words.get(index1);
		words.set(index1, words.get(index2));
		words.set(index2, tempString);
		int tempInt = indices[index1];
		indices[index1] = indices[index2];
		indices[index2] = tempInt;
	}
}