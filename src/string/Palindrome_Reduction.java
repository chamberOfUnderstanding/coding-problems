package string;

import java.util.Scanner;

public class Palindrome_Reduction {
	public static void main(String...strings){
		Scanner scanner = new Scanner(System.in);
		int testCases = Integer.parseInt(scanner.nextLine());
		while(testCases-->0){
		 	String input = scanner.nextLine();
		 	int operations = 0;
		 	for(int i=0,j=input.length()-1;i<input.length()/2;i++,j--){
		 		if(input.charAt(i)!=input.charAt(j))
		 			operations+=Math.abs(input.charAt(i)-input.charAt(j));
		 			
		 	}
		 	System.out.println(operations);
		}
		scanner.close();
	}
}