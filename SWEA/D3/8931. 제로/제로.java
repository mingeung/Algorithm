

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int K = sc.nextInt();

			Stack<Integer> stack = new Stack<>();

			for (int i = 0; i < K; i++) {
				int N = sc.nextInt();
				if (N == 0) { // N이 0이라면 수를 지워야 한다.
					stack.pop();
//					System.out.println("지우는 수" + stack.pop());
				} else { // N이 0이 아니라면 스택에 수를 넣는다.
					stack.push(N);
//					System.out.println("넣는 수" + stack.push(N));

				}
			}
			// stack의 합을 구하기
			int sum = 0;
//			System.out.println("stack:" + stack);
//			System.out.println("stackSiez" + stack.size());
			int stackSize = stack.size();
			for (int i = 0; i < stackSize; i++) {
				
				sum += stack.pop();
//				System.out.println("sum:" + sum);
			}
			System.out.println("#" + tc + " " + sum);

		}
	}

}
