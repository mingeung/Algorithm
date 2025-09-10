
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N;
	static int[] di = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dj = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static char[][] board;
	static boolean[][] visited;
	static int[][] numBoard;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			board = new char[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
//				System.out.println(str);
				for (int j = 0; j < N; j++) {
					board[i][j] = str.charAt(j);
				}
			}

			// 8방향이 .이면 먼저 터뜨리기
			// 없으면 숫자 적ㄱ기

			numBoard = new int[N][N];
			// 0이랑 혼동되지 않게 처음은 -1로 채우기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					numBoard[i][j] = -1;
				}
			}
			
			

			int click = 0;

			// 8방향이 다 .일 때
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 8방향으로 다 .인지 확인
					if (board[i][j] == '.' && checkZeroMine(i, j) && numBoard[i][j] == -1) {
//						System.out.println("0검사 : " + "i : " + i + "j : " + j);
//						// bfs로 돌면서 0으로 만들기
						zeroBfs(i, j);
						click += 1;
//						System.out.println("8방향 검사 후 : " + Arrays.deepToString(numBoard));
//						System.out.println("----------------------------");
					}

				}
			}


			// 8방향 다 돌았으면 나머지 것들 채우기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 지뢰가 아니고 방문하지 않았다면
					if (board[i][j] == '.' && numBoard[i][j] == -1) {
						numBoard[i][j] = checkMine(i, j);
						click += 1;
					}

				}
			}

			System.out.println("#" + tc + " " + click);
		}
	}

	private static void zeroBfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y });
		numBoard[x][y] = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int currX = curr[0];
			int currY = curr[1];
			for (int k = 0; k < 8; k++) {
				int nx = currX + di[k];
				int ny = currY + dj[k];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == '.' && numBoard[nx][ny] == -1) {
					// 지뢰가 몇 개 있는지 세기
					int mine = checkMine(nx, ny);
					numBoard[nx][ny] = mine;
//					System.out.println("큐: "+ nx + ny);
//					System.out.println("mine: " + mine);
					if (mine == 0) {
						q.offer(new int[] { nx, ny });
					}
				}
			}
//			System.out.println("bfs");
		}
	}

	private static int checkMine(int x, int y) {
		int count = 0;
		for (int k = 0; k < 8; k++) {
			int nx = x + di[k];
			int ny = y + dj[k];
//			System.out.println("nx : " + nx + "ny : " + ny);
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == '*') {
				count += 1;
//				System.out.println("count");
			}
		}
		return count;
	}

	private static boolean checkZeroMine(int x, int y) {
		boolean isSafe = true;
		for (int k = 0; k < 8; k++) {
			int nx = x + di[k];
			int ny = y + dj[k];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == '*') {
				isSafe = false;
			}
		}
		return isSafe;
	}
}