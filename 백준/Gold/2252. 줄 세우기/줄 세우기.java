

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		//진입차수를 넣을 배열 만들기
		int[] inDegrees = new int[N +1];
		
		//연결된 간선을 담을 linkedList 만들
		//아직도 헷갈리는 인접리스트 구현하는법... 
		LinkedList<Integer>[] map= new LinkedList[N+1];
		for (int i = 0;  i < N + 1; i++) {
			//인덱스 번호로 접근해서 배열을 추가해야
			map[i] = new LinkedList<>();
			
		}
		
		for (int i = 0; i < M; i++) {
			int pre = sc.nextInt();
			int next = sc.nextInt();
			
			//연결된 간선 추가 
			map[pre].add(next);
			
			//진입차수 추가
			inDegrees[next] += 1;
		}
		
		//큐 생성
		Queue<Integer> q = new LinkedList<>();
		
		//진입차수가 0인 것들 큐에 넣기
		for (int i = 1; i < N +1; i++) {
			if (inDegrees[i] == 0) {
				q.offer(i);
			}
		}
		
		
		List<Integer> answer = new ArrayList<>();
		
		//큐에서 하나씩 빼면서 진입차수 0인 거 정답 리스트에 추가하기
		while(!q.isEmpty()) {
			int curr = q.poll();
			answer.add(curr);
			
			//연결된 리스트의 진입차수를 -1 처리하기
			for (int i = 0; i < map[curr].size(); i++) {
				int next = map[curr].get(i);
				inDegrees[next] -= 1;
				if (inDegrees[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		for (int i = 0; i < answer.size(); i++) {
			System.out.print(answer.get(i) + " ");
		}
	}
}
