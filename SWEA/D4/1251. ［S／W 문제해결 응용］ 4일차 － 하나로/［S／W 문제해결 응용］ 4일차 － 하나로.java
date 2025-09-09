

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static class Edge implements Comparable<Edge> { // Comparable<Edge> 라는 것을 기억하기
		int from, to;

		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight); // compareTo는 int를 반환해야 함. double로 하려면 이렇게 해야됨
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[] x = new int[N];
			int[] y = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}

			// br.readline을 double로 저장하는 방법
			double E = Double.parseDouble(br.readLine());

			// 1. 가중치 계산해서 그걸로 Edge 만들고
			// 2. 크루스칼 알고리즘으로 연결지점 만들고
			// 3. 그게 하나의 무리인지 확인? -> 필요없다. 크루스칼 알고리즘을 거치면 하나의 무리가 되는 것이다.

			List<Edge> graph = new ArrayList<>();

			for (int i = 0; i < N - 1; i++) {
				int start_x = x[i];
				int start_y = y[i];
				for (int j = i + 1; j < N; j++) { // j의 시작점을 i + 1로 설정해서 중복되지 않게
					int arrive_x = x[j];
					int arrive_y = y[j];

					long diff_x = arrive_x - start_x;
					long diff_y = arrive_y - start_y;
					double cost = 0;

					cost = (diff_x * diff_x) + (diff_y * diff_y);
					cost *= E;

					graph.add(new Edge(i, j, cost));
				}
			}

			//정렬한번 진행해? 자동으로 정렬되는 거 아니었음?
			Collections.sort(graph);
			
			
			// 크루스칼 알고리즘으로 연결 지점 만들기
			// 일단 부모배열 만들어
			int[] parent = makeSet(N);
			double totalCost = 0.0; //totalCost랑 ans 자료형을 일치시켜야 함(double, long) 그렇지 않으면 답이 크게 왜곡될 수 있다. 
			int pick = 0; // pick이 N-1개가 될 때까지 반복
			// 그리고 union을 만들어
			
			
			for (int i = 0; i < graph.size(); i++) {
				int u = graph.get(i).from;
				int v = graph.get(i).to;
				double w = graph.get(i).weight;

				//!중요한 부분 : 사이클이 형성되지 않는 경우에만 untion 및 비용 추가
				if (findSet(parent, u) != findSet(parent, v)) {
					totalCost += w;
					unionSet(parent, u, v);
					pick += 1;

					if (pick == N - 1) {
						break;
					}
					
				}
				
			}
			// double을 소수 첫째 자리에서 반올림하여 정수로 출력하는 방법
			long ans = Math.round(totalCost);
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void unionSet(int[] parent, int x, int y) {
		int root_x = findSet(parent, x);
		int root_y = findSet(parent, y);

		if (root_x != root_y) {
			parent[root_y] = root_x;
		}

	}

	private static int findSet(int[] parent, int x) {
		if (parent[x] == x) {
			return x;
		}
		return findSet(parent, parent[x]);
	}

	private static int[] makeSet(int n) {
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = i;
		}

		return result;
	}
}
