
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	static List<String> pw;

	public static void main(String[] args)  {


		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			// 큐로 만들어서 하는 게 좋을 것 같다.

			int N = sc.nextInt(); // 숫자의 개수
			int K = sc.nextInt(); // 비밀번호 - K번째로 큰 수를 10진수로 바꾼 것

			pw = new ArrayList<>();

			Deque<Character> q1 = new LinkedList<>();
			Deque<Character> q2 = new LinkedList<>();
			Deque<Character> q3 = new LinkedList<>();
			Deque<Character> q4 = new LinkedList<>();

			String str = sc.next();

//			System.out.println(str);

			// 4개로 나눠서 큐에 담기
			int partLen = N / 4;

			for (int i = 0; i < partLen; i++) {
				q1.offer(str.charAt(i));
			}
			for (int i = partLen; i < partLen * 2; i++) {
				q2.offer(str.charAt(i));
			}
			for (int i = partLen * 2; i < partLen * 3; i++) {
				q3.offer(str.charAt(i));
			}
			for (int i = partLen * 3; i < partLen * 4; i++) {
				q4.offer(str.charAt(i));
			}

			// 이 문제는 큐랑 조금 다른 점이 맨 뒤에 있는 걸 꺼내서 맨 앞에 넣어야 한다. -> 양방향이 가능한 Deque을 사용한다.

			// 회전은 몇 번하는가? partLen - 1 만큼한다.
			for (int i = 0; i < partLen; i++) {
//				System.out.println("q1: " + q1);
//				System.out.println("q2: " + q2);
//				System.out.println("q3: " + q3);
//				System.out.println("q4: " + q4);
				saveInQ(q1);
				saveInQ(q2);
				saveInQ(q3);
				saveInQ(q4);

				char ch1 = q1.pollLast();
				q2.offerFirst(ch1);

				char ch2 = q2.pollLast();
				q3.offerFirst(ch2);

				char ch3 = q3.pollLast();
				q4.offerFirst(ch3);

				char ch4 = q4.pollLast();
				q1.offerFirst(ch4);

			}

			// 저장이 다 되면 내림차순으로 정렬하고 K번째로 큰 수를 구한다.
			// 중복을 제거할 거니까 HashSet을 사용한다.
			Set<String> set = new HashSet<>(pw);

			// 다시 리스트로 변환
			List<String> uniquePw = new ArrayList<>(set);

			// 16진수 값 기준 내림차순 정렬
			uniquePw.sort((a, b) -> {
				int valA = Integer.parseInt(a, 16);
				int valB = Integer.parseInt(b, 16);
				return Integer.compare(valB, valA);

			});
			String hx = uniquePw.get(K - 1);

			int demical = Integer.parseInt(hx, 16);
			
			System.out.println("#" + tc + " " + demical);

		}

	}

	private static void saveInQ(Queue<Character> q) {
		// 각 큐를 pw에 저장한다.
		StringBuilder sb = new StringBuilder();
		for (char ch : q) {
			sb.append(ch);
		}
		String result = sb.toString();
		pw.add(result);
//		System.out.println("pw:" + pw);

	}

}
