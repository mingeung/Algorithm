

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		// 0이 될 때 종료가 됨
		for (int T = 1; T <= 10; T++) {

		// 큐 생성
		Queue<Integer> q = new LinkedList<>();

		int tc = sc.nextInt();

		// 데이터를 담을 변수

		for (int i = 0; i < 8; i++) {
			q.offer(sc.nextInt());
		}

		int count = 1;


		// queue에서 맨 앞에 것을 poll해서 1을 감소하고 뒤로 보낸다
		while (true) {

			int target = q.poll() - count;
			// target이 0이하이면 0으로 저장되고 정지됨
			if (target <= 0) {
				target = 0;
				q.offer(target);
				break;
			}

			q.offer(target);

			count += 1;
			// count가 5를 넘어가면 초기화시킨다.
			if (count == 6) {
				count = 1;
			}
		}
		
		System.out.print("#" + tc + " ");
		for (int i = 0; i < 8; i++) {
			System.out.print(q.poll() + " ");
		}

		System.out.println();
		}
	}

}
