
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//백트래킹 - 제한시간 초과
//우선순위 큐 - 방문배열 필요없음

public class Solution {
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	static int N;
	static private int INF = 200_000_000;
	static int[][] ground;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ground = new int[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					ground[i][j] = str.charAt(j) - '0';
				}
			}

			int minPath = dijkstra();

			System.out.println("#" + tc + " " + minPath);
		}
	}

	private static int dijkstra() {
		int[][] dist = new int[N][N];
		for (int[] row : dist) {
			Arrays.fill(row, INF); // 이건 1차원 배열에만 적용된다.
		}
		dist[0][0] = 0;

		// 우선순위 큐 생성 -> Comparator을 직접 지정해야 한다.
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		pq.offer(new int[] { 0, 0, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int currX = curr[0];
			int currY = curr[1];
			int cost = curr[2];

			// 이미 더 싼 경로가 있으면 스킵 -> 이 부분 추가
			if (cost > dist[currX][currY])
				continue;

			// 도착지점이면 탐색 멈추기
			if (currX == N - 1 && currY == N - 1)
				return cost;

			for (int k = 0; k < 4; k++) {
				int dx = currX + di[k];
				int dy = currY + dj[k];

				// 범위초과나지 않는지 확인
				if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
					// 범위가 초과나지 않는다면,,,
					int nCost = cost + ground[dx][dy];

					// 기존의 거리와 비교하면서 최단 거리 갱신
					if (nCost < dist[dx][dy]) {
						dist[dx][dy] = nCost;
						// 큐에 값 넣기
						pq.offer(new int[] { dx, dy, nCost });

					}
				}
			}

		}

		return dist[N - 1][N - 1];
	}
}
