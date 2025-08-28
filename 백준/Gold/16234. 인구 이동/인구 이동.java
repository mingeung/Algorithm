

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int days;
	static int N, L, R;
	static int[][] board;

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	static int totalP;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		days = 0;

		while (true) {
			boolean[][] visited = new boolean[N][N];
			boolean moved = false; // 인구이동 확인

			// 모든 칸 돌기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) { // 만약 아직 방문하지 않았으면
						totalP = 0;
						List<int[]> lists = bfs(i, j, visited); // visited를 bfs에서 새로 선언하는 것이 아니라 넘겨줘야 한다.
						if (lists.size() > 1) {
							moved = true;
							for (int l = 0; l < lists.size(); l++) {
								int newP = totalP / lists.size();
								board[lists.get(l)[0]][lists.get(l)[1]] = newP;
							}
						}
					}
				}
			}
			if (!moved) {
				break;
			}
			days++;

		}

		System.out.println(days);

	}

	private static List<int[]> bfs(int i, int j, boolean[][] visited) {
		List<int[]> result = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		result.add(new int[] { i, j });
		totalP += board[i][j];

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int r = node[0];
			int c = node[1];
			// 델타탐색
			for (int k = 0; k < 4; k++) {
				int dr = r + di[k];
				int dc = c + dj[k];

				// 범위 확인
				if (dr >= 0 && dr < N && dc >= 0 && dc < N && !visited[dr][dc]) {

					// L, R 값을 만족하는지 확인
					int currP = board[r][c];
					int nextP = board[dr][dc];
					int diff = currP - nextP;
					if (diff < 0) {
						diff = -diff;
					}
					if (diff >= L && diff <= R) {
						visited[dr][dc] = true;
						q.offer(new int[] { dr, dc });
						result.add(new int[] { dr, dc });
						totalP += board[dr][dc];
					}
				}
			}

		}
		return result;

	}

}
