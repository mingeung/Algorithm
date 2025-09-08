

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static boolean[][] visited;
	static int[][] board;
	static int[] startP;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static boolean isPossible;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());

			board = new int[100][100];
			startP = new int[2];

			// 미로 입력값 받기
			for (int i = 0; i < 100; i++) {
				String str = br.readLine();
				for (int j = 0; j < 100; j++) {
					board[i][j] = str.charAt(j) - '0';
					if (board[i][j] == 2) {
						startP = new int[] { i, j };
					}
				}
			}

			visited = new boolean[100][100];
			// bfs로 해야징
			isPossible = false;
			bfs(startP[0], startP[1]);
			int ans = isPossible ? 1 : 0;

			System.out.println("#" + T + " " + ans);
		}
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { i, j });
		// 방문처리
		visited[i][j] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];

			if (board[x][y] == 3) {
				isPossible = true;
				return;
			}

			for (int k = 0; k < 4; k++) {
				int dx = x + di[k];
				int dy = y + dj[k];
				// 범위확인
				if (dx >= 0 && dx < 100 && dy >= 0 && dy < 100 && !visited[dx][dy] && board[dx][dy] != 1) {
					visited[dx][dy] = true;
					q.offer(new int[] { dx, dy });

				}
			}

		}
	}

}
