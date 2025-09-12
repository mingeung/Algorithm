

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

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			System.out.print("#" + tc + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			// 인접리스트로 만들기 -> 배열로 만들어야 한다. (기억하기)
			List<Integer>[] adjList = new ArrayList[V + 1];
			for (int i = 1; i < V + 1; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			int[] inDegree = new int[V + 1];
			Queue<Integer> q = new LinkedList<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i += 1) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjList[from].add(to);
				inDegree[to]++;
			}

			for (int i = 1; i < V + 1; i++) {
				if (inDegree[i] == 0) {
					q.offer(i);
				}
			}
			while (!q.isEmpty()) {
				int curr = q.poll();
				System.out.print(curr + " ");
				for(int next : adjList[curr]) {
					inDegree[next]--;
					if (inDegree[next] == 0) {
						q.offer(next);
					}
				}
			}
			System.out.println();
		}
	}
}

//<위상정렬 큐로 만드는 방법>
//정답배열 적기
//인접 행렬 or 리스트 만들기
//진입차수 배열 만들기
//진입차수가 0이면 큐에 삽입하기
//큐에서 빼고 연결되어 있는 곳에 진입차수 -1 빼기
//0이면 다시 진입차수에 넣기 