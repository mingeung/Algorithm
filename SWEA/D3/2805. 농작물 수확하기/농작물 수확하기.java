
import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();

			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] row = sc.next().split("");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(row[j]);
				}
			}

			// 큐 문제들에 왜 큐를 써야하는지 모르겠당.
			int center = N / 2;
			// N이 7이면 3

			int profit = 0;
			// 일단 가운데는 꽉 채워
			for (int i = 0; i < N; i++) {
				profit += arr[center][i];
			}
			// 그리고 그 위아래를 돌면서 채워
			int count = 1;

			//N이 5일 
			//i는 1, 2
			//j는 1, 2, 3 
			for (int i = 1; i < N / 2 + 1; i++) {
				//두 번 돌아
				for (int j = count; j <N - count; j++) {
					profit += arr[center - i][j];
					profit += arr[center + i][j];
				}
				count++;
			}

			System.out.println("#" + tc + " " + profit);
		}
	}

}
