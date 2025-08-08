

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case ++) {
			int isPalindrome = 0;
			String str = sc.next();
			
			int N = str.length();
			
			char[] arr = new char[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = str.charAt(N - i - 1);
			} 
			String str2 = new String(arr);
			
			if (str.equals(str2)) {
				isPalindrome = 1;
			}
			
			
			System.out.println("#" + test_case + " " + isPalindrome);
			
	}
		
		
	}

}