

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] lectures = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			lectures[i][0] = S;
			lectures[i][1] = T;
		}
		
		Arrays.sort(lectures, (a, b) -> a[0] - b[0]);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.offer(lectures[0][1]);
		
		for (int i = 1; i < N; i++ ) {
			//다음 강의의 시작시간과 종료시
			int start = lectures[i][0];
			int end = lectures[i][1];
			
			//현재 강의의 종료시간 <= 다음 강의의 시작
			if (pq.peek() <= start) {
				pq.poll(); //겹치지 않기 때문에 큐에서 빼버
			}
			//다음강의의 종료 시간을 넣
			pq.add(end);
		}
		
		//큐의 크기 구하기  
		System.out.println(pq.size());
		

	}

}
