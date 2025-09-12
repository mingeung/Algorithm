

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int to, time;

		public Edge(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return this.time - o.time;
		}
//
	}

	static int n, d, c, a, b, s;
	static List<Edge>[] graph;
	static int[] dist;
	static final int INF = 600_000_000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()); // 의존성 개수
			c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호

			graph = new ArrayList[n + 1];
			for (int i = 1; i < n + 1; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken()); //
				b = Integer.parseInt(st.nextToken()); // a가 b를 의존하고 s초 이후에
				s = Integer.parseInt(st.nextToken());

				graph[b].add(new Edge(a, s));
			}

			dijkstra(c);
		}
	}

	
	private static void dijkstra(int start) {
		// 다익스트라 준비과정
		// 우선순위 큐 생성
		// dist 배열 생성 및 무한대로
		// 지금 위치는 0으로
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist = new int[n + 1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		pq.offer(new Edge(start, 0)); // 이렇게 넣으면 된다.

		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();

			if (curr.time > dist[curr.to]) {
				continue;
			}


			// 다음을 찾자...
			for (Edge e : graph[curr.to]) {
				if (dist[e.to] > dist[curr.to] + e.time) {
					dist[e.to] = dist[curr.to] + e.time;
					pq.offer(new Edge(e.to, dist[e.to])); // pq에 잘못 넣은 것 같은데
				}
			}
		}
		//감염시간은 dist를 돌면서 최댓값을 찾아야 한다.
		int count= 0 ;
		int maxTime = 0;
		for (int i = 1; i < n + 1; i++) {
			if (dist[i] != INF) {
				count += 1;
				maxTime = Math.max(maxTime, dist[i]);
				
			}
		}

		System.out.println(count + " " + maxTime);

	}
}
