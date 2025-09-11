

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//프림 알고리즘
//우선순위 큐로 구현

public class Main {

	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	static List<Edge>[] edges;

	public static void main(String[] args) throws IOException {
		// 마을을 어떻게 분할할 것인가
		// 일단 최소비용으로 연결을 다 해 -> 최소신장 트리 만들기 -> 프림 알고리즘으로 ㄱㄱ
		// 그 중에서 비용이 가장 큰 걸 없애

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		edges = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i ++) {
			edges[i] = new ArrayList<>();
		}
		

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 양방향
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
		}
		
		//프림을 돌리기 위해서 필요한 것들
		//우선수위 큐를 쓰니까 여기서의 로직은 bfs와 비슷하다고 생각하면 되겠다. 
		boolean[] visited =new boolean[N + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int pick = 0; //N -1개 뽑으면 멈출 수 있게
		//처음은 방문처리
		visited[1] = true;
		List<Integer> costs = new ArrayList<>();
		
		for (Edge e : edges[1]) {
			pq.add(e);
		}
		
		while (pick < N -1) {
			Edge e = pq.poll();
			if (visited[e.to]) {
				continue;
			}
			visited[e.to] = true;
			pick++;
			costs.add(e.cost);
			
			pq.addAll(edges[e.to]);
		}
		
		int maxCost = Collections.max(costs);
		int totalCost = 0;
		
		for (int i = 0; i < costs.size(); i++) {
			totalCost += costs.get(i);
		}
		int ans = totalCost - maxCost;
		
		System.out.println(ans);
	}
}
