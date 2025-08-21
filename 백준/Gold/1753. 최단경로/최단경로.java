

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//이건 BFS 문제가 아니라 다익스트라 알고리즘 문제이다. 가중치가 있는 경우에는 다익스트라 알고리즘을 사용해야 한다. 
//다익스트라 알고리즘 - 우선순위 큐 사용, 각 정점까지 최소 거리를 저장하는 배열 dist[]사

public class Main {
	//도착 정점과 가중치를 담을 클래스를 생성한다.
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {return this.weight - o.weight;}
		
	}
	
	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt(); //정점의 개수
		int E = sc.nextInt(); //간선의 개수
		int K = sc.nextInt(); //시작 정점의 번호 -> 이거는 어디에 쓰는 거지...? 출력할 때라고 생각 
		
		//그래프를 ArrayList로 생성하기
		List<Edge>[] graph = new ArrayList[V + 1];		
		//정점의 개수만큼 내부 리스트 생성하
		for (int i = 0; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
				
		
		for (int i = 0; i <E; i++) {
			int u = sc.nextInt(); //시작 저점
			int v = sc.nextInt(); //도착정점
			int w = sc.nextInt(); //가중치
			
			//그래프에 간선 추가하기
			graph[u].add(new Edge(v, w));
		}
		int[] dist = new int[V +1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(K, 0));
		
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			if (curr.weight > dist[curr.to]) continue; //가지기
			
			for (Edge e : graph[curr.to]) {
				if (dist[e.to] > dist[curr.to] + e.weight) { //현재까지 알고 있는 최단거리보다 curr를 거쳐가는 초단거리가 짧으면 갱
					dist[e.to] = dist[curr.to] + e.weight;
					pq.offer(new Edge(e.to, dist[e.to]));
				}
			}
		}
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.print(sb);
		
	}
}
/*
2️⃣ 메모리 구조 예시

예를 들어 정점 1, 2, 3, 4 가 있고 간선이 있다면:

graph[1] -> [Edge(to=2, weight=3), Edge(to=3, weight=5)]
graph[2] -> [Edge(to=4, weight=2)]
graph[3] -> []
graph[4] -> [Edge(to=1, weight=4)]

3️⃣ 생성 방법

배열만 선언했다고 실제 리스트가 생성되는 건 아님. 반드시 내부 리스트도 생성해야 함:

for (int i = 0; i <= V; i++) {
    graph[i] = new ArrayList<>();
}
*/