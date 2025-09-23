
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] T = new int[N + 1];
		int[] P = new int[N + 1];
		int[] dp = new int[N + 1];
		T[0] = 0;
		P[0] = 0;
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		// dp 채우기
		for (int i = 1; i <= N; i++) {
			int max = dp[i -1];

			for (int j = i, x = 1; j >= 1; j--, x++) {
				if (T[j] == x) {
					max = Math.max(max, dp[j - 1] + P[j]);
				}
			}
			dp[i] = max;
		}

		int dp_max = 0;

		for (int i = 0; i < N + 1; i++) {
			dp_max = Math.max(dp_max, dp[i]);
		}

//		System.out.println(Arrays.toString(dp));
		System.out.println(dp_max);
	}
}
