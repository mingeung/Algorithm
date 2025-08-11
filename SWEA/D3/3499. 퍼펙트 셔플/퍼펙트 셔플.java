
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			
			String[] card;

			// 카드 수가 홀수라면 뒤에 하나 더해주기

			// 카드배열 담기
			if (N % 2 != 0) {
				card = new String[N + 1];
			} else {
				card = new String[N];
			}

			for (int i = 0; i < N; i++) {
				card[i] = sc.next();
			}
//			System.out.println(Arrays.toString(card));

			// 카드를 절반으로 나누고 번갈아서 넣기
			// 새로운 덱을 담을 배열 생성
			List<String> card2 = new ArrayList<>();
			
			for (int i = 0; i < card.length / 2; i++) {
				int center = card.length / 2;
				card2.add(card[i]);
				card2.add(card[i + center]);
			}
			
			//홀수라면 맨 뒤를 지우기
			//아까 하나를 생성했기 때문에
//			if (N % 2 != 0) {
//				card2.remove(N);
//			}
			
			//정답 출력
			System.out.print("#" + tc+" ");
			for (int i = 0; i < N; i++) {
				System.out.print(card2.get(i) + " ");
			}
			System.out.println();
			

		}
	}
}
