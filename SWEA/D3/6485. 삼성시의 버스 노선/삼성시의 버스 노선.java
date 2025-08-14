
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args)  {


		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			HashMap<Integer, Integer> busStop = new HashMap<>();

			for (int i = 0; i < N; i++) {
				int A = sc.nextInt();
				int B = sc.nextInt();

				// A부터 B까지 숫자 올리기

				for (int j = A; j <= B; j++) {
					// 해당 키가 존재하는지 확인
					if (busStop.containsKey(j)) { // 존재한다면
						int count = busStop.get(j);
						busStop.put(j, count + 1);

					} else {
						busStop.put(j, 1);
					}

				}

			}
			int P = sc.nextInt();

			System.out.print("#" + tc + " ");
			int result = 0;
			for (int i = 0; i < P; i++) {
				int target = sc.nextInt();
				
				if (busStop.containsKey(target)) {
					 result = busStop.get(target);
				}
				else {
					result = 0;
				}
				System.out.print(result + " ");
			}
			System.out.println();

		}
	}
}
