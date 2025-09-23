

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] stairs = new int[N + 1];
		int[] dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		// 기저조건 넣어주기 -> 인덱스 범위를  확인해서 분기해주기
		
		dp[0] = 0;
		
		if (N >= 1) {
			dp[1] = stairs[1];
			
		}

		
		if (N >=2 ) {
			dp[2] = stairs[1] + stairs[2];
			
		}

		// 점화식 세우기
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]) + stairs[i];
		}

		System.out.println(dp[N]);

	}
}
