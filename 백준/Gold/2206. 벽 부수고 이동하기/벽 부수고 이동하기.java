

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;

	static int[][] board;

	static int broke;
	static int destX;
	static int destY;
	static int minPath;

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	static boolean[][][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력값이 6 4 처럼 들어왔을 때 N, M을 받는 방법
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 2차원 배열만들기
		board = new int[N][M];

		// N줄 입력받아서 2차원 배열에 저장하는 방법
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				// '1'이면 1, '0'이면 0으로 저장
				board[i][j] = str.charAt(j) - '0';
			}
		}

		destX = N - 1;
		destY = M - 1;

		broke = 0; // 아직 부수지 않았으면 0, 부쉈으면 1

		// 방문배열
		visited = new boolean[N][M][2];

		minPath = Integer.MAX_VALUE;

		int path = 1;

		bfs(0, 0, path);

		if (minPath == Integer.MAX_VALUE) {
			minPath = -1;
		}

		System.out.println(minPath);

	}

	private static void bfs(int x, int y, int path) {
		Queue<int[]> q = new LinkedList<>();
		visited[x][y][0] = true;
		q.offer(new int[] { x, y, path, 0 });

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int r = node[0];
			int c = node[1];
			int currPath = node[2];
			int currBroke = node[3];
//			System.out.println("r : " + r + " c : " + c);

			// 도착지점에 왔다면
			if (r == destX && c == destY) {
//				System.out.println("도착지점 : " + currPath);
//				currPath += 1;
				// 최소의 값과 비교하기
				if (currPath < minPath) {
					minPath = currPath;
				}
				return;
			}

			// minPath를 초과하면 return 처리하기
			if (currPath > minPath) {
				return;
			}

			// 상하좌우를 돌면서 탐색
			for (int k = 0; k < 4; k++) {

				int dx = r + di[k];
				int dy = c + dj[k];

				// 범위 확인
				if (dx >= 0 && dx < N && dy >= 0 && dy < M) {

					if (board[dx][dy] == 0 && !visited[dx][dy][currBroke]) {
						visited[dx][dy][currBroke] = true; //상태에 맞게 방문체크
						q.add(new int[] { dx, dy, currPath + 1, currBroke });
					}
					// 벽이 막혀있지만 뚫을 수 있는 경우
					else if (board[dx][dy] == 1 && currBroke == 0 && !visited[dx][dy][1]) {
						visited[dx][dy][1] = true;
						q.add(new int[] { dx, dy, currPath + 1, 1 });

					}

				}
			}
		}
	}

}

//bfs 방문배열 잘 모르겠는 점
//bfs에 대해서 잘못 이해한 점 - 모든 경로를 탐색하지 않고 가능한 경우를 돈다. 
//VISITED 배열을 쓰는 것도 모든 경로를 탐색하지 않기 때문에 가능한 것이다.

//그렇기 때문에 이 문제에서는 visited 배열을 조금 손봐야 한다.
//하나의 공간에도 벽을 부수는 경우와 부수지 않는 경우, 두 가지로 방문이 가능하기 때문이다. 

//지금 돌고 있는 큐가 벽을 부쉈는지 여부 (큐에 정보를 넣어야 한다.) 

//한번 벽을 부수고 나면, 벽으 부수지 않는 경로로 탐색해야 한다. 
