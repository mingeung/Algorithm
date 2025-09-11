import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	static List<Edge> edges; // 간선 배열 초기화하는 거 -> 체크!
	static long[] dist;
	static final long INF =  600000000L; //무한대로 설정하면 더하기에서 오버플로우 발생 위험
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 음수 사이클이 나오거나 경로가 없으면 -1을 출력

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		dist = new long[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges.add(new Edge(from, to, cost));
		}

		bellmanford(1);

	}

	private static void bellmanford(int start) {
		// dist배열 초기화
		Arrays.fill(dist, INF);

		// 시작점을 0으로 초기화
		dist[start] = 0;

		// N - 1번만큼 진행
		for (int i = 0; i < N - 1; i++) {
			boolean flag = false;

			for (Edge e : edges) {
				// 최소거리를 확인 후 갱신
				if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.cost) // 이 조건문이 헷갈려
				{
					dist[e.to] = dist[e.from] + e.cost;
					flag = true;

				}
			}
			if (!flag)
				break;
		}

		// 음수사이클 확인 -> 다시 정리하기
		boolean negativeCycle = false;

		for (Edge e : edges) {
			// 음수 사이클 조건이 뭐지? -> N-1번 이후에도 더 갱신된다면 음수 사이클 존재
			if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.cost) {
				negativeCycle = true;
				break;
			}
		}

		if (negativeCycle) {
			System.out.println(-1);
		} else {
			for (int i = 2; i < N + 1; i++) {
				if (dist[i] == INF) {
					System.out.println(-1);
				} else {

					System.out.println(dist[i]);
				}

			}

		}
	}
}