

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//문제 접근
//리스트에 문자열을 넣고 반복을 돌린다. target에 해당되면 해당 문자열을 없앤다.
//이 경우는 리스트를 계속 돌기 때문에 시간초과가 날 가능성이 높다. 

//자료구조 스택을 사용하면 된다. 
//자바에서 스택을 사용하는 방
//1. 스택 클래스 사용 -> 오래된 vector 형식을 사용해서 불리하다. 
//2. StringBuilder를 스택처럼 사용한다. 
public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		
		Scanner sc = new Scanner(System.in);
		
		String string = sc.next();
		
		String target = sc.next();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0 ; i < string.length();i ++) {
			//한 글자씩 넣기 
			
			sb.append(string.charAt(i));
			//지금 넣는 수가 타겟의 마지막과 같다 면...
			if (string.charAt(i) == target.charAt(target.length() - 1) && sb.length() >= target.length()) {
//				System.out.println(string.charAt(i));
				boolean match = true;
				//
				for (int j = 0 ; j < target.length(); j++) {
//					System.out.println("i -j " + (i - j));
//					System.out.println("비교를 해보아" + string.charAt(i - j) + " " + target.charAt(target.length() - 1 - j));
					if (sb.charAt(sb.length() -1 - j) != target.charAt(target.length() - 1 - j)) {
						match = false;
						break;
					}
				}
				//만약에 문자열이 같다면 폭발 진
				if (match) {
//					System.out.println("폭발");
					sb.delete(sb.length() - target.length(), sb.length());
				}
//				System.out.psrintln("폭발 후 : " + sb);
			}
		}
		if (sb.length() == 0 ) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);
		}
		
		
		
		
	}
}
