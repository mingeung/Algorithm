
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution{
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()); // 정점의 개수
			int M = Integer.parseInt(st.nextToken()); // 간선의 개수

			// 부모배열을 초기화 해준다.
			int[] parent = makeSet(N);

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(parent, a, b);
			}
			//최종적으로 몇 개의 무리가 있는지 세기
			
			Set<Integer> set = new HashSet<>();
			
			for (int i = 1; i <=N; i++) {
				set.add(findSet(parent, parent[i]));
			}
			
			int ans = set.size();
			
			System.out.println("#" + tc + " " + ans);

		}

	}

	private static void union(int[] parent, int x, int y) {
		int root_x = findSet(parent, x);
		int root_y = findSet(parent, y);

		// 두 개의 부모 노드가 다르면
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

	static int[] makeSet(int n) {
		int[] result = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			result[i] = i;
		}
		return result;

	}

}
