
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			System.out.print("#" + tc + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 인접리스트 만들기
			List<Integer>[] adjList = new ArrayList[N + 1];
			int[] inDegree = new int[N + 1];
			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i < N + 1; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 1; i < M + 1; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjList[from].add(to);
				inDegree[to]++;
			}

			// 진입차수가 0인 것이 있다면 큐에 넣기
			for (int i = 1; i < N + 1; i++) {
				if (inDegree[i] == 0) {
					q.offer(i);
				}
			}

			while (!q.isEmpty()) {
				int curr = q.poll();
				System.out.print(curr + " ");

				for (int next : adjList[curr]) {
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
