package sorting_searching;

import java.util.Random;

public class Knuth_Shuffle {

	private static void knuthShuffle(int[] array) {
		for(int i = 0; i < array.length; i++)
			swap(array, i, new Random().nextInt(i+1));	
	}

	private static void swap(int[] a, int i, int min) {
		int temp = a[i];
		a[i] = a[min];
		a[min] = temp;
	}

	public static void main(String...x){
		int[] array = {-2, 1, 2, 3, 4, 40, 42, 49};
		knuthShuffle(array);
		System.out.println("Shuffle 1");
		knuthShuffle(array);
		System.out.println("Shuffle 2");
		knuthShuffle(array);
		System.out.println("Shuffle 3");
	}

}
