
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	// 진행방향 대각선
	// 하좌, 상좌, 상우, 하우
	// 이 순서대로 이동하기
	// 순서대로 0, 1, 2, 3
	static int[] di = { 1, -1, -1, 1 };
	static int[] dj = { -1, -1, 1, 1 };
	static int[][] cafe;
	static int N;

	static int startX;
	static int startY;

	static int maxCount;

	static boolean[] dessertVisited;

	// 여기에서는 visited 배열이 필요하지 않다. 되돌아가는 방향이 아니라 사각형을 그리는 모양이기 때문에 중복방문할 필요가 없다.
	// 이전에 디저트 먹었는지를 list로 관리를 했었다. list에 먹은 디저트를 넣는 식으로. 그러나 이때는 중복확인이 어렵기 때문에
	// boolean 배열을 만들어 확인해준다.

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			cafe = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cafe[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력값 받기

			maxCount = -1;

			// 출발지를 쫙 돌기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					startX = i;
					startY = j;
					// 방향도 넣어주기 -> 이 부분 추가
					dessertVisited = new boolean[101];
					dessertVisited[cafe[i][j]] = true;

					dfs(startX, startY, 0, 1);
				}
			}
			System.out.println("#" + tc + " " + maxCount);
		} // test case
	}

	private static void dfs(int x, int y, int direction, int count) {

		// for문 만들어야돼. -> 추가한 부분
		for (int d = direction; d < 4; d++) {

			// d == dir : 현재 방향으로 직진
			// d == dir + 1 : 다음 순서로 걲기
			// d == dir + 2 : 두 방향을 건너뛰는 거
//			 아래 코드는 왜 있는지 이해못해
			if (d > direction + 1) {
				break;
			}

			// 방문배열 만들기

			int dx = x + di[d];
			int dy = y + dj[d];
			// 종료조건
			if (dx == startX && dy == startY) {
				if (d == 3) {
					int result = Math.max(maxCount, count);
					maxCount = result;
					continue;
				}
			}

			if (indexCheck(dx, dy)) {
				dessertVisited[cafe[dx][dy]] = true;
				dfs(dx, dy, d, count + 1); //
				dessertVisited[cafe[dx][dy]] = false;

			}

		}
	}

	private static boolean indexCheck(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N && !dessertVisited[cafe[x][y]]) {
			return true;
		}
		return false;
	}
}
