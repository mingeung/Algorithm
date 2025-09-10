

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int[][] board;
	static int[][] island;
	static boolean[][] visited;

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 각 섬의 테두리를 섬 번호로 적기
		// 육지라면 -1을 넣기

		// 섬을 구분한 새로운 배열 만들기
		island = new int[N][N];
		visited = new boolean[N][N];
		int count = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0 && !visited[i][j]) {
					bfs(count, i, j);
					count += 1;
				}
			}
		}

		int minPath = Integer.MAX_VALUE;
		// 섬의 개수만큼 돌면서 길이를 구하기
		// 맨허튼 거리 구하기
		for (int m = 1; m <= count; m++) {
			// m번째 섬의 출발점을 다 구하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (island[i][j] == m) {

						// 출발지에서 도착지까지
						for (int x = 0; x < N; x++) {
							for (int y = 0; y < N; y++) {
								if (island[x][y] != m && island[x][y] != 0) {
									// 거리 구하기
									int currPath = manhattan(i, j, x, y);
									// 최소거리 업데이트
									minPath = Integer.min(minPath, currPath);

								}
							}
						}

					}
				}
			}
		}
		System.out.println(minPath -1);
	}

	private static void bfs(int count, int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		island[i][j] = count;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			for (int k = 0; k < 4; k++) {
				int nx = x + di[k];
				int ny = y + dj[k];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && board[nx][ny] == 1) {
					visited[nx][ny] = true;
					island[nx][ny] = count;
					q.offer(new int[] { nx, ny });
				}
			}

		}
	}

	public static int manhattan(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
