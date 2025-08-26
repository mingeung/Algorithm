
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

//파이프 아다리가 맞아야 된다.
//설계를 하고 문제를 풀자... 반성

public class Solution {

	static boolean[] t1 = { true, true, true, true };
	static boolean[] t2 = { true, true, false, false };
	static boolean[] t3 = { false, false, true, true };
	static boolean[] t4 = { true, false, false, true };
	static boolean[] t5 = { false, true, false, true };
	static boolean[] t6 = { false, true, true, false };
	static boolean[] t7 = { true, false, true, false };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken()); // 경과시간

			int[][] board = new int[N][M];
			boolean[][] visited = new boolean[N][M]; // 탈주범이 갈 수 있는 위치를 저장할 곳

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 큐를 만들고 넣어야지
			Queue<int[]> q = new LinkedList<>();

			// 1번째 시간은 처리
			q.offer(new int[] { R, C });

			visited[R][C] = true;
			// L시간만큼 반복
			for (int i = 2; i <= L; i++) {

				List<int[]> nextQ = new ArrayList<>();

				while (!q.isEmpty()) {
//					System.out.println("큐 사이즈 : " + q.size());

					// 큐에서 빼기
					int[] node = q.poll();
					int r = node[0];
					int c = node[1];

//					System.out.println("r : " + r + " c : " + c);

					// rc의 파이프 모양이 무엇인지 확인해서 큐에 넣기
					int type = board[r][c];

					boolean[] currTunnel = searchTunnel(type);
					boolean[] nextTunnel = null;

					// for문 안 돌리고 그냥 조건문으로 처리해도 되겠다.
					if (currTunnel[0]) { // 상이면 위의 파이프가 '하'에 연결되었는지
						if (r - 1 >= 0) {
							int nextType = board[r - 1][c];
							if (nextType > 0) {
								nextTunnel = searchTunnel(nextType);
								if (nextTunnel[1] && !visited[r - 1][c]) {
//									q.offer(new int[] { r - 1, c });
									nextQ.add(new int[] { r - 1, c });
									visited[r - 1][c] = true;
								}
							}
						}
					}

					if (currTunnel[1]) { // 하이면 아래의 파이프가 '상'에 연결되는지
						if (r + 1 < N) {
							int nextType = board[r + 1][c];
							if (nextType > 0) {
								nextTunnel = searchTunnel(nextType);
								if (nextTunnel[0] && !visited[r + 1][c]) {
//									q.offer(new int[] { r + 1, c });
									nextQ.add(new int[] { r + 1, c });
									visited[r + 1][c] = true;
								}
							}
						}
					}

					if (currTunnel[2]) { // '좌'이면 '우'의 파이프가 왼쪽으로 연결되는지
						if (c - 1 >= 0) {
							int nextType = board[r][c - 1];
							if (nextType > 0) {
								nextTunnel = searchTunnel(nextType);
								if (nextTunnel[3] && !visited[r][c - 1]) {
//									q.offer(new int[] { r, c - 1 });
									nextQ.add(new int[] { r, c - 1 });
									visited[r][c - 1] = true;
								}
							}
						}
					}

					if (currTunnel[3]) { // '우'이면 좌의 파이프가 '우'에 연결되는
						if (c + 1 < M) {
							int nextType = board[r][c + 1];
							if (nextType > 0) {
								{
									nextTunnel = searchTunnel(nextType);
									if (nextTunnel[2] && !visited[r][c + 1]) {
//										q.offer(new int[] { r, c + 1 });
										nextQ.add(new int[] { r, c + 1 });
										visited[r][c + 1] = true;
									}
								}
							}
						}
					}

				}
				for (int j = 0; j < nextQ.size(); j++) {
					q.offer(nextQ.get(j));

				}
//				System.out.println(Arrays.deepToString(visited));

			}

			int count = 0;

			// visited에서 true인 개수 구하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) {
						count += 1;
					}
				}
			}
			System.out.println("#" + tc + " " + count);

		}
	}

	private static boolean[] searchTunnel(int type) {
		boolean[] result = null;

		switch (type) {

		case (1): // 상하좌우
			result = t1;
			break;

		case (2): // 상하
			result = t2;
			break;
		case (3): // 좌우
			result = t3;
			break;
		case (4): // 상우
			result = t4;
			break;
		case (5): // 하우
			result = t5;
			break;
		case (6): // 하좌
			result = t6;
			break;
		case (7): // 상좌
			result = t7;
			break;
		}

		return result;
	}
}
