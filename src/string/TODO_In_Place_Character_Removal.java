package string;

public class TODO_In_Place_Character_Removal {
	public static void main(String...strings){
		String input = "aaabddabbbbbcacacaccab";
		TODO_In_Place_Character_Removal in_Place_Character_Removal = new TODO_In_Place_Character_Removal();
		in_Place_Character_Removal.removeBAC(input);
	}

	private String removeBAC(String input){
		char[] charArray = input.toCharArray();
		for(int i = 0; i < input.length(); i++){
			if(charArray[i] == 'b')
				charArray[i] = '\u0000';
			else if(charArray[i] == 'c'){
				if(i-1 >= 0 && charArray[i-1] == 'a'){
					charArray[i] = '\u0000';
					charArray[i-1] = '\u0000';
				}					
			}
		}
		return charArray.toString();
	}
}
