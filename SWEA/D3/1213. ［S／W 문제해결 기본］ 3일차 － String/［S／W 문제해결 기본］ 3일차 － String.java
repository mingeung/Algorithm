

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 0; tc < 10; tc++) {
			int T = sc.nextInt();
			String target = sc.next();
			String str = sc.next();
			
//			System.out.println(T);
//			System.out.println(target);
//			System.out.println(str);
			
			
			int N = str.length();
			int M = target.length();
			
			int count = 0;
			
			for (int i = 0; i < N - M + 1; i++) {
				boolean isOk = true;
				for (int j = 0; j < M; j++) {
					if (str.charAt(i + j) != target.charAt(j)) {
						isOk = false;
						break;
					}
				}
				if (isOk) {
					count += 1;
				}
			}
			
			System.out.println("#" + T + " " + count);
		}
		
	}

}
