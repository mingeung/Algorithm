import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static int searchCrossNum(char[] charArr) {

		// char 배열을 String으로 변환
		String str = new String(charArr);
		// 문자열을 정수로 변환
		int num = Integer.parseInt(str);

		// 첫번째 수를 최솟값으로 설정
		int minNum = num;

//			 시작지점
		for (int j = 1; j < 4; j++) {
			// 4자리니까 4번 돌아야지
			List<Character> cross = new ArrayList<>();
			for (int x = 0; x < 4; x++) {
				int idx = j + x;
				if (idx >= 4) {
					idx = idx % 4;
				}

				cross.add(charArr[idx]);
			}
			// 리스트가 만들어졌다.

			// char 배열을 String으로 변환 -> StringBuilder 사용
			StringBuilder sb = new StringBuilder();
			for (char ch : cross) {
				sb.append(ch);
			}
			String newStr = sb.toString();

			// 문자열을 정수로 변환
			int crossNum = Integer.parseInt(newStr);
			// 최솟값보다 작다면 업데이트
			if (crossNum < minNum) {
				minNum = crossNum;
			}
		}

		return minNum;

	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		// 십자모양의 숫자 배열에 넣기
		char arr[] = new char[4];

		for (int i = 0; i < 4; i++) {
			arr[i] = (char) (sc.nextInt() + '0');
		}

		int minNum = searchCrossNum(arr);
//		System.out.println("minNum: "  + minNum);

		int smallerNum = 1111;
		int count = 0;

		while (smallerNum <= minNum) {

			// smallerNum이 시계수인지 검사 -> int 배열에 담아서 보내야 한다.
			String str = String.valueOf(smallerNum);
			char[] chars = str.toCharArray();
			int newMinNum = searchCrossNum(chars);
			if (smallerNum == newMinNum) {

				// 각 자리에 0이 들어가는지 검사
				String numStr = String.format("%04d", smallerNum);
				char[] digits = numStr.toCharArray();

				if (digits[0] != '0' && digits[1] != '0' && digits[2] != '0' & digits[3] != '0') {
//					System.out.println("smallerNum : " + smallerNum);
					count += 1;
				}
			}

			smallerNum += 1;

		}

		System.out.println(count);
	}

}
