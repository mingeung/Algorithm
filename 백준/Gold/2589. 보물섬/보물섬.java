import java.io.BufferedReader;
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

	static int N, M;
	static char[][] map;
	static List<int[]> land;

	static int maxDistance;

	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		land = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'L') {
					land.add(new int[] { i, j });
				}
			}
		}

		maxDistance = 0;

		// 시작점과 끝점
		for (int i = 0; i < land.size(); i++) {

				int[] t1 = land.get(i);


				int dist = distance(t1);
				if (dist == -1) { // 도달 불가
					continue;
				}
//				System.out.println(Arrays.toString(t1) + " " + Arrays.toString(t2) + ":" + dist);

				maxDistance = Math.max(dist, maxDistance);


		}
		System.out.println(maxDistance);
	}

	// 시간초과
	// 현재 코드는 모든 가능한 시작점, 끝점을 잡고 bfs를 돌리고 있음
	// 개선점 : 시작점만 잡고 maxDist를 구한다.

	private static int distance(int[] t1) {
		// 최소거리니까 bfs
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N][M];

		int start_x = t1[0];
		int start_y = t1[1];
		int path = 0;

		//거리 path를 변수로 관리하는 것이 아니라
		//level 단위로 증가시켜야 하기 때문에 좌표와 함께 관리한다.
		q.offer(new int[] { start_x, start_y, path }); 
		visited[t1[0]][t1[1]] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int curr_x = curr[0];
			int curr_y = curr[1];
			int curr_path = curr[2];

			// 목적지에 도착하면
			maxDistance = Math.max(maxDistance, curr_path);
			
			
			for (int k = 0; k < 4; k++) {
				int nx = curr_x + di[k];
				int ny = curr_y + dj[k];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 'L' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny, curr_path + 1 });
				}
			}

		}

		return -1;
	}

}
