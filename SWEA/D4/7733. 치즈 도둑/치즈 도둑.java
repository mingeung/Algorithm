
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] cheese;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 최댓값을 빼먹기 쉽게 list로 관리하겠다
			List<Integer> howManyCheese = new ArrayList<>();

			//days는 1이 아니라 0부터 시작해야 한다. 
			for (int days = 0; days <= 100; days++) {
				// days가 적힌 치즈를 다 파먹어버려

				int count = 0;
				visited = new boolean[N][N];
				int howMany = bfs(count, visited, days);
				howManyCheese.add(howMany);
			}

			// 배열에서 가장 큰 수 뽑아내기
			int ans = Collections.max(howManyCheese);
			System.out.println("#" + tc + " " + ans);

		}
	}

	// 예전에도 이랬다. 덩어리의 bfs를 셀 때 한 덩어리를 세고 다음 덩어리로 어떻게 넘어가지? 했는데 그냥 for 반복문을 돌리면 됐었다.

	//날짜가 지난 부분을 -1로 바꾸는 등 배열을 변경시키는 것이 아니라 조건문으로 cheese[i][j] > days 처럼 처리하면 된다.
	private static int bfs(int count, boolean[][] visited, int days) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cheese[i][j] > days && !visited[i][j]) {
					// 시작할 곳을 찾아
					int[] startP = new int[] { i, j };
					Queue<int[]> q = new LinkedList<>();
					q.offer(startP);
					visited[startP[0]][startP[1]] = true;

					while (!q.isEmpty()) {

						int[] curr = q.poll();
						int x = curr[0];
						int y = curr[1];

						for (int k = 0; k < 4; k++) {
							int nx = x + di[k];
							int ny = y + dj[k];

							if (nx >= 0 && nx < N && ny >= 0 && ny < N && cheese[nx][ny] > days && !visited[nx][ny]) {
								q.offer(new int[] { nx, ny });
								visited[nx][ny] = true;
							}
						}
					}
					count += 1;
				}
			}
		}
		return count;
	}

}
