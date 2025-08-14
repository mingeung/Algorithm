import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 1; i <= T; i++) {
			int cnt = 0; // 카운트 값 증가시키기

			// 0011 입력값을 String으로 받아야 한다. int로 받게 되면 00이 사라진다.
			String str = sc.next();
			int[] arr = new int[str.length()];
			int[] zeroArr = new int[str.length()];

			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == '1') {
					arr[j] = 1;
				}
			}

			for (int j = 0; j < str.length(); j++) {

				if (arr[j] == zeroArr[j]) {
					continue;
				}
				// 다르면
				int changeNum = 0;
				if (zeroArr[j] == 0) {
					changeNum = 1;
				}

				for (int k = j; k < zeroArr.length; k++) {
					zeroArr[k] = changeNum;

				}
				cnt += 1;
			}

			System.out.println("#" + i + " " + cnt);

		}

	}

}
