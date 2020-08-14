package string;

import java.util.Arrays;

/**
 * @author 47un
 *
 * Given a plain-text message and a numeric key, cipher/de-cipher the given text using Rail Fence algorithm.
 * The rail fence cipher (also called a zigzag cipher) is a form of transposition cipher.
 * It derives its name from the way in which it is encoded.
 * 
 * http://www.geeksforgeeks.org/rail-fence-cipher-encryption-decryption/
 * 
 * METHOD 1
 * ========
 * Encryption
 *  Prepare a matrix of order k x n, where n is the number of characters in the string
 *  Fill the matrix with * character
 *  Each character of the input string falls into column = character's index in the string
 *   That is why there are n columns
 *  So scan the input
 *   Place a character at row x column of the matrix
 *   If this is the last row, we have to move diagonally upwards
 *   If this is the first row, we have to move diagonally downwards
 *   According to the direction, increment or decrement the row
 *  Once the above stuff is done, the matrix now has the input in zig zag form
 *  Read the matrix row-wise, add all characters other than * to the output (cipher text) 
 * 
 * Decryption
 *  Prepare a matrix of order k x n, where n is the number of characters in the string
 *  Fill the matrix with * in a zig zag manner
 *   Similar to how characters were stored in the matrix during encryption
 *  Scan the matrix and place characters at the locations marked with *
 *  Scan the matrix again, in a zig zag manner and add all character other than * to the output (plain text)             
 *             
 * TIME    : O(k x n)  // k x n matrix gets scanned
 * SPACE   : O(k x n)  // The matrix!
 * 
 */

public class Rail_Fence_Cipher {

	public static void main(String[] args) {
		String plainText = "We attack at dawn!";
		int k = 3;
		String cipherText = encrypt(plainText, k);
		System.out.println(cipherText);
		plainText = decrypt(cipherText, k);
		System.out.println(plainText);
	}

	private static String encrypt(String plainText, int k) {
		System.out.println("Encrypting...\n");
		char[][] railMatrix = new char[k][plainText.length()];
		for(char[] rail : railMatrix)
			Arrays.fill(rail, '*');
		boolean goingDown = true;
		for(int row = 0, column = 0; column < plainText.length(); column++) {
			railMatrix[row][column] = plainText.charAt(column);
			goingDown = row == k - 1? false : row == 0? true : goingDown;
			row += goingDown? 1 : -1;
		}
		char[] cipherText = new char[plainText.length()];
		int index = 0;
		for(char[] rail : railMatrix)
			for(char character : rail)
				if(character != '*')
					cipherText[index++] = character;
		return new String(cipherText);
	}
	
	private static String decrypt(String cipherText, int k) {
		System.out.println("\nDecrypting....\n");
		char[][] railMatrix = new char[k][cipherText.length()];
		boolean goingDown = true;
		for(int row = 0, column = 0; column < cipherText.length(); column++) {
			railMatrix[row][column] = '*';
			goingDown = row == k - 1? false : row == 0? true : goingDown;
			row += goingDown? 1 : -1;
		}
		int index = 0;
		for(int row = 0; row < k; row++)
			for(int column = 0; column < cipherText.length(); column++)
				if(railMatrix[row][column] == '*')
					railMatrix[row][column] = cipherText.charAt(index++);
		char[] plainText = new char[cipherText.length()];
		for(int row = 0, column = 0; column < cipherText.length(); column++) {
			plainText[column] = railMatrix[row][column];
			goingDown = row == k - 1? false : row == 0? true : goingDown;
			row += goingDown? 1 : -1;
		}
		return new String(plainText);
	}
}
