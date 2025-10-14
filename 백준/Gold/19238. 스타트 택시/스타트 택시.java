import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Customer {
	int[] depart;
	int[] dest;
	int path;
	boolean arrived;

	public Customer(int[] depart, int[] dest, int path, boolean arrived) {
		super();
		this.depart = depart;
		this.dest = dest;
		this.path = path;
		this.arrived = arrived;
	}

}
//매개변수 생성자를 추가해주어야 한다

public class Main {

	static int N, M, oil; // M은 승객의 명수
	static int[][] map;
	static int[] taxi;
	static List<Customer> customers;
	static int minPath, minRow, minCol, minIdx;
	static boolean end;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int minDistance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oil = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}
//		// 택시 시작 위치
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		taxi = new int[] { x - 1, y - 1 };

		// 손님의 위치 객체로 저장하기
		// 손님의 위치 배열
		customers = new ArrayList<>(M);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			// 객체 생성할 때 중괄호 말고 소괄호를 써야 한다.
			Customer cus = new Customer(new int[] { x1 - 1, y1 - 1 }, new int[] { x2 - 1, y2 -1 }, 0, false);
			customers.add(cus);

		}

		for (int i = 0; i < M; i++) {
			end = false; // 초기화
			// 1) 이번에 픽업할 손님을 고르는 메서드
			int custIdx = choose();
			if (end) {
				break;
			}
//			 2) 손님 픽업하기
			pickUp(custIdx);
			if (end) {
				break;
			}
//			// 손님을 목적지까지 이동하기.
			dropDown(custIdx);
			if (end) {
				break;
			}
		}

		if (end) {
			System.out.println(-1);
		} else {
			System.out.println(oil);
		}

	}

	// 손님 픽업 함수
	private static void pickUp(int custIdx) {

		int targetPath = 0;
		int targetX = 0;
		int targetY = 0;

		// 택시 위치에서 손님 depart까지의 거리
		targetX = customers.get(custIdx).depart[0];
		targetY = customers.get(custIdx).depart[1];

		targetPath = distance(taxi[0], taxi[1], targetX, targetY);

		if (targetPath == -1 || oil - targetPath < 0) {
		    end = true;
		    return;
		}
		oil -= targetPath;
		taxi[0] = targetX;
		taxi[1] = targetY;
	}

	// 손님 데려다 주기 함수

	private static void dropDown(int custIdx) {

		int departX = customers.get(custIdx).depart[0];
		int departY = customers.get(custIdx).depart[1];

		int destX = customers.get(custIdx).dest[0];
		int destY = customers.get(custIdx).dest[1];

		int dist = distance(taxi[0], taxi[1], destX, destY);
		if (dist == -1 || oil - dist < 0) { // 도달 불가 또는 연료 부족
		    end = true;
		    return;
		}
		oil = oil - dist + dist * 2;
		taxi[0] = destX;
		taxi[1] = destY;

	}

	private static int choose() {
		minPath = Integer.MAX_VALUE;
		minIdx = -1;
		// 객체를 돌기
		for (int i = 0; i < M; i++) {
			Customer cus = customers.get(i);

			if (cus.arrived) { // 이미 처리한 손님이면 pass
				continue;
			}
			int p = distance(taxi[0], taxi[1], cus.depart[0], cus.depart[1]); // 거리를 구하기
			if (p == -1) continue; 
			if (p < minPath) {
				minPath = p;
				minRow = cus.depart[0];
				minCol = cus.depart[1];
				minIdx = i;
			} else if (p == minPath) {
			    if (cus.depart[0] < minRow || (cus.depart[0] == minRow && cus.depart[1] < minCol)) {
			        minRow = cus.depart[0];
			        minCol = cus.depart[1];
			        minIdx = i;
			    }
			}


		}
		//모든 손님 접근 불가
		if( minIdx == -1) {
			end = true;
			return -1;
		}
		// 손님을 태운 거 처리하기
		customers.get(minIdx).arrived = true;

		return minIdx;
	}

	private static int distance(int startX, int startY, int targetX, int targetY) {
	    boolean[][] visited = new boolean[N][N];
	    Queue<int[]> q = new LinkedList<>();
	    q.offer(new int[]{startX, startY, 0}); // {x, y, depth}
	    visited[startX][startY] = true;

	    while (!q.isEmpty()) {
	        int[] cur = q.poll();
	        int x = cur[0];
	        int y = cur[1];
	        int d = cur[2];

	        // 목적지 도착
	        if (x == targetX && y == targetY) {
	            return d;
	        }

	        for (int k = 0; k < 4; k++) {
	            int nx = x + di[k];
	            int ny = y + dj[k];

	            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && !visited[nx][ny]) {
	                visited[nx][ny] = true;
	                q.offer(new int[]{nx, ny, d + 1});
	            }
	        }
	    }

	    // 도달 불가
	    return -1;
	}

}
