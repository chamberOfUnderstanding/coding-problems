package sorting_searching;

public class Shell_Sort {

	/* h sort the array
	 * h=1 => Insertion sort
	 * Value of h is a value in the sequence 3x+1 such that it is less than length of array/3
	 * Decrement by h
	 */
	public static void shellSort(int[] a){
		int h = 1;
		while(h<a.length/3)
			h=3*h+1;
		while(h>=1){
			for(int i=h;i<a.length;i++){			
				for(int j=i;j>=h;j-=h)				
					if(a[j-1]>a[j])
						swap(a, j, j-h);
			}
			h/=3;
		}
	}
	private static void swap(int[] a, int j, int i) {
		int temp = a[i];
		a[i]=a[j];
		a[j]=temp;
	}	

	public static void main(String...x){
		int[] a = {3,2,1,49,-2,42,4,40};
		System.out.println(a);
		shellSort(a);
		System.out.println(a);
	}
}
