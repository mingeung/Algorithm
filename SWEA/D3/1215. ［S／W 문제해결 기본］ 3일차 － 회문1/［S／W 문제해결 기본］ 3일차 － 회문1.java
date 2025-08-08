

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int len = sc.nextInt(); // 얘가 줄바꿈을 하지 않음. 줄바꿈을 하나 해줘야 함
			sc.nextLine();
//			System.out.println("len:" + len);

			// 회문의 개수
			int count = 0;

			int N = 8;
			char[][] arr = new char[N][N];

			// 문자열이 붙여서 input된다

			for (int i = 0; i < N; i++) {
				String line = sc.nextLine();
//				System.out.println("line:" + line);
				for (int j = 0; j < N; j++) {
					arr[i][j] = line.charAt(j);
				}
			}

//			System.out.println("arr : " + Arrays.deepToString(arr));

//			행에 회문이 있는지 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - len + 1; j++) {
					boolean isPalindrome = true;
					// 회문인지 확인
					// N/2번만 검사하면 된다.
					for (int x = 0; x < len / 2; x++) {
						if ((arr[i][j + x] != (arr[i][j + len - x - 1]))) {
							// j = 0, x = 1
							// len = 4
							isPalindrome = false;
						}

					}
					if (isPalindrome) {
						count += 1;
					}
				}
			}

			// 열에 회문이 있는지 찾기
			// 행에 회문이 있는지 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - len + 1; j++) {
					boolean isPalindrome = true;
					// 회문인지 확인
					// N/2번만 검사하면 된다.
					for (int x = 0; x < len / 2; x++) {
						if (arr[j + x][i] != arr[j + len - x - 1][i]) {
							isPalindrome = false;
						}

					}
					if (isPalindrome) {
						count += 1;
					}
				}
			}

			System.out.println("#" + tc + " " + count);
		}

	}

}
