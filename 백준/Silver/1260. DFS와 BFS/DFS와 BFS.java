

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static StringBuilder bfsResult = new StringBuilder();
	static StringBuilder dfsResult = new StringBuilder();

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 정점의 개수
		int M = sc.nextInt(); // 간선의 개수
		int V = sc.nextInt(); // 탐색을 시작할 정점 번호

		// 우선 그래프를 생성한다.
		graph = new ArrayList[N + 1];
		// 각 인덱스에도 arrayList를 추가한다.

		for (int i = 0; i < N + 1; i++) {
//			graph[i].addAll(new ArrayList<Integer>()); -> 틀린 예
			// 그래프를 추가해줄 때 add가 아니라 아래처럼 추가해줌
			graph[i] = new ArrayList<>();
		}


		// 간선 입력받기
		for (int i = 0; i < M; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();

			graph[node1].add(node2);
			graph[node2].add(node1);
		}

		// 작은 것부터 방문할 수 있게 오름차순 정렬을 해준다.
		for (int i = 0; i < N + 1; i++) {
			Collections.sort(graph[i]);
		}

		// dfs 실행
		visited = new boolean[N + 1]; // 리스트가 아니라 배열로 만들어주면 된다.
		dfs(V);
		System.out.println(dfsResult.toString().trim());

		// bfs 실행
		visited = new boolean[N + 1]; // 리스트가 아니라 배열로 만들어주면 된다.
		bfs(V);
		System.out.println(bfsResult.toString().trim());

	}

	// 깊이 탐색 - 재귀 사용
	private static void dfs(int node) {
		// 방문처리
		visited[node] = true;
		dfsResult.append(node).append(" "); // 방문순서에 기록

		for (int next : graph[node]) {
			if (visited[next] == false) { // 아직 방문하지 않았다면 재귀
				dfs(next);

			}
		}

	}

	// 넓이탐색 - 큐 사용
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.offer(start);

		while (!queue.isEmpty()) {
			int node = queue.poll();
			visited[node] = true;

			bfsResult.append(node).append(" ");

			for (int next : graph[node]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.offer(next);

				}
			}
		}
	}

}
