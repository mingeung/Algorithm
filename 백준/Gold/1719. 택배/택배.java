import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 이차원 배열 생성
		int[][] board = new int[n + 1][n + 1];

		// 첫번째로 거쳐가야 하는 부분
		int[][] fBoard = new int[n + 1][n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			// 양방향 간선
			board[from][to] = w;
			board[to][from] = w;

			fBoard[from][to] = to;
			fBoard[to][from] = from;

		}

		// 2차원 배열 상에서 최소 경로를 어덯게 찾을까?
		// 플로이드 워셜로 하면 잘 구할 수 있을 것 같음

		// 가중치가 아니었구나

		// 중간 다리
		for (int i = 1; i < n + 1; i++) {
			for (int from = 1; from < n + 1; from++) {
				if (from == i)
					continue;
				for (int to = 1; to < n + 1; to++) {
					if (from == to)
						continue;

					// 연결되어 있는 경우만 고려
					if (board[from][i] > 0 && board[i][to] > 0) {
						// 직접 연결이 되지 않았다면 새로운 걸로 업데이트
						if (board[from][to] == 0) {
							board[from][to] = board[from][i] + board[i][to];
							
							//경로를 갱신하는 과저에서 오류
							//아래처럼 쓰면 안 된다. 처음으로 거쳐야 하는 노드를 의미하지 않기 때문이다.
							fBoard[from][to] = i;
							
							//그래서 아래처럼 작성해준다. 
							fBoard[from][to] = fBoard[from][i];
						}

						// 직접 가는 것보다 중간다리를 통해서 가는 게 더 빠르다면
						else if (board[from][to] > board[from][i] + board[i][to]) {

							board[from][to] = board[from][i] + board[i][to];
							fBoard[from][to] = fBoard[from][i];
						}



					}
				}

			}
		}

		// 출력하기
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (i == j) {
					System.out.print("-");
				} else {
					System.out.print(fBoard[i][j]);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();


	}

}
