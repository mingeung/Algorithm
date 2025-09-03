
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
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 그래프 뭘로 만들더라..? ArrayList?

			int N = Integer.parseInt(st.nextToken()); // 반 학생 수
			int M = Integer.parseInt(st.nextToken()); // 친한 친구의 수

			boolean[] visited = new boolean[N + 1];

			// 아래 두 줄 매우 중요 -> 하지만 난 또 잊겠지...
			List<Integer>[] friends = new ArrayList[N + 1];

			for (int i = 0; i < N + 1; i++) {
				friends[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				friends[a].add(b); // 이런 식으로 추가하는구나. 알아두기
				friends[b].add(a);
			}

			// 상원이가 초대장을 줄 친구 수
			int bestFri = 0;

			// 큐를 돌려야 하나?
			Queue<Integer> q = new LinkedList<>();

			// 일단 큐에 상원이를 담아
			q.offer(1);
			visited[1] = true;

			int depth = 0;

			//2개의 depth만 처리하는 방법
			while (!q.isEmpty()) {
				
				if (depth == 2) {
					break;
				}
				
				//핵심 로직 부분
				int size = q.size();
				
				//현재 수만큼 반복
				for (int i = 0; i < size; i++) {
					
					int person = q.poll();


				for (int j = 0; j < friends[person].size(); j++) {
					int invite = friends[person].get(j);
					if (visited[invite]) {
						continue;
					}
					visited[invite] = true;
					bestFri += 1;
					q.offer(invite);
				}
			}
				depth++;
			}
			System.out.println("#" + tc + " " + bestFri);
		}
	}
}