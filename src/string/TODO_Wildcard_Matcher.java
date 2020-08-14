package string;

public class TODO_Wildcard_Matcher {
	public static void main(String...strings){
		String input = "abcd";
		String pattern = "*??*d";
		TODO_Wildcard_Matcher wildcardMatcher = new TODO_Wildcard_Matcher();
		System.out.println(wildcardMatcher.verify(input, 0, pattern, 0) ? "Yes" : "No");
//		System.out.println(wildcardMatcher.verify("g", 0, "g*", 0) ? "Yes" : "No");
//		System.out.println(wildcardMatcher.verify("gee", 0, "g*k", 0) ? "Yes" : "No");
//		System.out.println(wildcardMatcher.verify("pqrst", 0, "*pqrs", 0) ? "Yes" : "No");
//		System.out.println(wildcardMatcher.verify("abcdhghgbcd", 0, "abc*bcd", 0) ? "Yes" : "No");
//		System.out.println(wildcardMatcher.verify("abcd", 0, "abc*c?d", 0) ? "Yes" : "No");
//		System.out.println(wildcardMatcher.verify("abcd", 0, "*c*d", 0) ? "Yes" : "No");
//		System.out.println(wildcardMatcher.verify("abcd", 0, "*?c*d", 0) ? "Yes" : "No");
	}

	private boolean verify(String input, int inputIndex, String pattern, int patternIndex) {
		if(inputIndex == input.length() && patternIndex == pattern.length())
			return true;		
		if(pattern.charAt(patternIndex) == '*'){
			if(inputIndex == input.length()){
				if(patternIndex == pattern.length()-1)
					return true;
				return false;
			}
			return verify(input, inputIndex+1, pattern, patternIndex) || verify(input, inputIndex, pattern, patternIndex+1);
		} 
		if(pattern.charAt(patternIndex) == '?' || pattern.charAt(patternIndex) == input.charAt(inputIndex))
			return verify(input, inputIndex+1, pattern, patternIndex+1);			
		return false;
	}
}
