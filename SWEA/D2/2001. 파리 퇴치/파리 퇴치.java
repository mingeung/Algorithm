

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {


		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int maxFly = 0;

			int N = sc.nextInt();
			int M = sc.nextInt();

			// 2차원 배열 정의
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			// 파리 몇 마리 죽일 수 있는지 -> M이 2인 경우만 고려했다...!
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int totalFly = 0;
					for (int r = 0; r < M; r++) {
						for (int c = 0; c < M; c++) {
							if (i + r < N && j + c < N) {
								totalFly += arr[i + r][j + c];
							}
						}
					}
					if (totalFly > maxFly) {
						maxFly = totalFly;
					}

				}
			}

			System.out.println("#" + tc + " " + maxFly);
		}
	}
}
