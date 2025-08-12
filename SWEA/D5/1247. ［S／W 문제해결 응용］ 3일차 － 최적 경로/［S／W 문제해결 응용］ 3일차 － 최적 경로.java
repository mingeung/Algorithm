

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N;
	static int[][] customer;
	static boolean[] visited;
	static int minDist;
	static int[] company;
	static int[] home;

	// 다음 step - customer for문 돌면서 입력받는 곳에서 maxX, maxY 값 업데이트
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();

			company = new int[] { sc.nextInt(), sc.nextInt() };
			home = new int[] { sc.nextInt(), sc.nextInt() };

			customer = new int[N][2];
			for (int i = 0; i < N; i++) {
				customer[i][0] = sc.nextInt();
				customer[i][1] = sc.nextInt();
			}

			visited = new boolean[N];
			minDist = Integer.MAX_VALUE;

			dfs(0, company, 0);
			System.out.println("#" + tc + " " + minDist);
		}

	}

	private static void dfs(int depth, int[] currentPos, int distSum) {
		// 가지치키
		if (distSum >= minDist)
			return;

		// N개를 모두 돌아야 할 때는 dfs에서 depth 값을 넘겨주기
		if (depth == N) {
			// 마지막으로 집까지의 거리를 구하기
			distSum += manhattan(currentPos, home);
			if (distSum < minDist)
				minDist = distSum;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				int nextDist = distSum + manhattan(currentPos, customer[i]);
				dfs(depth + 1, customer[i], nextDist);
				visited[i] = false;

			}
		}

	}

	private static int manhattan(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
}
