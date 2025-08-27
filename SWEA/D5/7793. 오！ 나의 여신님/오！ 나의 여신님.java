

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

public class Solution {
	// S : 수연이의 위치
	// D : 여신의 공간
	// X : 돌
	// * : 악마

	static int N;
	static int M;
	static char[][] board;
	static int[][] s_time;

	static Queue<int[]> suyeonQ;
	static Queue<int[]> devilQ;

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			board = new char[N][M];
			s_time = new int[N][M];
			suyeonQ = new LinkedList<>();
			devilQ = new LinkedList<>();
			int startR = 0, startC = 0;
			int destR = 0, destC = 0;

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					board[i][j] = str.charAt(j);
					s_time[i][j] = -1; // 방문배열을 -1로 초기화

					if (board[i][j] == 'S') {
						startR = i;
						startC = j;

					} else if (board[i][j] == 'D') {
						destR = i;
						destC = j;
					}

					else if (board[i][j] == '*') {
						devilQ.offer(new int[] { i, j }); // 악마는 여러마리일 수 있다.

					}
				}
			}

			// BFS 시작 전 수연이와 여신의 위치를 임시로 변경하여 악마가
//			board[startR][startC] = '.';
//			board[destR][destC] = '.';

			suyeonQ.offer(new int[] { startR, startC });
			s_time[startR][startC] = 0;

			int result = bfs(destR, destC);

			if (result == -1) {
				System.out.println("#" + tc + " GAME OVER");

			} else {
				System.out.println("#" + tc + " " + result);
			}
		}
	}

	private static int bfs(int destR, int destC) {

		// 수연 한 바퀴 돌기
		while (!suyeonQ.isEmpty()) {
			// 악마가 먼저 확장
			int devilSize = devilQ.size();
			for (int i = 0; i < devilSize; i++) {
				int[] curr = devilQ.poll();
				int r = curr[0];
				int c = curr[1];

				for (int k = 0; k < 4; k++) {
					int nr = r + di[k];
					int nc = c + dj[k];
					// 범위가 맞고. S이거나 .이면 확장
					if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
						if (board[nr][nc] == '.') {
							board[nr][nc] = '*';
//							System.out.println("악마 확장 범위: " + nr + " " + nc);
							// 다음 큐 후보에 넣기
							devilQ.offer(new int[] { nr, nc });

						}
					}
				}
			}

			// 수연이 이동
			int suyeonSize = suyeonQ.size();

			// 더이상 이동할 수 있는 칸이 없을 때
			if (suyeonSize == 0) {
				return -1;
			}

			for (int i = 0; i < suyeonSize; i++) {

				int[] curr = suyeonQ.poll();
				int r = curr[0];
				int c = curr[1];
				int time = s_time[r][c];
//				System.out.println("수연이의 위치:" + Sx + Sy);

				// 사방 탐색
				for (int k = 0; k < 4; k++) {
					int nr = r + di[k];
					int nc = c + dj[k];

					if (nr == destR && nc == destC) {
						return time + 1;
					}

					// 범위가 맞고. S이거나 .이면 확장
					if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
						// 악마가 확장할 위치가 아닌지 확인

						if (board[nr][nc] == '.' && s_time[nr][nc] == -1) {
//							System.out.println("여기를 오는지?");
							s_time[nr][nc] = time + 1;
							suyeonQ.offer(new int[] { nr, nc });
//							System.out.println("수연 확장 범위: " + nr + " " + nc);
							// 여기 조건을 만족하면 while(true) 루트를 멈추도록 하기
						}
					}
				}
			}
		}
		return -1; // 여신에게 도착 불가능
	}
}
