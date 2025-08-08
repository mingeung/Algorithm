
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int T = 1; T <= 10; T++) {
			int tc = sc.nextInt();
			int N = sc.nextInt();
			int M = sc.nextInt();

			int result = recursion(N, M);
			
			System.out.println("#" + tc + " " + result);

		}
	}

	private static int recursion(int n, int m) {
		if (m == 1) {
			return n;
		}
		return n * recursion(n, m - 1);
	}

}
