
import java.io.FileInputStream;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int count; // 옮기는 방법의 수 세기
	static int[] horizontalR = { 0, 1 }; // 가로, 대각
	static int[] horizontalC = { 1, 1 };

	static int[] verticalR = { 1, 1 }; // 세로, 대각
	static int[] verticalC = { 0, 1 };

	static int[] diagonalR = { 0, 1, 1 }; // 가로,세로, 대
	static int[] diagonalC = { 1, 0, 1 };

	static int[][] home;

	static boolean[][] visited;

	public static void main(String[] args)  {


		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		home = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				home[i][j] = sc.nextInt();
			}
		}
//		System.out.println(Arrays.deepToString(home));
		// 방문배열 생성
		visited = new boolean[N][N];

		dfs(0, 1, "horizontal");

		// 가로 세로 대각선 : horizontal, vertical, 대각선 diagonal
		// 도착지는 (N-1, N-1)

		// 모든 경우의 수를 나타내야하기 때문에 dfs로 풀어야겠지?

		// 현재 위치와 방향을 넘겨줌

		System.out.println(count);
	}

	// 벽을 만났을 때는 이동을 못
	private static void dfs(int i, int j, String direction) {
		// 종료조건
		if (i == N - 1 && j == N - 1) {
			count += 1;
			return;
		}

		// 방문처리 해주기
		visited[i][j] = true;
		int r;
		int c;
		// 다음 재귀 돌리기
		switch (direction) {

		case "horizontal":
			// 아직 방문 안 하고 범위를 벗어나지 않는다
			r = i + horizontalR[0];
			c = j + horizontalC[0];
			if (canMove(r, c)) {
				dfs(r, c, "horizontal");
				visited[r][c] = false;
				// 백트래킹

			}
			r = i + horizontalR[1];
			c = j + horizontalC[1];
			if (canMoveDiagonal(r, c)) {
				dfs(r, c, "diagonal");
				visited[r][c] = false;
			}
			break;

		case "vertical":
			r = i + verticalR[0];
			c = j + verticalC[0];
			if (canMove(r, c)) {
				dfs(r, c, "vertical");
				visited[r][c] = false;
			}

			r = i + verticalR[1];
			c = j + verticalC[1];
			if (canMoveDiagonal(r, c)) {
				dfs(r, c, "diagonal");
				visited[r][c] = false;
			}
			break;

		case "diagonal":

			r = i + diagonalR[0];
			c = j + diagonalC[0];
			if (canMove(r, c)) {
				dfs(r, c, "horizontal");
				visited[r][c] = false;
			}

			r = i + diagonalR[1];
			c = j + diagonalC[1];
			if (canMove(r, c)) {

				dfs(r, c, "vertical");
				visited[r][c] = false;
			}

			r = i + diagonalR[2];
			c = j + diagonalC[2];
			if (canMoveDiagonal(r, c)) {
				dfs(r, c, "diagonal");
				visited[r][c] = false;
			}
			break;
		}
	}

	private static boolean canMove(int i, int j) {
		if (i < N && j < N && !visited[i][j] && home[i][j] == 0) {
			return true;
		}
		return false;

	}

	private static boolean canMoveDiagonal(int i, int j) {
		if (i < N && j < N && !visited[i][j] && home[i][j] == 0 && home[i][j - 1] == 0 && home[i - 1][j] == 0) {
			return true;
		}
		return false;

	}
}
