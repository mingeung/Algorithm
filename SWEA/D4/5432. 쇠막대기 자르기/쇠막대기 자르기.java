

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
// 레이저는 인접한 쌍
// 쇠막대기는 ()괄호

// stack size로 풀어야 하나?
// 스택에 괄호 넣기,
// )가 나올 때 top에 (가 있으면 레이저인 것임. 둘다 pop을 하기

// 레이저를 꺼낼 때 -> stack 안의 size만큼 조각이 생긴다

// 레이저가 아닌 )가 나올 때는 하나 pop 해준다.

// 3+ 3 1 + 3 1+ + 2+ 1
// 나무막대의 괄호가 닫힐 때마다 + 1을 해줄까 -> 5

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {


		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();

		for (int i = 1; i <= tc; i++) {

			Stack<Character> stack = new Stack<>();

			String str = sc.next();
			
			int count = 0;
			boolean isOpen = true;
			for (int j = 0; j < str.length(); j++) {
				char paren = str.charAt(j);
				
				switch (paren) {
				
				case '(':
					//스택에 추가
					stack.push('(');
					isOpen = true;
					break;
				case ')':
					//스택의 맨 위가 '('인지 확인 -> 레이저임
					if (isOpen == true) {
						stack.pop();
						count += stack.size();
					} else {
						stack.pop();
						count += 1; 
					}
					isOpen = false;
					break;
				}
			}
			System.out.println("#" + i+ " " + count);
		}
	}
}

