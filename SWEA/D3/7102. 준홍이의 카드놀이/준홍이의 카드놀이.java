
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();

			// 이 문제를 꼭 queue로 풀어야 하나?
			Queue<Integer> q1 = new LinkedList<>();
			Queue<Integer> q2 = new LinkedList<>();

			for (int i = 1; i <= N; i++) {
				q1.offer(i);
			}
			for (int i = 1; i <= M; i++) {
				q2.offer(i);
			}

			// hash map을 써야 하나
			Map<Integer, Integer> map = new HashMap<>();

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					int sum = i + j;
					// 이미 존재한다면 + 1
					if (map.get(sum) != null) {
						// 같은 key로 put하면 value가 덮어쓰기 된다.
						int count = map.get(sum) + 1;
						map.put(sum, count);
					}

					// 처음 들어간다면 1로 초기화해서 넣어주기
					else {
						map.put(sum, 1);
					}
				}
			}

			// value 값이 가장 큰 것을 찾아야한다.
			// 여러 개가 나올 수 있으니 리스트로
			List<Integer> list = new ArrayList<>();

			// 최대값을 찾기
			int maxValue = 0;

			for (Map.Entry<Integer, Integer> entry : map.entrySet()) { // 맵의 최대 value 값 찾기 -> 정리하기
				if (entry.getValue() > maxValue) {
					maxValue = entry.getValue();
				}
			}

			// 해당 최댓값을 갖고 있는 수 찾기
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				if (entry.getValue() == maxValue) {
					list.add(entry.getKey());
				}
			}

//			System.out.println(list);

			// 리스트를 오름차순 정렬
			Collections.sort(list);

			System.out.print("#" + tc + " ");

			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();

		}
	}
}
