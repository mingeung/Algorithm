import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//최단거리를 구하기 위해서는 거리 배열을 따로 두는 것이 좋다. 
public class Main {

	static boolean[][] visited;
	static int count;
	static int[] node;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int[][] arr;
	static int N;
	static int M;
 

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		// 최단거리문제는 bfs로 푸는 게 좋다.
		// dfs는 최소거리를 바로 보장하지 않는다.
		// bfs는 가까운 칸부터 순서대로 탐색하기 때문에 처음 도착한 순간의 거리가 최소 거리가 된다.

		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] row = sc.next().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(row[j]);
			}
		}
		

		// bfs 전에 방문배열과 count 초기화
		visited = new boolean[N][M];
		// 시작위치는 0,0
		int[] start = { 0, 0 };
		bfs(start); // 배열로 넘겨야 하나?

		System.out.println(count);
	}

	private static void bfs(int[] start) {
		// 큐 생성
		Queue<int[]> queue = new LinkedList<>();
		int[][] dist = new int[N][M]; //거리 저장 배열
		dist[start[0]][start[1]] = 1; //시작칸 거리 = 1
		
		// 큐에 초기값 넣고 방문처리
		queue.offer(start);
		visited[start[0]][start[1]] = true;

		while (!queue.isEmpty()) {
			node = queue.poll();

			//도착하면 while문을 끝낸다.
			if (node[0] == N - 1 && node[1] == M -1) {
				//여기서 거리배열에서 거리를 꺼낸다
				count = dist[node[0]][node[1]];
				break;
			}
			
			visited[node[0]][node[1]] = true;

			// 상하좌우에 이동할 수 있는지 확인
			for (int k = 0; k < 4; k++) {
				//범위를 벗어나는지 확인해야
				int ni = node[0] + di[k];
				int nj = node[1] + dj[k];
				
				if (ni >= N ||  ni < 0 || nj >= M || nj < 0) {
					continue;
				}
				if (arr[ni][nj] == 1 && (!visited[ni][nj])) {
					//길이 있고 이미 방문한 곳이 아니라면 큐에 추가하기
					//방문처리해주기
					visited[ni][nj] = true;
					//거리 배열 업데이트하기
					dist[ni][nj] = dist[node[0]][node[1]] + 1;
					
					queue.offer(new int[] {ni, nj});
					
				}
			}
		}

	}

}


