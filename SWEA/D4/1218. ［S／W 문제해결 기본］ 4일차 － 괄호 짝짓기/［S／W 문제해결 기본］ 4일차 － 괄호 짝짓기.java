

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		

		for (int tc = 1; tc <= 10; tc++) {
			int len = sc.nextInt();
			
			boolean isOk = true;

			String str = sc.next();

			Stack<Character> stack = new Stack<>();

			outer: for (int i = 0; i < len; i++) {
				char paren = str.charAt(i);
				// 여는 괄호라면
				if (paren == '(' || paren == '[' || paren == '{' || paren == '<') {
					stack.push(paren);
				}
				// 닫는 괄호라면
				else {
					// 스택에 요소가 비어있으면? -> 실패!!
					if (stack.isEmpty()) {
						isOk = false;
						break;
					} else {

						// 짝이 맞는지 확인
						switch (paren) {
						case ')':
							if (stack.pop() != '(') {
//								System.out.println("안 됨!!");
								isOk = false;
								break outer;
							}
							break;
						case ']':
							if (stack.pop() != '[') {
//								System.out.println("안 됨!!");
								isOk = false;
								break outer;
							}
							break;
						case '}':
							if (stack.pop() != '{') {
//								System.out.println("안 됨!!");
								isOk = false;
								break outer;
							}
							break;
						case '>':
							if (stack.pop() != '<') {
//								System.out.println("안 됨!!");
								isOk = false;
								break outer;
							}
							break;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + (isOk ? 1 : 0));
		}

	}

}

//강사님의 코드 : 
//char other = stack.pop()
//if (other == '(' && ch ==')') continue;