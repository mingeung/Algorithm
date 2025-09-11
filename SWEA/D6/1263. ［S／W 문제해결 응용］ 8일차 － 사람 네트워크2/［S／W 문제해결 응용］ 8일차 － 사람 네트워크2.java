
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드 워셜 알고리즘
public class Solution {
	static int N;
	static int[][] graph;
	static int[][] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// 일단 입력값을 2차원 배열로 만들기

			//두 개를 더하게 되면 오버플로우가 발생해서 maxValue로 하지 말어라
			final int INF = 1_000_000;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			// 플로이드 워셜 알고리즘
			dist = new int[N][N];

			// dist에 입력값 업데이트

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int input = Integer.parseInt(st.nextToken());
					//자기자신은 0으로 초기화해야 한다. -> 이 부분 빼먹었음!!!
					if (i == j) {
						dist[i][j] = 0;
					}
					
					else if (input == 1) {
						dist[i][j]= input;
					} else {
						dist[i][j] = INF;
					}
				}
			}
//
			// 삼중 for문 돌리기
			for (int k = 0; k < N; k++) {// 경유지
				for (int from = 0; from < N; from++) {
					if (dist[from][k] == INF)
						continue; // 근데 처음에는 다 무한대로 설정했는데 이게 되려나?
					for (int to = 0; to < N; to++) {
						if (dist[k][to] == INF)
							continue;
						// 거리 비교
						if (dist[from][to] > dist[from][k] + dist[k][to]) {
							dist[from][to] = dist[from][k] + dist[k][to];
						}
					}
				}
			}
			int minPath = INF;
			// 출력값을 뭘로 해야할까?
			for (int i = 0; i < N; i++) {
				int sumPath = 0;
				for (int j = 0; j < N; j++) {
					sumPath += dist[i][j];
				}
				minPath = Math.min(sumPath, minPath);
			}
			System.out.println("#" + tc + " " + minPath);
		}
	}
}
