

import java.io.BufferedReader;

//첫번재 풀이
//치즈를 큐에 넣고, 큐를 돌면서 해당 치즈가 공기와 접촉이 되어 있는지를 확인함
//해당 방식의 문제점 -> 접촉한 공기가 외부의 공기인지, 내부의 공기인지 구별을 하지 못함
//-> 해결책으로 0,0 공기부터 시작해서 bfs 돌려야할 것 같음

//핵심!! -> 외부공기만 방문배열로 다 표시한다. bfs를 통해서 연결된 0만 찾아다니면 외부공기만 구분할 수 있다.
//격자판을 다 돌면서 외부공기와 접촉했는지 확인한다. 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	static int N;
	static int M;
	static int[][] cheese;
	static boolean[][] outAir;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cheese = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		int time = 0;

		while (true) {

			// 외부공기 배열
			outAir = new boolean[N][M];

			// 격자판을 탐색하면서 외부 공기는 true로 바꾸기 -> 큐로 해야지 이런!!!!
			int[] start = new int[] { 0, 0 };

			exploreOuterAir(start);

			// 치즈를 돌면서 외부 공기와 2개 이상 맞다아있으면 없애기
			// 없앨 치즈 저장
			List<int[]> willDisappear = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (cheese[i][j] == 1) {
						int[] target = new int[] { i, j };
						if (airCheck(target)) {
							willDisappear.add(target);
						}
					}
				}
			}

			if (willDisappear.size() == 0) {
				break;
			}

			// 다 돌면 그때 치즈 없애기
			for (int i = 0; i < willDisappear.size(); i++) {
				int x = willDisappear.get(i)[0];
				int y = willDisappear.get(i)[1];
				cheese[x][y] = 0;
			}

			time += 1;
		}

		System.out.println(time);
	}

	private static void exploreOuterAir(int[] start) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(start);
		visited[start[0]][start[1]] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];

			for (int k = 0; k < 4; k++) {
				int dx = x + di[k];
				int dy = y + dj[k];
				if (dx >= 0 && dx < N && dy >= 0 && dy < M && !visited[dx][dy]) {
					if (cheese[dx][dy] == 0) {
						q.offer(new int[] { dx, dy });
						visited[dx][dy] = true;
						outAir[dx][dy] = true;
					}
				}
			}
		}

	}

	// 공기랑 2면 이상 맞닿아있는지 확인
	private static boolean airCheck(int[] target) {
		boolean isDisappear = false;
		int exposeCount = 0;
		int x = target[0];
		int y = target[1];

		for (int k = 0; k < 4; k++) {
			int dx = x + di[k];
			int dy = y + dj[k];
			if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
				if (outAir[dx][dy]) {
					exposeCount += 1;
				}
			}
		}

		if (exposeCount >= 2) {
			isDisappear = true;
		}
		return isDisappear;

	}

}
