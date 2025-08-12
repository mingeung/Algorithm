
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args)  {


		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt(); // 원본 암호문의 길이
			LinkedList<Integer> code = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				int input = sc.nextInt();
				code.add(input);
			}
			int M = sc.nextInt(); //명령어의 개수
			for (int i = 0; i <M; i++) {
				String I = sc.next(); //이건 그냥 | 이거일거야
				int x = sc.nextInt(); //앞에서부터 x의 위치
				int y = sc.nextInt(); //y개의 숫자를 삽입할 것임
				int[] extraNum = new int[y] ;
				
				for (int j = 0; j < y; j++) {
					extraNum[j] = sc.nextInt();
				}
				
//				y만큼 돌면서 숫자 삽입하기
				for (int j = 0; j < y; j++) {
					code.add(x, extraNum[j]);
					x++;
				}
				

				
				
			}				//처음 10개의 숫자를 출력
			System.out.print("#" + tc + " ");
			for (int j = 0; j < 10; j++) {
				System.out.print(code.get(j) + " ");
			}
			System.out.println();
			

		}
	}

}
