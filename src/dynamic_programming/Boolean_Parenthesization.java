package dynamic_programming;

/**
 * @author 47un
 *
 * Given a boolean expression with operands, T for true and F for false.
 * The following operators are also given :  
 *     &   ---> boolean AND
 *     |   ---> boolean OR
 *     ^   ---> boolean XOR
 * Count the number of ways we can PARENTHESIZE the expression so that the value of expression evaluates to true. 
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-37-boolean-parenthesization-problem/
 * https://people.cs.clemson.edu/~bcdean/dp_practice/dp_9.swf
 * 
 * =========
 * METHOD 1 
 * ========= 
 * trues[i][j] tracks the # parenthesization between i and j that give true
 * falses[i][j] tracks the # parenthesization between i and j that give false
 * If the operand[i] is true, then trues[i][i] is always 1. As the operand contributes a true to the final output
 * Same goes for falses[i][i] when operand[i] is false
 * The idea is to split the operand at an index, say splitIndex, and calculate the value of true and false arrays for the the partitions
 * For each split, (for loop on splitIndex)
 *  For each item on the right half (i just supports k, j is the big man here)
 *   Scan each item on the left half (k scans the left half)
 *    If the operator is ^ (TT, FF => F, TF, FT => T)
 *     For an xor operations,
 *      # trues  = TF * FT
 *      # falses = TT * FF 
 *    Else if the operator is & or |
 *     Calculate the total number of parenthesizations (Product of t + f of both intervals)
 *     For and operations,
 *      # trues  = TT
 *      # falses = total - trues
 *     For or operations,
 *      # falses = FF
 *      # trues  = total - falses
 *    Add up all the trues and feed it to trues[i][j]
 *    Add up all the falses and feed it to falses[i][j]
 *     
 *             
 * TIME    : O(n^3)
 * SPACE   : O(n^2)
 * 
 */

public class Boolean_Parenthesization {

	public static void main(String[] args) {
		char[] operand  = {'T', 'T', 'F', 'T'};
		char[] operator = {'|', '&', '^'};
		System.out.println(findNumberOfParanthesizations(operand, operator));
	}

	private static int findNumberOfParanthesizations(char[] operand, char[] operator) {
		int length = operand.length;
		int[][] trues  = new int[length][length];
		int[][] falses = new int[length][length];
		for(int i = 0; i < length; i++)
			if(operand[i] == 'T')
				trues[i][i] = 1;
			else
				falses[i][i] = 1;
		for(int splitIndex = 1; splitIndex < length; splitIndex++)
			for(int i = 0, j = splitIndex; j < length; i++, j++)
				for(int k = i; k < i + splitIndex; k++) {
					int truesUsingXOR  = 0;
					int falsesUsingXOR = 0;
					int truesUsingAND  = 0;
					int falsesUsingAND = 0;
					int falsesUsingOR  = 0;
					int truesUsingOR   = 0;
					if(operator[k] == '^') {
						truesUsingXOR  = trues[i][k] * falses[k + 1][j] + falses[i][k] * trues[k + 1][j];
						falsesUsingXOR = trues[i][k] * trues[k + 1][j] + falses[i][k] * falses[k + 1][j];
					}
					else {
						int totalCombinations = (trues[i][k] + falses[i][k]) * (trues[k + 1][j] + falses[k + 1][j]);
						if(operator[k] == '&') { 
							truesUsingAND  = trues[i][k] * trues[k + 1][j];
							falsesUsingAND = totalCombinations - truesUsingAND; 
						}
						else if(operator[k] == '|') {
							falsesUsingOR = falses[i][k] * falses[k + 1][j];
							truesUsingOR  = totalCombinations - falsesUsingOR;
						}
					}
					trues[i][j]  += truesUsingXOR + truesUsingAND + truesUsingOR;
					falses[i][j] += falsesUsingXOR + falsesUsingAND + falsesUsingOR;
				}
		return trues[0][length - 1];
	}
}
