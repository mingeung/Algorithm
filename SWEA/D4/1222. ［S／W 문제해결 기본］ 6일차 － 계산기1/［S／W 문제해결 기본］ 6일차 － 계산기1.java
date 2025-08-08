
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			// 전위연산자를 후위연산자로 바꾸기 =====================================

			int len = sc.nextInt();

			// 연산자를 저장할 스택
			Stack<Character> stack = new Stack<>();

			// 후위표시수식을 저장할 StringBuilder
			StringBuilder postfix = new StringBuilder();

			String infix = sc.next();

			// 연산자 우선순위 정리하기

			for (int i = 0; i < infix.length(); i++) {
				char ch = infix.charAt(i);
//				System.out.println("ch: " + ch);

				// 1. 피연산자라면 출력
				if (ch >= '0' && ch <= '9') { // AND 대신 OR 연산자를 쓰면 안된다.
					postfix.append(ch);
				}
				// 2. top < 현재 -> push
				else if (ch == '+') {
					if (!stack.isEmpty()) {
						postfix.append(stack.pop());
					}
					stack.push(ch);

				}

			}
			while (!stack.isEmpty()) {
				postfix.append(stack.pop());
			}

			// 후위 연산자를 구했다!!

			// 후위 연산자 실제 연산하기 ===============================================
			// 1. 숫자가 나오면 stack에 넣는다
			// 2. 연산자가 나오면 stack에서 꺼내 b + a를 해준다.
			// 2.1 .더한 수를 다시 stack에 넣는다. -> 놓친부분

			// 피연산자를 저장할 stack
			Stack<Integer> opStack = new Stack<>();

			for (int i = 0; i < postfix.length(); i++) {
				char ch = postfix.charAt(i);

				if (Character.isDigit(ch)) {// 숫자인지 알아내는 또 다른 방법
					opStack.push(ch - '0'); // 숫자로 저장하기
				} else {
					int a = opStack.pop();
					int b = opStack.pop();
					int temp = a + b;
					opStack.push(temp);
				}
			}
			System.out.println("#" + tc + " " + opStack.pop());

		}
	}
}
