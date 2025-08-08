

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {


		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			long len = sc.nextInt();

			String str = sc.next();

			long passWord = searchPw(str);

			System.out.println("#" + tc + " " + passWord);
		}

	}

	private static long searchPw(String str) {
		str = str + "*";
		// 새 문자를 저장할 StringBuilder
		StringBuilder stringBuilder = new StringBuilder();
		boolean isRepeat = false;
		for (int i = 0; i < str.length() - 1; i++) {

			char ch = str.charAt(i);
			char next = str.charAt(i + 1);

			if (ch == next) {
				i += 1;
				isRepeat = true;
			} else {
				stringBuilder.append(ch);
			}
		}
		String newStr = stringBuilder.toString();

		if (isRepeat == false) {
			// 문자열을 정수형으로 표현하기 -> Integer.paseInt()를 사용하면 된다! -> 근데 지금은 int의 범위를 넘으니 long으로
			// 받아야 한다.
			//long으로 변환시키는 것이 if문 바깥에 있으면 NumberFormatException가 발생. (불필요하게 변환하고 있기 때문에)
			long pw = Long.parseLong(newStr);
			return pw;
		}

		return searchPw(newStr);
	}

}
