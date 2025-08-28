

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	// 객체 생성하기 - 어떻게 하는지 기억하기
	static class Edge implements Comparable<Edge> {
		int u; // private 안 적어도 된다.
		int v;
		int w;

		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) { // 대소문자 조심
			return this.w - o.w; // 가중치 비교
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			PriorityQueue<Edge> pq = new PriorityQueue<>();

			for (int i = 0; i < E; i++) {
				// 큐에 입력값 저장
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				Edge node = new Edge(u, v, w);
				pq.offer(node);
			}

			// 부모노드 생성
			int[] parent = new int[V + 1];

			for (int i = 1; i < V + 1; i++) {
				parent[i] = i; // 초기에는 자기자신의 부모
			}

			long minW = 0;
			//edgeCount는 왜 필요한거지 ?
			int edgeCount = 0;
			
			// 큐에서 하나씩 빼서 값 확인
			while (!pq.isEmpty() && edgeCount < V - 1) {
				Edge cur = pq.poll();

				// 두 개의 부모가 같은지 확인
				// 부모가 같지 않다면 사이클이 돌지 않는 것이다. 추가하기
				int rootU = find(parent, cur.u);
				int rootV = find(parent, cur.v);

				if (rootU != rootV) {
					minW += cur.w;
					union(parent, rootU, rootV); // 합칠 때 그냥 node가 아니라 부모 node를 넘겨줘야 함.
					edgeCount++;
				}

				// 부모가 같다고 해서 break문을 넣지 않는다. 다음 큐를 돌아야 하기 때문에
			}

			System.out.println("#" + tc + " " + minW);
		}
	}

	// 두 트리를 합치기
	public static void union(int[] parent, int rootU, int rootV) {
//		if (rootU > rootV) {
//			parent[rootV] = rootU;
//		} else {
//			parent[rootU] = rootV;
//		}
		if (rootU != rootV) {
			parent[rootV] = rootU;
		}
	}

	private static int find(int[] parent, int x) {
		if (parent[x] != x) {
			parent[x] = find(parent, parent[x]); // 최상단 부모를 찾기
		}
		return parent[x];

	}

}
