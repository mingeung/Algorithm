

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		// 제일 앞에 있는 숫자를 빼서, 가장 뒤에 넣는 것이니 선입선출(FIFO) 방식의 queue를 쓰는 게 좋다.

		// 총 10개의 테스트케이스가 주어진다.
		for (int T = 1; T <= 10; T++) {

			// 큐 생성
			Queue<Integer> q = new LinkedList<>();

			// 테스트케이스 번호 입력받기
			int tc = sc.nextInt();

			// 큐에 각 암호 입력받기
			for (int i = 0; i < 8; i++) {
				q.offer(sc.nextInt());
			}

			// 감소시킬 숫자 1로 초기화
			int count = 1;

			// while로 무한루프 돌리기
			while (true) {
				// 큐의 가장 앞에 있는 숫자를 꺼내서
				int target = q.poll();
				// count만큼 감소시키기
				target -= count;

				// 만약 감소시킨 수가 0보다 같거나 작아지는 경우는 0으로 유지되며 프로그램은 종료된다.
				if (target <= 0) {
					target = 0;
					q.offer(target);
					break;
				}

				// 종료되는 경우가 아니라면 큐의 가장 뒤에 target값을 추가한다.
				q.offer(target);

				// 감소되는 숫자는 1씩 작아져야 하므로 +1을 해준다.
				count += 1;

				// count가 5를 넘어가면 한 cycle을 돈 것이기 때문에 count 값을 1로 초기화시킨다.
				if (count == 6) {
					count = 1;
				}
			}

			// 출력로직
			System.out.print("#" + tc + " ");
			// for문으로 queue에서 poll로 값을 빼내면서 출력한다.
			while (!q.isEmpty()) {
				System.out.print(q.poll() + " ");
			}

			System.out.println();
		}
	}

}
